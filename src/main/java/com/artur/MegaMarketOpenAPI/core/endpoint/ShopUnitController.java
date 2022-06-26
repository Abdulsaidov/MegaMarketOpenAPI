package com.artur.MegaMarketOpenAPI.core.endpoint;

import com.artur.MegaMarketOpenAPI.core.dto.GetNodeShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.dto.ImportRequestShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.dto.ShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.dto.response.GetSalesResponse;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.core.exception.ShopUnitNotFoundException;
import com.artur.MegaMarketOpenAPI.core.service.GetNodeShopUnitService;
import com.artur.MegaMarketOpenAPI.core.service.SalesShopUnitService;
import com.artur.MegaMarketOpenAPI.core.service.ShopUnitService;
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
    private final SalesShopUnitService salesShopUnitService;

    @Autowired
    public ShopUnitController(ShopUnitService shopUnitService, GetNodeShopUnitService nodeShopUnitService, SalesShopUnitService salesShopUnitService) {
        this.shopUnitService = shopUnitService;
        this.nodeShopUnitService = nodeShopUnitService;
        this.salesShopUnitService = salesShopUnitService;
    }

    @PostMapping("/imports")
    public ResponseEntity<HttpStatus> importShopUnitDTO(@RequestBody @Valid ImportRequestShopUnitDTO importRequestShopUnitDTO) {
        shopUnitService.importItems(importRequestShopUnitDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/nodes/{id}")
    public ResponseEntity<GetNodeShopUnitDTO> getNodeById(@PathVariable @Pattern(regexp = ShopUnitDTO.REGEXPUUID) String id) {
        ShopUnit shopUnit = shopUnitService.getShopUnitById(id).orElse(null);
        if (shopUnit == null)
            throw new ShopUnitNotFoundException(404, "Item not found");

        return ResponseEntity.ok(nodeShopUnitService.map(shopUnit));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteNodeById(@PathVariable @Pattern(regexp = ShopUnitDTO.REGEXPUUID) String id) {
        ShopUnit shopUnit = shopUnitService.getShopUnitById(id).orElse(null);
        if (shopUnit == null)
            throw new ShopUnitNotFoundException(404, "Item not found");

        shopUnitService.deleteUnitById(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/sales")
    public ResponseEntity<GetSalesResponse> sales (@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime date) {
        GetSalesResponse salesResponse = new GetSalesResponse();
        salesResponse.setItems(salesShopUnitService.getSalesByDate(date));
        return ResponseEntity.ok(salesResponse);
    }

//    @GetMapping("/nodes/{id}/statistic")
//    public ResponseEntity<GetSalesResponse> getStatisticById(@PathVariable @Pattern(regexp = ShopUnitDTO.REGEXPUUID) String id,
//                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateStart,
//                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateEnd){
//
//        GetSalesResponse salesResponse = new GetSalesResponse();
//        salesResponse.setItems(salesShopUnitService.getSalesByDate(date));
//        return ResponseEntity.ok(salesResponse);
//    }
}
