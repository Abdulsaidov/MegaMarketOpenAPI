package com.artur.MegaMarketOpenAPI.core.repository;

import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShopUnitRepository extends JpaRepository<ShopUnit,String>  {
   Optional<ShopUnit> findFirstByIdOrderByDate(String id);

   List<ShopUnit> findShopUnitsByDateBetween(OffsetDateTime before, OffsetDateTime query);
}
