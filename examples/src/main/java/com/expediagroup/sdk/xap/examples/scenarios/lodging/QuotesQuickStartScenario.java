package com.expediagroup.sdk.xap.examples.scenarios.lodging;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.models.LodgingQuotesResponse;
import com.expediagroup.sdk.xap.models.LodgingRoomType;
import com.expediagroup.sdk.xap.models.PresignedUrlResponse;
import com.expediagroup.sdk.xap.models.Property;
import com.expediagroup.sdk.xap.models.Room;
import com.expediagroup.sdk.xap.operations.GetFeedDownloadUrlOperation;
import com.expediagroup.sdk.xap.operations.GetFeedDownloadUrlOperationParams;
import com.expediagroup.sdk.xap.operations.GetLodgingQuotesOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingQuotesOperationParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to retrieve accessible Vrbo property ids and location content from
 * SDP DownloadURL API and then get the prices of these properties using the Lodging Quotes API.
 *
 * <p>This is a common scenario for Vrbo partners. In practice, you can cache the property id
 * list along with content that does not change frequently (name, description, address, amenities,
 * etc.) to reduce heavy API calls and get only the prices of these properties in real-time from
 * the Lodging Quotes API.
 *
 * <p>Note: this is a Vrbo scenario. You need a key that is enabled for Vrbo brand to run this.
 */
public class QuotesQuickStartScenario implements VrboScenario {

  private final XapClient client = createClient();

  private static final Logger LOGGER =
      LoggerFactory.getLogger(QuotesQuickStartScenario.class);

  /**
   * This field limits the number of line to read from the SDP DownloadURL API Listings file to
   * reduce time to run the example.
   * If the first 20 properties from the file are not accessible OR available when you run this
   * example, it may end with "No accessible property ids found." OR NO_RESULT_FOUND. In that case,
   * you can adjust the property count to get more properties.
   */
  private static final int SAMPLE_ITEMS_RESTRICTION = 20;

  /**
   * A property id to location map. This mocks a cache in this example to store the static content
   * of the properties.
   */
  private static final Map<String, String> PROPERTY_ID_AND_LOCATION_CACHE = new HashMap<>();

  public static void main(String[] args) {
    new QuotesQuickStartScenario().run();
    System.exit(0);
  }

  @Override
  public void run() {
    LOGGER.info(
        "======================== Running QuotesQuickStartScenario =======================");

    List<String> propertyIds = getPropertyIdsFromDownloadUrl();
    cachePropertyLocationFromDownloadUrl(propertyIds);
    LodgingQuotesResponse lodgingQuotesResponse = getPropertyPriceFromLodgingQuotes(propertyIds);
    displayResult(lodgingQuotesResponse);

    LOGGER.info(
        "========================== End QuotesQuickStartScenario =========================");
  }

  private List<String> getPropertyIdsFromDownloadUrl() {
    LOGGER.info(
        "==================== Executing Step I: getPropertyIdsFromDownloadUrl ===================");

    GetFeedDownloadUrlOperationParams getPropertyIdListParams =
        GetFeedDownloadUrlOperationParams.builder()
            // Use the type VACATION_RENTAL to get the list of accessible Vrbo property ids.
            .type(GetFeedDownloadUrlOperationParams.Type.VACATION_RENTAL)
            // Without any filters, this operation will return the information of all Vrbo
            // properties in en_US by default.
            .build();

    Response<PresignedUrlResponse> downloadUrlListingsResponse =
        client.execute(new GetFeedDownloadUrlOperation(getPropertyIdListParams));

    if (downloadUrlListingsResponse.getData() == null
        || downloadUrlListingsResponse.getData().getBestMatchedFile() == null) {
      throw new IllegalStateException("No vacation rental file found");
    }

    // The download URL points to a zip file containing various jsonl files.
    // Each line in the jsonl files contains a json object representing a Vrbo property.
    // For demonstration purposes, we will only read a few properties from the file without
    // downloading the entire file.
    String vacationRentalDownloadUrl = downloadUrlListingsResponse.getData()
        .getBestMatchedFile()
        .getDownloadUrl();
    LOGGER.info("Vacation Rental Download URL: {}", vacationRentalDownloadUrl);

    // Read property ids from the file.
    List<String> propertyIds = getPropertyIdsFromVacationRentalFile(vacationRentalDownloadUrl
    );

    if (propertyIds.isEmpty()) {
      throw new IllegalStateException("No accessible Vrbo property ids found.");
    }
    LOGGER.info("Accessible Vrbo Property Ids: {}", propertyIds);

    LOGGER.info(
        "==================== Step I: getPropertyIdsFromDownloadUrl Executed ====================");
    return propertyIds;
  }

