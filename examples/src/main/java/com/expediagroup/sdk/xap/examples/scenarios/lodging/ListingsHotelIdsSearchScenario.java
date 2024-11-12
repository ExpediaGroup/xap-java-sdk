package com.expediagroup.sdk.xap.examples.scenarios.lodging;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.XapScenario;
import com.expediagroup.sdk.xap.models.Hotel;
import com.expediagroup.sdk.xap.models.HotelListingsResponse;
import com.expediagroup.sdk.xap.models.PresignedUrlResponse;
import com.expediagroup.sdk.xap.models.RoomType;
import com.expediagroup.sdk.xap.operations.GetFeedDownloadUrlOperation;
import com.expediagroup.sdk.xap.operations.GetFeedDownloadUrlOperationParams;
import com.expediagroup.sdk.xap.operations.GetLodgingListingsOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingListingsOperationParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to retrieve accessible property ids and location content from
 * SDP DownloadURL API and then get the prices of these properties using the Lodging Listings API.
 *
 * <p>This is a common scenario for meta site partners. In practice, you can cache the property id
 * list along with content that does not change frequently (name, description, address, amenities,
 * etc.) to reduce heavy API calls and get only the prices of these properties in real-time from
 * the Lodging Listings API.
 */
public class ListingsHotelIdsSearchScenario implements XapScenario {

  private final XapClient client = createClient();

  private static final Logger LOGGER =
      LoggerFactory.getLogger(ListingsHotelIdsSearchScenario.class);

  /**
   * The property count to read from the file.
   * If the first 20 properties are not available when you run this example, it may end with
   * NO_RESULT_FOUND. In that case, you can adjust the property count to get more properties.
   */
  private static final int PROPERTY_COUNT = 20;

  /**
   * A property id to location map. This mocks a cache in this example to store the static content
   * of the properties.
   */
  private static final Map<String, String> PROPERTY_ID_AND_LOCATION_CACHE = new HashMap<>();

  public static void main(String[] args) {
    new ListingsHotelIdsSearchScenario().run();
    System.exit(0);
  }

  @Override
  public void run() {
    LOGGER.info(
        "======================== Running ListingsHotelIdsSearchScenario =======================");

    List<String> propertyIds = getPropertyIdsFromDownloadUrl();
    cachePropertyLocationFromDownloadUrl(propertyIds);
    HotelListingsResponse hotelListingsResponse = getPropertyPriceFromLodgingListings(propertyIds);
    displayResult(hotelListingsResponse);

    LOGGER.info(
        "========================== End ListingsHotelIdsSearchScenario =========================");
  }

  /**
   * Retrieve accessible property ids from SDP DownloadURL API.
   *
   * @return property ids
   */
  private List<String> getPropertyIdsFromDownloadUrl() {
    LOGGER.info(
        "====================== Executing getPropertyIdsFromDownloadUrl =====================");

    GetFeedDownloadUrlOperationParams getPropertyIdListParams =
        GetFeedDownloadUrlOperationParams.builder()
            // Use the type LISTINGS to get the list of accessible property ids.
            .type(GetFeedDownloadUrlOperationParams.Type.LISTINGS)
            // Without any filters, this operation will return the information of all lodging
            // properties in en_US by default.
            .build();

    Response<PresignedUrlResponse> downloadUrlListingsResponse =
        client.execute(new GetFeedDownloadUrlOperation(getPropertyIdListParams));

    if (downloadUrlListingsResponse.getData() == null
        || downloadUrlListingsResponse.getData().getBestMatchedFile() == null) {
      throw new IllegalStateException("No listings file found");
    }

    // The download URL points to a zip file containing various jsonl files.
    // Each line in the jsonl files contains a json object representing a property.
    // For demonstration purposes, we will only read a few properties from the file without
    // downloading the entire file.
    String listingsDownloadUrl = downloadUrlListingsResponse.getData()
        .getBestMatchedFile()
        .getDownloadUrl();
    LOGGER.info("Listings Download URL: {}", listingsDownloadUrl);

    // Read property ids from the file.
    List<String> propertyIds = getPropertyIdsFromListingsFile(listingsDownloadUrl);

    if (propertyIds.isEmpty()) {
      throw new IllegalStateException("No accessible property ids found.");
    }
    LOGGER.info("Accessible Property Ids: {}", propertyIds);

    LOGGER.info(
        "======================= getPropertyIdsFromDownloadUrl Executed ======================");
    return propertyIds;
  }

