package com.artur.MegaMarketOpenAPI.core.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

public class ShopUnitId implements Serializable {
    private String id;
    private OffsetDateTime date;

    public ShopUnitId(String id, OffsetDateTime date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopUnitId)) return false;
        ShopUnitId that = (ShopUnitId) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate());
    }
}
