package com.artur.MegaMarketOpenAPI.core.service;

import com.artur.MegaMarketOpenAPI.dto.request.ImportUnitsRequest;
import com.artur.MegaMarketOpenAPI.dto.request.Units;
import com.artur.MegaMarketOpenAPI.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.entity.ShopUnitType;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDataGenerator {
    private static final String BASE_UUID = "550cb8c6-1554-4ffb-aaa7-f3fbefc8fa2";

    static OffsetDateTime getDateTime() {
        Clock clock = Clock.systemUTC();
        return OffsetDateTime.ofInstant(Instant.now(clock), ZoneId.of("Europe/Moscow"));
    }

    static List<ShopUnit> generateShopUnits() {
        return Stream.of(
                new ShopUnit(BASE_UUID + 4, "phones", null, ShopUnitType.CATEGORY, getDateTime().plusHours(3)),
                new ShopUnit(BASE_UUID + 5, "tv", null, ShopUnitType.CATEGORY, getDateTime().plusHours(4)),
                new ShopUnit(BASE_UUID + 1, "iphone", BASE_UUID + 4, ShopUnitType.OFFER, getDateTime()),
                new ShopUnit(BASE_UUID + 2, "samsungTV", BASE_UUID + 5, ShopUnitType.OFFER, getDateTime().plusHours(1)),
                new ShopUnit(BASE_UUID + 3, "xiaomi", BASE_UUID + 4, ShopUnitType.OFFER, getDateTime().plusHours(2))
        ).collect(Collectors.toList());
    }

    static ImportUnitsRequest generateTestData() {

        return new ImportUnitsRequest(
                List.of(new Units("550cb8c6-1554-4ffb-aaa7-f3fbefc8fa24",
                        "phones",
                        null,
                        ShopUnitType.CATEGORY,
                        null
                        ),
                        new Units("550cb8c6-1554-4ffb-aaa7-f3fbefc8fa26",
                                "iphoneX",
                                "550cb8c6-1554-4ffb-aaa7-f3fbefc8fa24",
                                ShopUnitType.OFFER,
                                90)),
                getDateTime());
    }

    static List<ImportUnitsRequest> generateWrongTestData() {

        return Stream.of(
                new ImportUnitsRequest(
                        List.of(new Units("550cb8c6-1554-4ffb-aaa7-f3fbefc8fa11",
                                "cost",
                                "550cb8c6-1554-4ffb-aaa7-f3fbefc8fa",
                                ShopUnitType.OFFER,
                                90)),
                        OffsetDateTime.now()),
                new ImportUnitsRequest(
                        List.of(new Units(null,
                                "cost",
                                "550cb8c6-1554-4ffb-aaa7-f3fbefc8fa22",
                                ShopUnitType.OFFER,
                                90)),
                        OffsetDateTime.now())).collect(Collectors.toList());
    }
}
