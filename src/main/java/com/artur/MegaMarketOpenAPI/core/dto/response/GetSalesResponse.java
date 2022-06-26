package com.artur.MegaMarketOpenAPI.core.dto.response;

import com.artur.MegaMarketOpenAPI.core.dto.ShopUnitDTO;

import java.util.List;

public class GetSalesResponse {
    private List<ShopUnitDTO> items;

    public List<ShopUnitDTO> getItems() {
        return items;
    }

    public void setItems(List<ShopUnitDTO> items) {
        this.items = items;
    }
}
