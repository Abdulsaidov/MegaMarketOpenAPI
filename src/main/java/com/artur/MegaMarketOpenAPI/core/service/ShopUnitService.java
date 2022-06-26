package com.artur.MegaMarketOpenAPI.core.service;

import com.artur.MegaMarketOpenAPI.core.dto.request.ImportRequestShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.dto.request.ImportShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnitType;
import com.artur.MegaMarketOpenAPI.core.exception.ShopUnitValidationException;
import com.artur.MegaMarketOpenAPI.core.repository.ShopUnitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

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
    public void importItems(ImportRequestShopUnitDTO importRequestShopUnitDTO) {

        for (ImportShopUnitDTO item : importRequestShopUnitDTO.getItems()) {
            ShopUnit shopUnit = getShopUnitById(item.getId()).orElse(new ShopUnit(item.getId(), item.getType()));

            if (!shopUnit.getType().equals(item.getType())) {
                throw new ShopUnitValidationException(400,"Validation failed");
            }
            if (!isValidParent(item.getParentId())) {
                throw new ShopUnitValidationException(400,"Validation failed");
            }

            shopUnit.setName(item.getName());
            shopUnit.setPrice(item.getPrice());
            shopUnit.setParentId(item.getParentId());
            shopUnit.setDate(importRequestShopUnitDTO.getUpdateDate());
            unitRepository.save(shopUnit);

            String parentId = shopUnit.getParentId();
            while(parentId != null) {
                ShopUnit parent = unitRepository.getReferenceById(parentId);
                parent.setDate(importRequestShopUnitDTO.getUpdateDate());
                parentId = parent.getParentId();
            }

        }
    }

    private boolean isValidParent(String parentId) {
        if (parentId != null) {
            if (unitRepository.existsById(parentId)) {
                return !Objects.equals(getShopUnitById(parentId).get().getType(), ShopUnitType.OFFER);
            } else {
                return false;
            }
        }
        return true;
    }
}
