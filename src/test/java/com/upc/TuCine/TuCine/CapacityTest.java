// CapacityTest.java
package com.upc.TuCine.TuCine;

import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Showtime;
import com.upc.TuCine.TuCine.service.impl.PromotionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CapacityTest {

    @InjectMocks
    private PromotionServiceImpl promotionService;

    private Showtime showtime;

    @BeforeEach
    public void setUp() {
        showtime = new Showtime();
        showtime.setMaxCapacity(100);
        showtime.setCurrentCapacity(0);
    }

    @Test
    public void testExceedingCapacity() {
        // Add tickets within capacity
        showtime.addTickets(101);
        assertEquals(100, showtime.getCurrentCapacity());

        // Attempt to add tickets exceeding capacity and verify that it throws an exception
        assertThrows(ValidationException.class, () -> {
            promotionService.checkCapacity(showtime, 1);
            showtime.addTickets(1);
        });
    }
}