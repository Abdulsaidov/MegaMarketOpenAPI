package com.artur.MegaMarketOpenAPI.core.service;

import com.artur.MegaMarketOpenAPI.core.dto.response.ShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnitType;
import com.artur.MegaMarketOpenAPI.core.repository.ShopUnitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesShopUnitService {

    private final ShopUnitRepository shopUnitRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SalesShopUnitService(ShopUnitRepository shopUnitRepository, ModelMapper modelMapper) {
        this.shopUnitRepository = shopUnitRepository;
        this.modelMapper = modelMapper;
    }

    public List<ShopUnitDTO> getSalesByDate(OffsetDateTime date) {

        OffsetDateTime before = date.minusHours(24);
        List<ShopUnitDTO> items = new ArrayList<>();
        List<ShopUnit> shopUnits = shopUnitRepository.findShopUnitsByDateBetween(before, date);
        ShopUnitDTO item = new ShopUnitDTO();

        for (ShopUnit s : shopUnits) {
            if (s.getType().equals(ShopUnitType.OFFER)) {
                modelMapper.map(s, item);
                item.setDate(s.getDate().format(new DateTimeFormatterBuilder()
                        .appendInstant(3).toFormatter()));
                items.add(item);
            }
        }
        return items;
    }
}
