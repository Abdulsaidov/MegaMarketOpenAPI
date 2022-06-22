package com.artur.MegaMarketOpenAPI.core.util;

import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;

import java.util.List;

public class MegaMarketResponse {
    private List<ShopUnit> items;

    public List<ShopUnit> getItems() {
        return items;
    }

    public void setItems(List<ShopUnit> items) {
        this.items = items;
    }
}
