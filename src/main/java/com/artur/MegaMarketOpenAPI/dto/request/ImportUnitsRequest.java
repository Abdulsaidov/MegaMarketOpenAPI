package com.artur.MegaMarketOpenAPI.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;
import java.util.List;

public class ImportUnitsRequest {

    @NotEmpty
    private List<@Valid Units> units;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime updateDate;

    public List<Units> getItems() {
        return units;
    }

    public void setItems(List<Units> units) {
        this.units = units;
    }

    public OffsetDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(OffsetDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public ImportUnitsRequest() {
    }

    public ImportUnitsRequest(List<@Valid Units> units, OffsetDateTime updateDate) {
        this.units = units;
        this.updateDate = updateDate;
    }
}
