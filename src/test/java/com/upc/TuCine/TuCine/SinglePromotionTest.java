// SinglePromotionTest.java
package com.upc.TuCine.TuCine;

import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Promotion;
import com.upc.TuCine.TuCine.model.Showtime;
import com.upc.TuCine.TuCine.service.impl.PromotionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SinglePromotionTest {

    @InjectMocks
    private PromotionServiceImpl promotionService;

    private Showtime showtime;
    private Promotion promo1;
    private Promotion promo2;

    @BeforeEach
    public void setUp() {
        showtime = new Showtime();

        promo1 = new Promotion();
        promo1.setId(1);

        promo2 = new Promotion();
        promo2.setId(2);
    }

    @Test
    public void testSinglePromotionPerShowtime() {
        // Assign promotion1 to showtime
        showtime.setPromotion(promo1);
        // Verify that the promotion assigned to showtime is promotion1
        assertNotNull(showtime.getPromotion());
        assertEquals(promo1, showtime.getPromotion());

        // Verify that the showtime has a single promotion
        assertTrue(promotionService.hasSinglePromotion(showtime));

        // Attempt to assign promotion2 to showtime and verify that it throws an exception
        assertThrows(ValidationException.class, () -> {
            showtime.setPromotion(promo2);
            promotionService.hasSinglePromotion(showtime);
        });
    }
}