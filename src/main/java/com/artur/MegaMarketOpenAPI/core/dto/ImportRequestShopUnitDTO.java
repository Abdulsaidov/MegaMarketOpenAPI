package com.artur.MegaMarketOpenAPI.core.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;
import java.util.List;

public class ImportRequestShopUnitDTO {

    @NotEmpty
    private List<@Valid ShopUnitDTO> items;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime updateDate;

    public List<ShopUnitDTO> getItems() {
        return items;
    }

    public void setItems(List<ShopUnitDTO> items) {
        this.items = items;
    }

    public OffsetDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(OffsetDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
