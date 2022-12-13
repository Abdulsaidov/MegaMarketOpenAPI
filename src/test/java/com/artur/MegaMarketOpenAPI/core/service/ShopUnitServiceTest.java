package com.artur.MegaMarketOpenAPI.core.service;

import com.artur.MegaMarketOpenAPI.dto.response.SalesResponse;
import com.artur.MegaMarketOpenAPI.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.repository.ShopUnitRepository;
import com.artur.MegaMarketOpenAPI.service.ShopUnitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static com.artur.MegaMarketOpenAPI.core.service.TestDataGenerator.*;

@SpringBootTest
class ShopUnitServiceTest {
    @Autowired
    ShopUnitRepository repository;
    @Autowired
    ShopUnitService service;

    @BeforeEach
    void setUp() {
        repository.saveAll(generateShopUnits());
    }


    @ParameterizedTest
    @ValueSource(strings = {"550cb8c6-1554-4ffb-aaa7-f3fbefc8fa21", "550cb8c6-1554-4ffb-aaa7-f3fbefc8fa22"})
    void getShopUnitById(String UUID) {
        assertThat(service.getShopUnitById(UUID)).isNotEmpty();
        assertThat(service.getShopUnitById(UUID).orElse(new ShopUnit()).getId()).isEqualTo(UUID);
    }

    @ParameterizedTest
    @ValueSource(strings = {"550cb8c6-1554-4ffb-aaa7-f3fbefc8fa21", "550cb8c6-1554-4ffb-aaa7-f3fbefc8fa22"})
    void deleteUnitById(String UUID) {
        assertDoesNotThrow(() -> service.deleteUnitById(UUID));
    }

    @Test
    void importItems() {
        service.importItems(generateTestData());
        repository.findAll().forEach(System.out::println);
        assertThat(repository.findAll().size()).isEqualTo(6);
        assertThat(service.getShopUnitById("550cb8c6-1554-4ffb-aaa7-f3fbefc8fa26")).isNotEmpty();
        assertThat(service.getShopUnitById("550cb8c6-1554-4ffb-aaa7-f3fbefc8fa26").orElse(new ShopUnit()).getName()).isEqualTo("iphoneX");
    }

    @Test
    void importNotValidItems() {
        generateWrongTestData()
                .forEach(unitDTO -> assertThrows(RuntimeException.class, () -> service.importItems(unitDTO)));

    }

    @Test
    void getSalesByDate() {
        SalesResponse sales = service.getSalesByDate(getDateTime().plusHours(5));
        assertThat(sales.getItems()).isNotEmpty();
        assertThat(sales.getItems().size()).isEqualTo(3);
    }
}