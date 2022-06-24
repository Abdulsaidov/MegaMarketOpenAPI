package com.artur.MegaMarketOpenAPI.core.dto.response;

import com.artur.MegaMarketOpenAPI.core.dto.ShopUnitDTO;

public class SalesShopUnitDTO extends ShopUnitDTO {
   private String date;

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }
}
