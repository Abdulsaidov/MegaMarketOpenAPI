package com.artur.MegaMarketOpenAPI.service;

import com.artur.MegaMarketOpenAPI.dto.ShopUnitDTO;
import com.artur.MegaMarketOpenAPI.dto.request.ImportUnitsRequest;
import com.artur.MegaMarketOpenAPI.dto.request.Units;
import com.artur.MegaMarketOpenAPI.dto.response.SalesResponse;
import com.artur.MegaMarketOpenAPI.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.entity.ShopUnitType;
import com.artur.MegaMarketOpenAPI.exception.ShopUnitValidationException;
import com.artur.MegaMarketOpenAPI.repository.ShopUnitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ShopUnitService {
    public final ShopUnitRepository unitRepository;
    public final ModelMapper modelMapper;

    @Autowired
    public ShopUnitService(ShopUnitRepository unitRepository, ModelMapper modelMapper) {
        this.unitRepository = unitRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<ShopUnit> getShopUnitById(String id) {
        return unitRepository.findById(id);
    }

    @Transactional
    public void deleteUnitById(String id) {
        unitRepository.deleteById(id);
    }


    @Transactional
    public void importItems(ImportUnitsRequest importUnitsRequest) {

        for (Units units : importUnitsRequest.getItems()) {
            ShopUnit shopUnit = getShopUnitById(units.getId()).orElse(new ShopUnit(units.getId(), units.getType()));

            if (!shopUnit.getType().equals(units.getType())) {
                throw new ShopUnitValidationException(400, "Validation failed");
            }
            if (!isValidParent(units.getParentId())) {
                throw new ShopUnitValidationException(400, "Validation failed");
            }

            shopUnit.setName(units.getName());
            shopUnit.setPrice(units.getPrice());
            shopUnit.setParentId(units.getParentId());
            shopUnit.setDate(importUnitsRequest.getUpdateDate());
            unitRepository.save(shopUnit);

            String parentId = shopUnit.getParentId();
            while (parentId != null) {
                ShopUnit parent = unitRepository.getReferenceById(parentId);
                parent.setDate(importUnitsRequest.getUpdateDate());
                parentId = parent.getParentId();
            }
        }
    }

    private boolean isValidParent(String parentId) {
        if (parentId == null) {
            return true;
        }
        if (unitRepository.existsById(parentId)) {
            return !Objects.equals(getShopUnitById(parentId).orElse(new ShopUnit()).getType(), ShopUnitType.OFFER);
        } else {
            return false;
        }
    }

    public SalesResponse getSalesByDate(OffsetDateTime date) {
        List<ShopUnitDTO> items = unitRepository.findShopUnitsByDateBetweenAndType(date.minusHours(24), date, ShopUnitType.OFFER).stream()
                .map(shopUnit -> modelMapper.map(shopUnit, ShopUnitDTO.class))
                .collect(Collectors.toList());
        return new SalesResponse(items);
    }
}
