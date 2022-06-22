package com.artur.MegaMarketOpenAPI.core.endpoint;

import com.artur.MegaMarketOpenAPI.core.dto.GetNodeShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.dto.ImportRequestShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.dto.ShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.core.exception.ShopUnitNotFoundException;
import com.artur.MegaMarketOpenAPI.core.service.GetNodeShopUnitService;
import com.artur.MegaMarketOpenAPI.core.service.ShopUnitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping()
@Validated
public class ShopUnitController {

    private final ShopUnitService unitService;
    private final GetNodeShopUnitService nodeShopUnitService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShopUnitController(ShopUnitService unitService, GetNodeShopUnitService nodeShopUnitService, ModelMapper modelMapper) {
        this.unitService = unitService;
        this.nodeShopUnitService = nodeShopUnitService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/imports")
    public ResponseEntity<HttpStatus> importShopUnitDTO(@RequestBody @Valid ImportRequestShopUnitDTO importRequestShopUnitDTO) {
        unitService.importItems(importRequestShopUnitDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/nodes/{id}")
    public ResponseEntity<GetNodeShopUnitDTO> getNodeById(@PathVariable @Pattern(regexp = ShopUnitDTO.REGEXPUUID) String id) {
        ShopUnit shopUnit = unitService.getShopUnitById(id).orElse(null);
        if (shopUnit == null)
            throw new ShopUnitNotFoundException(404, "Item not found");

        return ResponseEntity.ok(nodeShopUnitService.map(shopUnit));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteNodeById(@PathVariable @Pattern(regexp = ShopUnitDTO.REGEXPUUID) String id) {
        ShopUnit shopUnit = unitService.getShopUnitById(id).orElse(null);
        if (shopUnit == null)
            throw new ShopUnitNotFoundException(404, "Item not found");

        unitService.deleteUnitById(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
