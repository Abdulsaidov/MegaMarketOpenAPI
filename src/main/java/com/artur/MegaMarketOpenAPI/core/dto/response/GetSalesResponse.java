package com.artur.MegaMarketOpenAPI.core.dto.response;

import com.artur.MegaMarketOpenAPI.core.dto.ShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;

import java.util.List;

public class GetSalesResponse {
    private List<SalesShopUnitDTO> items;

    public List<SalesShopUnitDTO> getItems() {
        return items;
    }

    public void setItems(List<SalesShopUnitDTO> items) {
        this.items = items;
    }
}
