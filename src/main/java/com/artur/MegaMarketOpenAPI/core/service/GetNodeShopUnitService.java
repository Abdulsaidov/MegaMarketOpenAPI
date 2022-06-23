package com.artur.MegaMarketOpenAPI.core.service;

import com.artur.MegaMarketOpenAPI.core.dto.GetNodeShopUnitDTO;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnit;
import com.artur.MegaMarketOpenAPI.core.entity.ShopUnitType;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GetNodeShopUnitService {
    private class TraverseState {
        public GetNodeShopUnitDTO getNodeShopUnitDTO;
        public int offersCount;
        public int offersTotalPrice;

        public TraverseState(GetNodeShopUnitDTO getNodeShopUnitDTO, int offersCount, int totalOffersPrice) {
            this.getNodeShopUnitDTO = getNodeShopUnitDTO;
            this.offersCount = offersCount;
            this.offersTotalPrice = totalOffersPrice;
        }
    }

    private class ShopUnitMapper {
        private TraverseState traverse(ShopUnit shopUnit) {
            if (shopUnit.getType().equals(ShopUnitType.OFFER)) {
                return new TraverseState(GetNodeShopUnitDTO.fromOffer(shopUnit), 1, shopUnit.getPrice());
            } else {
                GetNodeShopUnitDTO currentTraversingCategory = GetNodeShopUnitDTO.fromCategory(shopUnit);
                int offersCount = 0;
                int offersTotalPrice = 0;

                List<ShopUnit> children = shopUnit.getChildren();
                for (ShopUnit child : children) {
                    TraverseState childTraverseResult = traverse(child);

                    offersTotalPrice += childTraverseResult.offersTotalPrice;
                    offersCount += childTraverseResult.offersCount;

                    currentTraversingCategory.addChild(childTraverseResult.getNodeShopUnitDTO);
                }
                if (offersCount != 0) {
                    currentTraversingCategory.setPrice(offersTotalPrice / offersCount);
                }

                return new TraverseState(currentTraversingCategory, offersCount, offersTotalPrice);
            }
        }

        public GetNodeShopUnitDTO map(ShopUnit shopUnit) {
            TraverseState state = this.traverse(shopUnit);

            return state.getNodeShopUnitDTO;
        }
    }

    public GetNodeShopUnitDTO map(ShopUnit shopUnit) {
        ShopUnitMapper mapper = new ShopUnitMapper();

        return mapper.map(shopUnit);
    }
}
