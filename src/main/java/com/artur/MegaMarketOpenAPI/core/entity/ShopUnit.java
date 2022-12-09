package com.artur.MegaMarketOpenAPI.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shop_unit")
public class ShopUnit {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date", nullable = false)
    private OffsetDateTime date;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ShopUnitType type;

    @Column(name = "price", nullable = true)
    private Integer price;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parent_id")
    private List<ShopUnit> children = new ArrayList<>();

    public List<ShopUnit> getChildren() {
        return children;
    }

    public ShopUnit() {

    }

    public ShopUnit(String id,ShopUnitType type ) {
        this.type = type;
        this.id = id;
    }

    public ShopUnit(String id, String name,ShopUnitType type, OffsetDateTime date) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.date = date;
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

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShopUnit{" +
                "id='" + id + '\'' +
                ", date=" + date +
                '}';
    }
}
