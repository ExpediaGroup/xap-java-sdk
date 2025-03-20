package com.expediagroup.sdk.xap.integrations.lodging;

import static com.expediagroup.sdk.xap.integrations.common.Constant.ACCEPT_HOTEL;
import static com.expediagroup.sdk.xap.integrations.common.Constant.AUTHORIZATION;
import static com.expediagroup.sdk.xap.integrations.common.Constant.MOCK_KEY;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.xap.integrations.common.Constant;
import com.expediagroup.sdk.xap.integrations.common.XapIntegrationTests;
import com.expediagroup.sdk.xap.models.AgeClassRestriction;
import com.expediagroup.sdk.xap.models.BedType;
import com.expediagroup.sdk.xap.models.CancellationPolicy;
import com.expediagroup.sdk.xap.models.Distance;
import com.expediagroup.sdk.xap.models.Hotel;
import com.expediagroup.sdk.xap.models.HotelHotelAmenitiesInner;
import com.expediagroup.sdk.xap.models.HotelLinks;
import com.expediagroup.sdk.xap.models.HotelLinksApiRateCalendar;
import com.expediagroup.sdk.xap.models.HotelLinksWebSearchResult;
import com.expediagroup.sdk.xap.models.HotelListingsResponse;
import com.expediagroup.sdk.xap.models.HotelLocation;
import com.expediagroup.sdk.xap.models.HotelPropertyType;
import com.expediagroup.sdk.xap.models.HotelRoomAmenitiesInner;
import com.expediagroup.sdk.xap.models.LocationGeoLocation;
import com.expediagroup.sdk.xap.models.Media;
import com.expediagroup.sdk.xap.models.Neighborhood;
import com.expediagroup.sdk.xap.models.NonRefundableDateRange;
import com.expediagroup.sdk.xap.models.PaymentSchedule;
import com.expediagroup.sdk.xap.models.RatePlan;
import com.expediagroup.sdk.xap.models.RatePlanAmenitiesInner;
import com.expediagroup.sdk.xap.models.RatePlanPrice;
import com.expediagroup.sdk.xap.models.RatePlanPriceNightlyRatesInner;
import com.expediagroup.sdk.xap.models.RatePlanPriceTaxesAndFeesDetailsInner;
import com.expediagroup.sdk.xap.models.RatePlanStandalonePrice;
import com.expediagroup.sdk.xap.models.Room;
import com.expediagroup.sdk.xap.models.RoomOccupancyPolicy;
import com.expediagroup.sdk.xap.models.RoomType;
import com.expediagroup.sdk.xap.models.RoomTypeAmenitiesInner;
import com.expediagroup.sdk.xap.models.RoomTypeLinks;
import com.expediagroup.sdk.xap.models.RoomTypeLinksApiDetails;
import com.expediagroup.sdk.xap.models.RoomTypeLinksWebDetails;
import com.expediagroup.sdk.xap.models.RoomTypeMediaInner;
import com.expediagroup.sdk.xap.models.RoomTypePrice;
import com.expediagroup.sdk.xap.models.RoomTypeStandalonePrice;
import com.expediagroup.sdk.xap.models.StayDates;
import com.expediagroup.sdk.xap.operations.GetLodgingListingsOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingListingsOperationParams;
import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource;
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import okhttp3.Headers;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * This class is used to test the integration of the Lodging Listings API.
 */
@TestWithResources
public class ListingsIntegrationTests extends XapIntegrationTests {

