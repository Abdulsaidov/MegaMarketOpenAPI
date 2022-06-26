package com.artur.MegaMarketOpenAPI.core.dto.response;

import com.artur.MegaMarketOpenAPI.core.dto.ImportShopUnitDTO;

public class SalesImportShopUnitDTO extends ImportShopUnitDTO {
   private String date;

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }
}
