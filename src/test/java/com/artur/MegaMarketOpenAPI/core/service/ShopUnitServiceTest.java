package com.artur.MegaMarketOpenAPI.core.service;

import com.artur.MegaMarketOpenAPI.core.dto.request.ImportRequestShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.dto.request.ImportShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnitType;
import com.artur.MegaMarketOpenAPI.core.exception.ShopUnitValidationException;
import com.artur.MegaMarketOpenAPI.core.repository.ShopUnitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShopUnitServiceTest {
    private static final String UUID = "550cb8c6-1554-4ffb-aaa7-f3fbefc8fa22";
    @Autowired
    ShopUnitRepository repository;
    @Autowired
    ShopUnitService service;
    ShopUnit unit;

    @BeforeEach
    void setUp() {
        unit = new ShopUnit(UUID, "name", ShopUnitType.CATEGORY, OffsetDateTime.now());
        repository.save(unit);
    }


    @Test
    void getShopUnitById() {
        assertThat(service.getShopUnitById(UUID)).isNotEmpty();
        assertThat(service.getShopUnitById(UUID).orElse(new ShopUnit()).getId()).isEqualTo(UUID);
    }

    @Test
    void deleteUnitById() {
        assertDoesNotThrow(() -> service.deleteUnitById("id"));
    }

    @ParameterizedTest
    @MethodSource("generateTestData")
    void importItems(ImportRequestShopUnitDTO unitDTO) {
        service.importItems(unitDTO);
        assertThat(service.getShopUnitById("550cb8c6-1554-4ffb-aaa7-f3fbefc8fa11")).isNotEmpty();
        assertThat(service.getShopUnitById("550cb8c6-1554-4ffb-aaa7-f3fbefc8fa11").orElse(new ShopUnit()).getName()).isEqualTo("cost");
    }
    @ParameterizedTest
    @MethodSource("generateWrongTestData")
    void importNotValidItems(ImportRequestShopUnitDTO unitDTO) {
        assertThrows(RuntimeException.class,()->service.importItems(unitDTO));
    }

    private static Stream<ImportRequestShopUnitDTO> generateTestData() {

        return Stream.of(
                new ImportRequestShopUnitDTO(
                        List.of(new ImportShopUnitDTO("550cb8c6-1554-4ffb-aaa7-f3fbefc8fa11",
                                "cost",
                                "550cb8c6-1554-4ffb-aaa7-f3fbefc8fa22",
                                ShopUnitType.OFFER,
                                90)),
                        OffsetDateTime.now()));
    }

    private static Stream<ImportRequestShopUnitDTO> generateWrongTestData() {

        return Stream.of(
                new ImportRequestShopUnitDTO(
                        List.of(new ImportShopUnitDTO("550cb8c6-1554-4ffb-aaa7-f3fbefc8fa11",
                                "cost",
                                "550cb8c6-1554-4ffb-aaa7-f3fbefc8fa",
                                ShopUnitType.OFFER,
                                90)),
                        OffsetDateTime.now()),
                new ImportRequestShopUnitDTO(
                        List.of(new ImportShopUnitDTO(null,
                                "cost",
                                "550cb8c6-1554-4ffb-aaa7-f3fbefc8fa22",
                                ShopUnitType.OFFER,
                                90)),
                        OffsetDateTime.now()));
    }
}