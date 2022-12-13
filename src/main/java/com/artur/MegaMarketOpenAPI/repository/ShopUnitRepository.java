package com.artur.MegaMarketOpenAPI.repository;

import com.artur.MegaMarketOpenAPI.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.entity.ShopUnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShopUnitRepository extends JpaRepository<ShopUnit,String>  {
   Optional<ShopUnit> findById(String id);

   List<ShopUnit> findShopUnitsByDateBetweenAndType(OffsetDateTime before, OffsetDateTime query, ShopUnitType type);
}