  /**
   * Cache the location content from SDP DownloadURL API.
   *
   * @param propertyIds The property ids that need the location content.
   */
  private void cachePropertyLocationFromDownloadUrl(List<String> propertyIds) {
    LOGGER.info(
        "====================== Executing CachePropertyLocationFromDownloadUrl ==================");
    GetFeedDownloadUrlOperationParams getPropertyLocationParams =
        GetFeedDownloadUrlOperationParams.builder()
            // Use the type LOCATIONS to get the address of accessible properties.
            .type(GetFeedDownloadUrlOperationParams.Type.LOCATIONS)
            // Without any filters, this operation will return the information of all lodging
            // properties in en_US by default.
            .build();

    Response<PresignedUrlResponse> downloadUrlLocationsResponse =
        client.execute(new GetFeedDownloadUrlOperation(getPropertyLocationParams));

    if (downloadUrlLocationsResponse.getData() == null
        || downloadUrlLocationsResponse.getData().getBestMatchedFile() == null) {
      throw new IllegalStateException("No location file found");
    }

    String locationsDownloadUrl = downloadUrlLocationsResponse.getData()
        .getBestMatchedFile()
        .getDownloadUrl();
    LOGGER.info("Locations Download URL: {}", locationsDownloadUrl);

    // Read and cache property locations from the file.
    cachePropertyLocationFromLocationsFile(locationsDownloadUrl, propertyIds);

    LOGGER.info(
        "===================== CachePropertyLocationFromDownloadUrl Executed ====================");
  }

  /**
   * Get prices of the properties using the Lodging Listings API.
   *
   * @param propertyIds The property ids to get the prices.
   * @return The response of the Lodging Listings API.
   */
  private HotelListingsResponse getPropertyPriceFromLodgingListings(List<String> propertyIds) {
    LOGGER.info(
        "===================== Executing GetPropertyPriceFromLodgingListings ====================");

    GetLodgingListingsOperationParams getLodgingListingsOperationParams =
        GetLodgingListingsOperationParams.builder()
            .partnerTransactionId(PARTNER_TRANSACTION_ID)
            // Use the property ids read from the file
            .ecomHotelIds(new HashSet<>(propertyIds))
            // Use LOWEST to get minimal content details to reduce the response size
            .contentDetails(GetLodgingListingsOperationParams.ContentDetails.LOWEST)
            // Check-in 5 days from now
            .checkIn(LocalDate.now().plusDays(5))
            // Check-out 10 days from now
            .checkOut(LocalDate.now().plusDays(10))
            // Filter the properties that are available only
            .availOnly(true)
            // Use the default occupancy: 2 adults in one room
            .build();

    HotelListingsResponse hotelListingsResponse =
        client.execute(new GetLodgingListingsOperation(getLodgingListingsOperationParams))
            .getData();

    LOGGER.info(
        "===================== GetPropertyPriceFromLodgingListings Executed ====================");
    return hotelListingsResponse;
  }