  /**
   * Cache the location content from SDP DownloadURL API.
   *
   * @param propertyIds The property ids that need the location content.
   */
  private void cachePropertyLocationFromDownloadUrl(List<String> propertyIds) {
    LOGGER.info(
        "================ Executing Step II: CachePropertyLocationFromDownloadUrl ===============");
    GetFeedDownloadUrlOperationParams getPropertyLocationParams =
        GetFeedDownloadUrlOperationParams.builder()
            // Use the type LOCATIONS to get the address of accessible properties.
            .type(GetFeedDownloadUrlOperationParams.Type.LOCATIONS)
            // Filter the properties by brand.
            .brand(GetFeedDownloadUrlOperationParams.Brand.VRBO)
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
        "================= Step II: CachePropertyLocationFromDownloadUrl Executed ===============");
  }

  /**
   * Get prices of the properties using the Lodging Quotes API.
   *
   * @param propertyIds The property ids to get the prices.
   * @return The response of the Lodging Quotes API.
   */
  private LodgingQuotesResponse getPropertyPriceFromLodgingQuotes(List<String> propertyIds) {
    LOGGER.info(
        "================= Executing Step III: GetPropertyPriceFromLodgingQuotes ================");

    // Build the occupancy
    ArrayList<Room> rooms = new ArrayList<>();
    // The first room, with 2 adult
    rooms.add(Room.builder().adults(2L).childAges(null).build());

    // Build the query parameters with GetLodgingQuotesOperationParams
    GetLodgingQuotesOperationParams quotesOperationParams =
        GetLodgingQuotesOperationParams.builder()
            .partnerTransactionId(PARTNER_TRANSACTION_ID)
            // Check-in 5 days from now
            .checkIn(LocalDate.now().plusDays(5))
            // Check-out 10 days from now
            .checkOut(LocalDate.now().plusDays(10))
            // Set of Expedia Property IDs.
            .propertyIds(new HashSet<>(propertyIds))
            // The links to return, WEB includes WS (Web Search Result Page) and
            // WD (Web Details Page)
            .links(Collections.singletonList(GetLodgingQuotesOperationParams.Links.WEB))
            .rooms(rooms)
            .build();

    LodgingQuotesResponse lodgingQuotesResponse =
        client.execute(new GetLodgingQuotesOperation(quotesOperationParams))
            .getData();

    LOGGER.info(
        "================= Step III: GetPropertyPriceFromLodgingQuotes Executed =================");
    return lodgingQuotesResponse;
  }

  /**
   * Reads given number of property ids from the file pointed by the download URL.
   *
   * @param downloadUrl The download URL of the zip file containing the property information.
   * @return A list of property ids read from the file.
   */
  private List<String> getPropertyIdsFromVacationRentalFile(String downloadUrl) {
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
              while ((line = reader.readLine()) != null
                  && propertyIds.size() < SAMPLE_ITEMS_RESTRICTION) {
                // Parse the property id from the json object
                // An example json line from the jsonl file:
                /*
                {
                  "propertyId": {
                    "expedia": "1234567",
                    "hcom": "987654321",
                    "vrbo": "123.1234567.7654321"
                  },
                  "country": "France",
                  "propertySize": {
                    "measurement": 441,
                    "units": "SQUARE_FEET"
                  },
                  "maxOccupancy": 4,
                  "bathrooms": {
                    "numberOfBathrooms": 1
                  },
                  "bedrooms": {
                    "numberOfBedrooms": 2
                  },
                  "houseRules": {
                    "partyOrEventRules": {
                      "partiesOrEventsPermitted": false,
                      "ownerPartyFreeText": "No events allowed"
                    },
                    "smokingRules": {
                      "smokingPermitted": false,
                      "ownerSmokingFreeText": "Smoking is not permitted"
                    },
                    "petRules": {
                      "petsPermitted": true,
                      "ownerPetsFreeText": "Pets allowed"
                    },
                    "childRules": {
                      "childrenPermitted": true,
                      "ownerChildrenFreeText": "Children allowed: ages 0-17 "
                    }
                  },
                  "propertyManager": {
                    "name": "Résidences Louis",
                    "hostType": "Professional"
                  },
                  "premierHost": true,
                  "propertyLiveDate": "2022-05-31"
                }
                */
                JsonNode jsonNode = objectMapper.readTree(line);
                propertyIds.add(jsonNode.get("propertyId").get("expedia").asText());
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
                    "expedia": "1234567",
                    "hcom": "987654321",
                    "vrbo": "123.1234567.1234567"
                  },
                  "propertyType": {
                    "id": 16,
                    "name": "Apartment"
                  },
                  "propertyName": "Vrbo Property Name",
                  "address1": "",
                  "address2": "",
                  "city": "Newark",
                  "province": "Delaware",
                  "country": "United States",
                  "postalCode": "19711",
                  "geoLocation": {
                    "latitude": "10.999999",
                    "longitude": "-10.999999",
                    "obfuscated": false
                  },
                  "locationAttribute": {
                    "neighborhood": {
                      "id": "553248635976468695",
                      "name": "Westmoreland"
                    },
                    "city": {
                      "id": "8946",
                      "name": "Newark"
                    },
                    "region": {
                      "id": "6055689",
                      "name": "North Wilmington"
                    },
                    "airport": {
                      "id": "6028579",
                      "code": "ILG",
                      "name": "Wilmington, DE (ILG-New Castle)",
                      "distance": "13.17",
                      "unit": "km"
                    },
                    "distanceFromCityCenter": {
                      "distance": "1.24",
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
   * @param lodgingQuotesResponse The response of the Lodging Quotes API.
   */
  private static void displayResult(LodgingQuotesResponse lodgingQuotesResponse) {
    LOGGER.info("======================= Executing Step IV: DisplayResult =======================");
    if (lodgingQuotesResponse == null || lodgingQuotesResponse.getProperties() == null
        || lodgingQuotesResponse.getProperties().isEmpty()) {
      throw new IllegalStateException("No properties found.");
    }

    // The HotelListingsResponse contains a transaction ID for troubleshooting
    LOGGER.info("Transaction ID: {}", lodgingQuotesResponse.getTransactionId());

    // To access the properties, iterate through the list of hotel properties
    lodgingQuotesResponse.getProperties().forEach(property -> {
      // Check if the property is available
      if (Property.Status.AVAILABLE != property.getStatus()) {
        LOGGER.info("Property {} is not available.", property.getId());
        return;
      }
      LOGGER.info(
          "=================================== Property Start ===================================");
      String propertyId = property.getId();

      // Get the location content of the property from the cache
      LOGGER.info("Property Id: {}", propertyId);
      LOGGER.info("Cached Property Location: {}", PROPERTY_ID_AND_LOCATION_CACHE.get(propertyId));

      // Get the price of the property from the room type
      if (property.getRoomTypes() != null && !property.getRoomTypes().isEmpty()) {
        // To get the first room type information
        LodgingRoomType roomType = property.getRoomTypes().get(0);

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
        // To get the free cancellation flag of the selected room
        if (roomType.getRatePlans() != null && !roomType.getRatePlans().isEmpty()
            && roomType.getRatePlans().get(0).getCancellationPolicy() != null) {
          LOGGER.info("Free Cancellation: {}",
              roomType.getRatePlans().get(0).getCancellationPolicy().getFreeCancellation());
        }
        if (roomType.getLinks() != null) {
          // To get the deeplink to the website Search Result Page
          if (roomType.getLinks().getWebSearchResult() != null) {
            LOGGER.info("WebSearchResult Link: {}",
                roomType.getLinks().getWebSearchResult().getHref());
          }
          // To get the deeplink to the website Details Page
          if (roomType.getLinks().getWebDetails() != null) {
            LOGGER.info("WebDetails Link: {}", roomType.getLinks().getWebDetails().getHref());
          }
        }
      }
      LOGGER.info(
          "==================================== Property End ====================================");
    });
    LOGGER.info("======================= Step IV: DisplayResult Executed ========================");
  }
}
