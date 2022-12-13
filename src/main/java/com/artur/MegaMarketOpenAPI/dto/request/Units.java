package com.artur.MegaMarketOpenAPI.dto.request;

import com.artur.MegaMarketOpenAPI.entity.ShopUnitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Units {
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
}