  @Test
  public void testRequest() {
    ArrayList<Room> rooms = new ArrayList<>();
    rooms.add(Room.builder().adults(1L).build());
    rooms.add(Room.builder().adults(2L).childAges(Arrays.asList(10L, 12L)).build());
    rooms.add(Room.builder().adults(1L).childAges(Collections.singletonList(5L)).build());
    rooms.add(Room.builder().adults(6L).build());
    rooms.add(Room.builder().adults(2L).childAges(Arrays.asList(3L, 4L)).build());
    rooms.add(Room.builder().adults(3L).childAges(Arrays.asList(1L, 2L, 3L, 4L, 5L)).build());
    rooms.add(Room.builder().adults(2L).childAges(Arrays.asList(1L, 2L, 3L, 4L)).build());
    rooms.add(Room.builder().adults(1L).build());

    GetLodgingListingsOperationParams getLodgingListingsOperationParams =
        GetLodgingListingsOperationParams.builder()
            .partnerTransactionId(UUID.randomUUID().toString())
            // hotel selection
            .ecomHotelIds(new HashSet<>(Arrays.asList("123", "456")))
            .hcomHotelIds(new HashSet<>(Arrays.asList("135", "246")))
            .geoLocation("47.6062,-122.3321")
            .regionIds(new HashSet<>(Collections.singletonList("1001")))
            .locationKeyword("Space Needle, Seattle")
            .radius(10)
            .unit(GetLodgingListingsOperationParams.Unit.KM)
            // search
            .checkIn(LocalDate.now().plusDays(5))
            .checkOut(LocalDate.now().plusDays(10))
            .locale("en_US")
            .currency("USD")
            .rooms(rooms)
            .source(GetLodgingListingsOperationParams.Source.BROWSER)
            .travelWithPets(true)
            // content detail
            .contentDetails(GetLodgingListingsOperationParams.ContentDetails.HIGH)
            .allRoomTypes(true)
            .links(Arrays.asList(GetLodgingListingsOperationParams.Links.WEB,
                GetLodgingListingsOperationParams.Links.API))
            // filtering
            .minStarRating(GetLodgingListingsOperationParams.MinStarRating._1_PERIOD5)
            .maxStarRating(GetLodgingListingsOperationParams.MaxStarRating._5_PERIOD0)
            .limit(5)
            .queryText("Seattle")
            .availOnly(true)
            .smokingPreference(GetLodgingListingsOperationParams.SmokingPreference.BOTH)
            .rateType(GetLodgingListingsOperationParams.RateType.ALL)
            .imageSizes(GetLodgingListingsOperationParams.ImageSizes.S)
            .thumbnailImageSize(GetLodgingListingsOperationParams.ThumbnailImageSize.T)
            .includedPropertyTypeIds(new HashSet<>(Arrays.asList("1", "2")))
            .excludedPropertyTypeIds(new HashSet<>(Arrays.asList("3", "4")))
            .includedInventorySourceIds(new HashSet<>(Arrays.asList("5", "6")))
            .freeCancellation(true)
            .groupedAmenities(true)
            .blockFullDepositRateplan(true)
            // package pricing
            .ratePlanType(GetLodgingListingsOperationParams.RatePlanType.PACKAGE)
            // sorting
            .sortType(GetLodgingListingsOperationParams.SortType.PRICE)
            .sortOrder(GetLodgingListingsOperationParams.SortOrder.ASC)
            .build();

    mockWebServer.enqueue(
        new MockResponse()
            .setHeader("Content-Type", ACCEPT_HOTEL)
            .setResponseCode(200)
            .setBody("{}")
    );

    xapClient.execute(new GetLodgingListingsOperation(getLodgingListingsOperationParams));
    try {
      RecordedRequest recordedRequest = mockWebServer.takeRequest();
      // method
      Assertions.assertEquals("GET", recordedRequest.getMethod());
      // headers
      Headers headers = recordedRequest.getHeaders();
      Assertions.assertEquals(getLodgingListingsOperationParams.getPartnerTransactionId(),
          headers.get("Partner-Transaction-Id"));
      Assertions.assertEquals(ACCEPT_HOTEL, headers.get("Accept"));
      Assertions.assertEquals(MOCK_KEY, headers.get("key"));
      Assertions.assertEquals(AUTHORIZATION, headers.get("Authorization"));
      // path and query
      String path = recordedRequest.getPath();
      Assertions.assertNotNull(path);
      Assertions.assertTrue(path.startsWith("/hotels/listings"));
      String query = path.substring(path.indexOf("?") + 1);
      // hotel selection
      Assertions.assertTrue(query.contains("ecomHotelIds=123%2C456"));
      Assertions.assertTrue(query.contains("hcomHotelIds=135%2C246"));
      Assertions.assertTrue(query.contains("geoLocation=47.6062%2C-122.3321"));
      Assertions.assertTrue(query.contains("regionIds=1001"));
      Assertions.assertTrue(query.contains("locationKeyword=Space+Needle%2C+Seattle"));
      Assertions.assertTrue(query.contains("radius=10"));
      Assertions.assertTrue(query.contains("unit=km"));
      // search
      Assertions.assertTrue(
          query.contains("checkIn=" + getLodgingListingsOperationParams.getCheckIn()));
      Assertions.assertTrue(
          query.contains("checkOut=" + getLodgingListingsOperationParams.getCheckOut()));
      Assertions.assertTrue(query.contains("locale=en_US"));
      Assertions.assertTrue(query.contains("currency=USD"));
      Assertions.assertTrue(query.contains("room1.adults=1"));
      Assertions.assertTrue(query.contains("room2.adults=2"));
      Assertions.assertTrue(query.contains("room2.childAges=10%2C12"));
      Assertions.assertTrue(query.contains("room3.adults=1"));
      Assertions.assertTrue(query.contains("room3.childAges=5"));
      Assertions.assertTrue(query.contains("room4.adults=6"));
      Assertions.assertTrue(query.contains("room5.adults=2"));
      Assertions.assertTrue(query.contains("room5.childAges=3%2C4"));
      Assertions.assertTrue(query.contains("room6.adults=3"));
      Assertions.assertTrue(query.contains("room6.childAges=1%2C2%2C3%2C4%2C5"));
      Assertions.assertTrue(query.contains("room7.adults=2"));
      Assertions.assertTrue(query.contains("room7.childAges=1%2C2%2C3%2C4"));
      Assertions.assertTrue(query.contains("room8.adults=1"));
      Assertions.assertFalse(query.contains("room9"));
      Assertions.assertTrue(query.contains("source=browser"));
      Assertions.assertTrue(query.contains("travelWithPets=true"));
      // content detail
      Assertions.assertTrue(query.contains("contentDetails=high"));
      Assertions.assertTrue(query.contains("allRoomTypes=true"));
      Assertions.assertTrue(query.contains("links=WEB%2CAPI"));
      // filtering
      Assertions.assertTrue(query.contains("minStarRating=1.5"));
      Assertions.assertTrue(query.contains("maxStarRating=5.0"));
      Assertions.assertTrue(query.contains("limit=5"));
      Assertions.assertTrue(query.contains("queryText=Seattle"));
      Assertions.assertTrue(query.contains("availOnly=true"));
      Assertions.assertTrue(query.contains("smokingPreference=both"));
      Assertions.assertTrue(query.contains("rateType=all"));
      Assertions.assertTrue(query.contains("imageSizes=s"));
      Assertions.assertTrue(query.contains("thumbnailImageSize=t"));
      Assertions.assertTrue(query.contains("includedPropertyTypeIds=1%2C2"));
      Assertions.assertTrue(query.contains("excludedPropertyTypeIds=3%2C4"));
      Assertions.assertTrue(query.contains("includedInventorySourceIds=5%2C6"));
      Assertions.assertTrue(query.contains("freeCancellation=true"));
      Assertions.assertTrue(query.contains("groupedAmenities=true"));
      Assertions.assertTrue(query.contains("blockFullDepositRateplan=true"));
      // package pricing
      Assertions.assertTrue(query.contains("ratePlanType=package"));
      // sorting
      Assertions.assertTrue(query.contains("sortType=price"));
      Assertions.assertTrue(query.contains("sortOrder=asc"));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

  @Test
  public void testResponse(
      @GivenTextResource("lodging/GetLodgingListingsResponse.json") String mockedResponse) {
    GetLodgingListingsOperationParams operationParams = GetLodgingListingsOperationParams.builder()
        .partnerTransactionId(Constant.PARTNER_TRANSACTION_ID)
        .locationKeyword("Seattle")
        .checkIn(LocalDate.now().plusDays(5))
        .checkOut(LocalDate.now().plusDays(10))
        .links(Arrays.asList(GetLodgingListingsOperationParams.Links.WEB,
            GetLodgingListingsOperationParams.Links.API))
        .contentDetails(GetLodgingListingsOperationParams.ContentDetails.HIGH)
        .availOnly(true)
        .build();

    mockWebServer.enqueue(
        new MockResponse()
            .setHeader("Content-Type", ACCEPT_HOTEL)
            .setHeader("Partner-Transaction-Id", Constant.PARTNER_TRANSACTION_ID)
            .setResponseCode(200)
            .setBody(mockedResponse));

    Response<HotelListingsResponse> response = xapClient.execute(
        new GetLodgingListingsOperation(operationParams)
    );

    verifyResponse(response);
  }

  private void verifyResponse(Response<HotelListingsResponse> response) {
    Assertions.assertNotNull(response);
    Assertions.assertEquals(200, response.getStatusCode());
    Map<String, List<String>> headers = response.getHeaders();
    Assertions.assertNotNull(headers);
    Assertions.assertEquals(Constant.PARTNER_TRANSACTION_ID, headers.get("partner-transaction-id")
        .get(0));
    verifyHotelListingsResponse(response.getData());
  }

  private void verifyHotelListingsResponse(
      HotelListingsResponse hotelListingsResponse) {
    Assertions.assertNotNull(hotelListingsResponse);
    Assertions.assertNull(hotelListingsResponse.getWarnings());
    Assertions.assertNotNull(hotelListingsResponse.getCount());
    Assertions.assertTrue(hotelListingsResponse.getCount() > 0);
    Assertions.assertNotNull(hotelListingsResponse.getTotalHotelCount());
    Assertions.assertTrue(hotelListingsResponse.getTotalHotelCount() > 0);
    Assertions.assertNotNull(hotelListingsResponse.getTransactionId());
    Assertions.assertNotNull(hotelListingsResponse.getStayDates());
    Assertions.assertNotNull(hotelListingsResponse.getLengthOfStay());
    Assertions.assertTrue(hotelListingsResponse.getLengthOfStay() > 0);
    Assertions.assertNotNull(hotelListingsResponse.getNumberOfRooms());
    Assertions.assertTrue(hotelListingsResponse.getNumberOfRooms() > 0);
    Assertions.assertNotNull(hotelListingsResponse.getOccupants());
    Assertions.assertFalse(hotelListingsResponse.getOccupants().isEmpty());
    Assertions.assertNotNull(hotelListingsResponse.getHotels());
    Assertions.assertFalse(hotelListingsResponse.getHotels().isEmpty());
    hotelListingsResponse.getHotels().forEach(this::verifyHotel);
  }

  private void verifyHotel(Hotel hotel) {
    Assertions.assertNotNull(hotel);
    Assertions.assertNotNull(hotel.getId());
    Assertions.assertNotNull(hotel.getName());

    HotelPropertyType propertyType = hotel.getPropertyType();
    if (propertyType != null) {
      Assertions.assertNotNull(propertyType.getId());
      Assertions.assertNotNull(propertyType.getName());
    }

    HotelLocation location = hotel.getLocation();
    if (location != null) {
      Assertions.assertNotNull(location.getAddress());

      LocationGeoLocation geoLocation = location.getGeoLocation();
      if (geoLocation != null) {
        Assertions.assertNotNull(geoLocation.getLatitude());
        Assertions.assertNotNull(geoLocation.getLongitude());
        Assertions.assertNotNull(geoLocation.getObfuscated());
      }

      Neighborhood neighborhood = location.getNeighborhood();
      if (neighborhood != null) {
        Assertions.assertNotNull(neighborhood.getId());
        Assertions.assertNotNull(neighborhood.getName());
      }
    }

    Distance distance = hotel.getDistance();
    if (distance != null) {
      Assertions.assertNotNull(distance.getValue());
      Assertions.assertNotNull(distance.getUnit());
      Assertions.assertNotNull(distance.getDirection());
    }

    HotelLinks links = hotel.getLinks();
    Assertions.assertNotNull(links);
    HotelLinksWebSearchResult webSearchResult = links.getWebSearchResult();
    Assertions.assertNotNull(webSearchResult);
    Assertions.assertNotNull(webSearchResult.getMethod());
    Assertions.assertNotNull(webSearchResult.getHref());
    HotelLinksApiRateCalendar apiRateCalendar = links.getApiRateCalendar();
    Assertions.assertNotNull(apiRateCalendar);
    Assertions.assertNotNull(apiRateCalendar.getAccept());
    Assertions.assertNotNull(apiRateCalendar.getMethod());
    Assertions.assertNotNull(apiRateCalendar.getHref());

    List<Media> media = hotel.getMedia();
    if (media != null && !media.isEmpty()) {
      media.forEach(item -> {
        Assertions.assertNotNull(item.getType());
        Assertions.assertNotNull(item.getPropertySize());
        Assertions.assertNotNull(item.getUrl());
      });
    }

    List<HotelHotelAmenitiesInner> hotelAmenities = hotel.getHotelAmenities();
    if (hotelAmenities != null && !hotelAmenities.isEmpty()) {
      hotelAmenities.forEach(item -> {
        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getName());
      });
    }

    List<HotelRoomAmenitiesInner> roomAmenities = hotel.getRoomAmenities();
    if (roomAmenities != null && !roomAmenities.isEmpty()) {
      roomAmenities.forEach(item -> {
        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getName());
      });
    }

    List<RoomType> roomTypes = hotel.getRoomTypes();
    if (roomTypes != null && !roomTypes.isEmpty()) {
      roomTypes.forEach(this::verifyRoomType);
    }
  }

