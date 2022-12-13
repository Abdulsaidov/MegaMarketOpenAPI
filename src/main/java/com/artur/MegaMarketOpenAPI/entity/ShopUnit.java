package com.artur.MegaMarketOpenAPI.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Getter
@Setter
@NoArgsConstructor
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

    public ShopUnit(String id,ShopUnitType type ) {
        this.type = type;
        this.id = id;
    }

    public ShopUnit(String id, String name,String parentId, ShopUnitType type, OffsetDateTime date) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.type = type;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShopUnit{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", type=" + type +
                '}';
    }
}
