package com.artur.MegaMarketOpenAPI.dto;

import com.artur.MegaMarketOpenAPI.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.entity.ShopUnitType;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ShopUnitDTO {

    private String id;
    private String name;
    private String parentId;
    private ShopUnitType type;
    private Integer price;
    private String date;

    public void setDate(OffsetDateTime date) {

        this.date = date.format(new DateTimeFormatterBuilder()
                .appendInstant(3).toFormatter());
    }
    private List<ShopUnitDTO> children;


    public static ShopUnitDTO fromOffer(ShopUnit shopUnit) {
        ShopUnitDTO shopUnitDTO = mapShopUnit(shopUnit);
        shopUnitDTO.price = shopUnit.getPrice();
        shopUnitDTO.children = null;

        return shopUnitDTO;
    }

    public static ShopUnitDTO fromCategory(ShopUnit shopUnit) {
        ShopUnitDTO shopUnitDTO = mapShopUnit(shopUnit);
        shopUnitDTO.children = new ArrayList<>();
        shopUnitDTO.price = null;
        return shopUnitDTO;
    }

    private static ShopUnitDTO mapShopUnit(ShopUnit shopUnit) {
        ShopUnitDTO shopUnitDTO = new ShopUnitDTO();
        shopUnitDTO.id = shopUnit.getId();
        shopUnitDTO.name = shopUnit.getName();
        shopUnitDTO.parentId = shopUnit.getParentId();
        shopUnitDTO.type = shopUnit.getType();
        shopUnitDTO.setDate(shopUnit.getDate());
        return shopUnitDTO;
    }

    public void addChild(ShopUnitDTO mappedChild) {
        children.add(mappedChild);
    }
}