  private void verifyRoomType(RoomType roomType) {
    Assertions.assertNotNull(roomType);
    Assertions.assertNotNull(roomType.getOfferId());
    Assertions.assertNotNull(roomType.getRatePlanType());

    RoomTypePrice roomTypePrice = roomType.getPrice();
    if (roomTypePrice != null) {
      Assertions.assertNotNull(roomTypePrice.getTotalPrice());
      Assertions.assertNotNull(roomTypePrice.getAvgNightlyRate());
      Assertions.assertNotNull(roomTypePrice.getTotalPriceWithHotelFees());
    }

    RoomTypeStandalonePrice standalonePrice = roomType.getStandalonePrice();
    if (standalonePrice != null) {
      Assertions.assertNotNull(standalonePrice.getTotalPrice());
      Assertions.assertNotNull(standalonePrice.getAvgNightlyRate());
    }

    List<BedType> bedTypeOptions = roomType.getBedTypeOptions();
    if (bedTypeOptions != null && !bedTypeOptions.isEmpty()) {
      bedTypeOptions.forEach(item -> {
        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getDescription());
      });
    }

    RoomOccupancyPolicy roomOccupancyPolicy = roomType.getRoomOccupancyPolicy();
    if (roomOccupancyPolicy != null) {
      Assertions.assertNotNull(roomOccupancyPolicy.getMaxGuestCount());
      List<AgeClassRestriction> ageClassRestrictions =
          roomOccupancyPolicy.getAgeClassRestrictions();
      if (ageClassRestrictions != null && !ageClassRestrictions.isEmpty()) {
        ageClassRestrictions.forEach(item -> {
          Assertions.assertNotNull(item.getAgeClass());
          Assertions.assertNotNull(item.getAgeMinimum());
        });
      }
    }

