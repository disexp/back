package com.upc.TuCine.TuCine;

import com.upc.TuCine.TuCine.model.*;
import com.upc.TuCine.TuCine.service.impl.PromotionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class PromotionIsValidTest {

    @InjectMocks
    private PromotionServiceImpl promotionService;

    private Film film;
    private Showtime showtime;
    private Promotion promotion;

    @BeforeEach
    public void setUp() {
        film = new Film();
        film.setId(1);

        promotion = new Promotion();
        promotion.setDiscount(0.15f);
        promotion.setStartDate(LocalDate.now());
        promotion.setEndDate(LocalDate.now().plusDays(5));

        showtime = new Showtime();
        showtime.setFilm(film);
        showtime.setPromotion(promotion);
        showtime.setDate(LocalDate.now());
    }

    @Test
    public void testPromotionExistsOnlyIfFilmAvailable() {
        boolean isPromotionValid = promotionService.isPromotionValid(showtime);
        Assertions.assertTrue(isPromotionValid);

        showtime.setDate(LocalDate.now().plusDays(16));
        isPromotionValid = promotionService.isPromotionValid(showtime);
        Assertions.assertFalse(isPromotionValid);
    }

}