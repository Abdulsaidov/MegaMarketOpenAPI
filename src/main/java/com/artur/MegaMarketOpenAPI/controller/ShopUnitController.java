package com.artur.MegaMarketOpenAPI.controller;

import com.artur.MegaMarketOpenAPI.dto.ShopUnitDTO;
import com.artur.MegaMarketOpenAPI.dto.request.ImportUnitsRequest;
import com.artur.MegaMarketOpenAPI.dto.request.Units;
import com.artur.MegaMarketOpenAPI.dto.response.SalesResponse;
import com.artur.MegaMarketOpenAPI.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.exception.ShopUnitNotFoundException;
import com.artur.MegaMarketOpenAPI.service.GetNodeShopUnitService;
import com.artur.MegaMarketOpenAPI.service.ShopUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@RestController
@RequestMapping()
@Validated
public class ShopUnitController {

    private final ShopUnitService shopUnitService;
    private final GetNodeShopUnitService nodeShopUnitService;

    @Autowired
    public ShopUnitController(ShopUnitService shopUnitService, GetNodeShopUnitService nodeShopUnitService) {
        this.shopUnitService = shopUnitService;
        this.nodeShopUnitService = nodeShopUnitService;
    }

    @PostMapping("/imports")
    public ResponseEntity<HttpStatus> importShopUnitDTO(@RequestBody @Valid ImportUnitsRequest importUnitsRequest) {
        shopUnitService.importItems(importUnitsRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/nodes/{id}")
    public ResponseEntity<ShopUnitDTO> getNodeById(@PathVariable @Pattern(regexp = Units.REGEXPUUID) String id) {
        ShopUnit shopUnit = shopUnitService.getShopUnitById(id).orElse(null);
        if (shopUnit == null)
            throw new ShopUnitNotFoundException(404, "Item not found");

        return ResponseEntity.ok(nodeShopUnitService.map(shopUnit));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteNodeById(@PathVariable @Pattern(regexp = Units.REGEXPUUID) String id) {
        ShopUnit shopUnit = shopUnitService.getShopUnitById(id).orElse(null);
        if (shopUnit == null)
            throw new ShopUnitNotFoundException(404, "Item not found");

        shopUnitService.deleteUnitById(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/sales")
    public ResponseEntity<SalesResponse> sales (@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime date) {
        return ResponseEntity.ok(shopUnitService.getSalesByDate(date));
    }
}