    List<RoomTypeAmenitiesInner> amenities = roomType.getAmenities();
    if (amenities != null && !amenities.isEmpty()) {
      amenities.forEach(item -> {
        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getName());
      });
    }

    List<RoomTypeMediaInner> media = roomType.getMedia();
    if (media != null && !media.isEmpty()) {
      media.forEach(item -> {
        Assertions.assertNotNull(item.getType());
        Assertions.assertNotNull(item.getPropertySize());
        Assertions.assertNotNull(item.getUrl());
      });
    }

    RoomTypeLinks links = roomType.getLinks();
    Assertions.assertNotNull(links);
    HotelLinksWebSearchResult webSearchResult = links.getWebSearchResult();
    Assertions.assertNotNull(webSearchResult);
    Assertions.assertNotNull(webSearchResult.getMethod());
    Assertions.assertNotNull(webSearchResult.getHref());
    RoomTypeLinksWebDetails webDetails = links.getWebDetails();
    Assertions.assertNotNull(webDetails);
    Assertions.assertNotNull(webDetails.getMethod());
    Assertions.assertNotNull(webDetails.getHref());
    RoomTypeLinksApiDetails apiDetails = links.getApiDetails();
    Assertions.assertNotNull(apiDetails);
    Assertions.assertNotNull(apiDetails.getAccept());
    Assertions.assertNotNull(apiDetails.getMethod());
    Assertions.assertNotNull(apiDetails.getHref());

    List<RatePlan> ratePlans = roomType.getRatePlans();
    Assertions.assertNotNull(ratePlans);
    Assertions.assertFalse(ratePlans.isEmpty());
    ratePlans.forEach(this::verifyRatePlan);
  }

  private void verifyRatePlan(RatePlan ratePlan) {
    Assertions.assertNotNull(ratePlan);
    Assertions.assertNotNull(ratePlan.getRoomTypeId());
    Assertions.assertNotNull(ratePlan.getRatePlanId());
    Assertions.assertNotNull(ratePlan.getInventorySourceId());

    StayDates stayDates = ratePlan.getStayDates();
    Assertions.assertNotNull(stayDates);
    Assertions.assertNotNull(stayDates.getCheckInDate());
    Assertions.assertNotNull(stayDates.getCheckOutDate());

    Assertions.assertNotNull(ratePlan.getRemainingCount());

    RatePlanPrice ratePlanPrice = ratePlan.getPrice();
    if (ratePlanPrice != null) {
      Assertions.assertNotNull(ratePlanPrice.getTotalPrice());
      Assertions.assertNotNull(ratePlanPrice.getAvgNightlyRate());

      List<RatePlanPriceNightlyRatesInner> nightlyRates = ratePlanPrice.getNightlyRates();
      if (nightlyRates != null && !nightlyRates.isEmpty()) {
        nightlyRates.forEach(item -> {
          Assertions.assertNotNull(item.getStayDate());
          Assertions.assertNotNull(item.getBaseRate());
        });
      }

      List<RatePlanPriceTaxesAndFeesDetailsInner> taxesAndFeesDetails =
          ratePlanPrice.getTaxesAndFeesDetails();
      if (taxesAndFeesDetails != null && !taxesAndFeesDetails.isEmpty()) {
        taxesAndFeesDetails.forEach(item -> {
          Assertions.assertNotNull(item.getCategoryCode());
          Assertions.assertNotNull(item.getAmount());
        });
      }
    }

    RatePlanStandalonePrice standalonePrice = ratePlan.getStandalonePrice();
    if (standalonePrice != null) {
      Assertions.assertNotNull(standalonePrice.getTotalPrice());
      Assertions.assertNotNull(standalonePrice.getAvgNightlyRate());
    }

    Assertions.assertNotNull(ratePlan.getPaymentMethod());

    List<PaymentSchedule> paymentSchedule = ratePlan.getPaymentSchedule();
    if (paymentSchedule != null && !paymentSchedule.isEmpty()) {
      paymentSchedule.forEach(item -> {
        Assertions.assertNotNull(item.getDue());
        Assertions.assertNotNull(item.getPrice());
      });
    }

    CancellationPolicy cancellationPolicy = ratePlan.getCancellationPolicy();
    if (cancellationPolicy != null) {
      List<NonRefundableDateRange> nonRefundableDateRanges =
          cancellationPolicy.getNonRefundableDateRanges();
      if (nonRefundableDateRanges != null && !nonRefundableDateRanges.isEmpty()) {
        nonRefundableDateRanges.forEach(item -> {
          Assertions.assertNotNull(item.getStartDate());
          Assertions.assertNotNull(item.getEndDate());
        });
      }
    }

    List<RatePlanAmenitiesInner> amenities = ratePlan.getAmenities();
    if (amenities != null && !amenities.isEmpty()) {
      amenities.forEach(item -> {
        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getName());
      });
    }
  }
}
