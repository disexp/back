package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.PromotionDto;

import java.util.List;

public interface PromotionService {
    List<PromotionDto> getAllPromotions();

    PromotionDto createPromotion(PromotionDto promotionDto);

    PromotionDto updatePromotion(Integer id, PromotionDto promotionDto);

    PromotionDto deletePromotion(Integer id);

}