  /**
   * Reads given number of property ids from the file pointed by the download URL.
   *
   * @param downloadUrl The download URL of the zip file containing the property information.
   * @return A list of property ids read from the file.
   */
  private List<String> getPropertyIdsFromListingsFile(String downloadUrl) {
    List<String> propertyIds = new ArrayList<>();
    HttpURLConnection connection = null;
    try {
      // Open a connection to the URL
      URL url = new URL(downloadUrl);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setDoInput(true);

      try (ZipInputStream zipStream = new ZipInputStream(connection.getInputStream())) {
        ZipEntry entry;
        while ((entry = zipStream.getNextEntry()) != null) {
          if (entry.getName().endsWith(".jsonl")) {
            LOGGER.info("Reading property ids from file: {}", entry.getName());
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(zipStream))) {
              String line;
              ObjectMapper objectMapper = new ObjectMapper();
              while ((line = reader.readLine()) != null && propertyIds.size() < PROPERTY_COUNT) {
                // Parse the property id from the json object
                // An example json line from the jsonl file:
                /*
                {
                  "propertyId": {
                    "expedia": "75032362",
                    "hcom": "2402035584",
                    "vrbo": "731.2068610.2244521"
                  },
                  "bookable": {
                    "expedia": true,
                    "hcom": true,
                    "vrbo": true
                  },
                  "propertyType": {
                    "id": 16,
                    "name": "Apartment"
                  },
                  "lastUpdated": "10-27-2024 13:41:16",
                  "country": "France",
                  "inventorySource": "vrbo",
                  "referencePrice": {
                    "value": "89.52",
                    "currency": "USD"
                  },
                  "vrboPropertyType": {
                    "instantBook": true
                  }
                }
                */
                JsonNode jsonNode = objectMapper.readTree(line);
                // Check if the property is accessible from Lodging Listings API
                // (Vrbo properties that are not instantBookable are not accessible for now)
                if (!jsonNode.get("propertyId").get("vrbo").asText().isEmpty()
                    && jsonNode.has("vrboPropertyType")
                    && !jsonNode.get("vrboPropertyType").get("instantBook").asBoolean()
                ) {
                  // Skip the property if it is a not instant bookable Vrbo property
                  continue;
                } else {
                  // Get the Expedia property id for the Lodging Listings API
                  propertyIds.add(jsonNode.get("propertyId").get("expedia").asText());
                }
              }
            }
          }
        }
      }

    } catch (IOException e) {
      LOGGER.error("Error reading property ids from download URL", e);
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
    return propertyIds;
  }

  /**
   * Caches the location content of the properties from the file pointed by the download URL.
   *
   * @param locationsDownloadUrl The download URL of the zip file containing the property locations.
   * @param propertyIds          The property ids to get the location content.
   */
  private void cachePropertyLocationFromLocationsFile(String locationsDownloadUrl,
                                                      List<String> propertyIds) {
    HttpURLConnection connection = null;
    try {
      // Open a connection to the URL
      URL url = new URL(locationsDownloadUrl);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setDoInput(true);

      try (ZipInputStream zipStream = new ZipInputStream(connection.getInputStream())) {
        ZipEntry entry;
        while ((entry = zipStream.getNextEntry()) != null) {
          if (entry.getName().endsWith(".jsonl")) {
            LOGGER.info("Reading property locations from file: {}", entry.getName());
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(zipStream))) {
              String line;
              ObjectMapper objectMapper = new ObjectMapper();
              while ((line = reader.readLine()) != null
                  && PROPERTY_ID_AND_LOCATION_CACHE.size() < propertyIds.size()) {
                // Parse the property location from the json object
                // An example json line from the jsonl file:
                /*
                {
                  "propertyId": {
                    "expedia": "75032362",
                    "hcom": "2402035584",
                    "vrbo": "731.2068610.2244521"
                  },
                  "propertyType": {
                    "id": 16,
                    "name": "Apartment"
                  },
                  "propertyName": "Pasteur : Charming 2 bedroom flat near the thermal baths",
                  "address1": "",
                  "address2": "",
                  "city": "Bains-les-Bains",
                  "province": "Grand Est",
                  "country": "France",
                  "postalCode": "88240",
                  "geoLocation": {
                    "latitude": "48.003323",
                    "longitude": "6.264765",
                    "obfuscated": false
                  },
                  "locationAttribute": {
                    "city": {
                      "id": "553248635975861971",
                      "name": "Forges-les-Bains"
                    },
                    "region": {
                      "id": "553248634539665703",
                      "name": "Paroisse Saint-Colomban-en-Vôge"
                    },
                    "distanceFromCityCenter": {
                      "distance": "0.21",
                      "unit": "km"
                    }
                  }
                }
                */
                JsonNode jsonNode = objectMapper.readTree(line);
                // Check if the property id is in the list
                if (propertyIds.contains(jsonNode.get("propertyId").get("expedia").asText())) {
                  // Get the location content of the property
                  String location = jsonNode.get("propertyName").asText() + ", "
                      + jsonNode.get("city").asText() + ", "
                      + jsonNode.get("province").asText() + ", "
                      + jsonNode.get("country").asText();
                  // Store the location content in the cache
                  PROPERTY_ID_AND_LOCATION_CACHE.put(
                      jsonNode.get("propertyId")
                          .get("expedia")
                          .asText(),
                      location);
                }
              }
            }
          }
        }
      }

    } catch (IOException e) {
      LOGGER.error("Error reading property locations from download URL", e);
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }

  /**
   * Display the result of the operations.
   *
   * @param hotelListingsResponse The response of the Lodging Listings API.
   */
  private static void displayResult(HotelListingsResponse hotelListingsResponse) {
    if (hotelListingsResponse == null || hotelListingsResponse.getHotels() == null
        || hotelListingsResponse.getHotels().isEmpty()) {
      throw new IllegalStateException("No properties found.");
    }

    // The HotelListingsResponse contains a transaction ID for troubleshooting
    LOGGER.info("Transaction ID: {}", hotelListingsResponse.getTransactionId());

    // To access the properties, iterate through the list of hotel properties
    hotelListingsResponse.getHotels().forEach(hotel -> {
      // Check if the property is available
      if (Hotel.Status.AVAILABLE != hotel.getStatus()) {
        LOGGER.info("Property {} is not available.", hotel.getId());
        return;
      }
      LOGGER.info(
          "=================================== Property Start ===================================");
      String hotelId = hotel.getId();

      // Get the location content of the property from the cache
      LOGGER.info("Property Id: {}", hotelId);
      LOGGER.info("Cached Property Location: {}", PROPERTY_ID_AND_LOCATION_CACHE.get(hotelId));

      // Get the price of the property from the room type
      if (hotel.getRoomTypes() != null && !hotel.getRoomTypes().isEmpty()) {
        RoomType roomType = hotel.getRoomTypes().get(0);
        if (roomType.getPrice() != null) {
          // To get the total price of the room type
          if (roomType.getPrice().getTotalPrice() != null) {
            LOGGER.info("Price: {}, Currency: {}",
                roomType.getPrice().getTotalPrice().getValue(),
                roomType.getPrice().getTotalPrice().getCurrency());
          }
          // To get the average nightly rate of the room type
          if (roomType.getPrice().getAvgNightlyRate() != null) {
            LOGGER.info("Average Nightly Rate: {}, Currency: {}",
                roomType.getPrice().getAvgNightlyRate().getValue(),
                roomType.getPrice().getAvgNightlyRate().getCurrency());
          }
        }
      }
      LOGGER.info(
          "==================================== Property End ====================================");
    });
  }
}
