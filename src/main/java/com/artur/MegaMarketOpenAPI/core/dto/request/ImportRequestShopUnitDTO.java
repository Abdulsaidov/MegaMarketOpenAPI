package com.artur.MegaMarketOpenAPI.core.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;
import java.util.List;

public class ImportRequestShopUnitDTO {

    @NotEmpty
    private List<@Valid ImportShopUnitDTO> items;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime updateDate;

    public List<ImportShopUnitDTO> getItems() {
        return items;
    }

    public void setItems(List<ImportShopUnitDTO> items) {
        this.items = items;
    }

    public OffsetDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(OffsetDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
