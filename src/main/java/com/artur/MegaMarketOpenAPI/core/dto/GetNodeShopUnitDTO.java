package com.artur.MegaMarketOpenAPI.core.dto;

import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnitType;

import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class GetNodeShopUnitDTO {

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

    public List<GetNodeShopUnitDTO> getChildren() {
        return children;
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

    private List<GetNodeShopUnitDTO> children;

    private String date;

    public static GetNodeShopUnitDTO fromOffer(ShopUnit shopUnit) {
        GetNodeShopUnitDTO getNodeShopUnitDTO = mapShopUnit(shopUnit);
        getNodeShopUnitDTO.price = shopUnit.getPrice();
        getNodeShopUnitDTO.children = null;

        return getNodeShopUnitDTO;
    }

    public static GetNodeShopUnitDTO fromCategory(ShopUnit shopUnit) {
        GetNodeShopUnitDTO getNodeShopUnitDTO = mapShopUnit(shopUnit);
        getNodeShopUnitDTO.children = new ArrayList<>();
        getNodeShopUnitDTO.price = null;
        return getNodeShopUnitDTO;
    }

    private static GetNodeShopUnitDTO mapShopUnit(ShopUnit shopUnit) {
        GetNodeShopUnitDTO getNodeShopUnitDTO = new GetNodeShopUnitDTO();
        getNodeShopUnitDTO.id = shopUnit.getId();
        getNodeShopUnitDTO.name = shopUnit.getName();
        getNodeShopUnitDTO.parentId = shopUnit.getParentId();
        getNodeShopUnitDTO.type = shopUnit.getType();
        getNodeShopUnitDTO.date = shopUnit.getDate().format(new DateTimeFormatterBuilder().appendInstant(3).toFormatter());
        return getNodeShopUnitDTO;
    }

    public void addChild(GetNodeShopUnitDTO mappedChild) {
        children.add(mappedChild);
    }
}
