package com.artur.MegaMarketOpenAPI.core.dto.response;

import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnitType;

import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class ShopUnitDTO {

    private String id;

    private String name;

    private String parentId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }

    public ShopUnitType getType() {
        return type;
    }

    public List<ShopUnitDTO> getChildren() {
        return children;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    private ShopUnitType type;

    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    private List<ShopUnitDTO> children;

    private String date;

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
        shopUnitDTO.date = shopUnit.getDate().format(new DateTimeFormatterBuilder().appendInstant(3).toFormatter());
        return shopUnitDTO;
    }

    public void addChild(ShopUnitDTO mappedChild) {
        children.add(mappedChild);
    }
}
