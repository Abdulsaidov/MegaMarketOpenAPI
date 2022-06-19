package com.artur.MegaMarketOpenAPI.core.service;

import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.core.repository.ShopUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopUnitService {
    public final ShopUnitRepository unitRepository;

    @Autowired
    public ShopUnitService(ShopUnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public ShopUnit getShopUnitById(String id) {
        return unitRepository.findById(id).get();
    }

    public void deleteUnitById(String id) {
        unitRepository.deleteById(id);
    }

}
