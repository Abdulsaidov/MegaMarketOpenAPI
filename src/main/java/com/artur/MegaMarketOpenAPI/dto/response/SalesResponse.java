package com.artur.MegaMarketOpenAPI.dto.response;

import com.artur.MegaMarketOpenAPI.dto.ShopUnitDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SalesResponse {
    private List<ShopUnitDTO> items;
}
