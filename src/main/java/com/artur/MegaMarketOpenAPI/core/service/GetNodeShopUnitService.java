package com.artur.MegaMarketOpenAPI.core.service;

import com.artur.MegaMarketOpenAPI.core.dto.ShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnitType;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GetNodeShopUnitService {
    private class TraverseState {
        public ShopUnitDTO shopUnitDTO;
        public int offersCount;
        public int offersTotalPrice;

        public TraverseState(ShopUnitDTO shopUnitDTO, int offersCount, int totalOffersPrice) {
            this.shopUnitDTO = shopUnitDTO;
            this.offersCount = offersCount;
            this.offersTotalPrice = totalOffersPrice;
        }
    }

    private class ShopUnitMapper {
        private TraverseState traverse(ShopUnit shopUnit) {
            if (shopUnit.getType().equals(ShopUnitType.OFFER)) {
                return new TraverseState(ShopUnitDTO.fromOffer(shopUnit), 1, shopUnit.getPrice());
            } else {
                ShopUnitDTO currentTraversingCategory = ShopUnitDTO.fromCategory(shopUnit);
                int offersCount = 0;
                int offersTotalPrice = 0;

                List<ShopUnit> children = shopUnit.getChildren();
                for (ShopUnit child : children) {
                    TraverseState childTraverseResult = traverse(child);

                    offersTotalPrice += childTraverseResult.offersTotalPrice;
                    offersCount += childTraverseResult.offersCount;

                    currentTraversingCategory.addChild(childTraverseResult.shopUnitDTO);
                }
                if (offersCount != 0) {
                    currentTraversingCategory.setPrice(offersTotalPrice / offersCount);
                }

                return new TraverseState(currentTraversingCategory, offersCount, offersTotalPrice);
            }
        }

        public ShopUnitDTO map(ShopUnit shopUnit) {
            TraverseState state = this.traverse(shopUnit);

            return state.shopUnitDTO;
        }
    }

    public ShopUnitDTO map(ShopUnit shopUnit) {
        ShopUnitMapper mapper = new ShopUnitMapper();

        return mapper.map(shopUnit);
    }
}
