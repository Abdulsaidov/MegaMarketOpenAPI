package com.artur.MegaMarketOpenAPI.core.dto;

import com.artur.MegaMarketOpenAPI.core.entity.ShopUnitType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class ShopUnitDTO {
    @Id
    @Column(name = "id", nullable = false)
    @Pattern(message = "Введите действительный UUID",
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")
    private String id;

    @Column(name = "name", length = 127, nullable = false)
    private String name;

    @Column(name = "parentId")
    @Pattern(message = "Введите действительный UUID",
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")
    private String parentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ShopUnitType type;

    @Column(name = "price")
    @Min(message = "Цена не может быть меньше 0", value = 0)
    private int price;

    public ShopUnitDTO(String id, String name, String parentId, ShopUnitType type, int price) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.type = type;
        this.price = price;
    }

    public ShopUnitDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public ShopUnitType getType() {
        return type;
    }

    public void setType(ShopUnitType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
