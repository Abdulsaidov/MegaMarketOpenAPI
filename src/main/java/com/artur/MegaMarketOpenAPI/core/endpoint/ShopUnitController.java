package com.artur.MegaMarketOpenAPI.core.endpoint;

import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.core.service.ShopUnitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class ShopUnitController {

    private final ShopUnitService unitService;

    @Autowired
    public ShopUnitController(ShopUnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping("/nodes/{id}")
    public ResponseEntity<ShopUnit> getNodeById(@PathVariable String id) {
        ShopUnit shopUnit = unitService.getShopUnitById(id);
        return ResponseEntity.ok(shopUnit);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteNodeById(@PathVariable String id) {
        unitService.deleteUnitById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
