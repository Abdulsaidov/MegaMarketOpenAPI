package com.artur.MegaMarketOpenAPI.core.dto.request;

import com.artur.MegaMarketOpenAPI.core.entity.ShopUnitType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class ImportShopUnitDTO {
    public static final String REGEXPUUID = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    @Pattern(message = "Введите действительный UUID",
            regexp = REGEXPUUID )
    @NotNull
    private String id;

    @NotEmpty
    private String name;

    @Pattern(message = "Введите действительный UUID",
            regexp = REGEXPUUID)
    private String parentId;

    @Enumerated(EnumType.STRING)
    private ShopUnitType type;

    private Integer price;

    public ImportShopUnitDTO() {
    }

    public ImportShopUnitDTO(String id, String name, String parentId, ShopUnitType type, Integer price) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.type = type;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }

    public ShopUnitType getType() {
        return type;
    }

    public Integer getPrice() {
        return price;
    }
}
