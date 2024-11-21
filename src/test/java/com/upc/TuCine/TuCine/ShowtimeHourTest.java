package com.upc.TuCine.TuCine;

import com.upc.TuCine.TuCine.dto.ShowtimeDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Business;
import com.upc.TuCine.TuCine.model.Film;
import com.upc.TuCine.TuCine.model.Promotion;
import com.upc.TuCine.TuCine.model.Showtime;
import com.upc.TuCine.TuCine.repository.BusinessRepository;
import com.upc.TuCine.TuCine.repository.FilmRepository;
import com.upc.TuCine.TuCine.repository.PromotionRepository;
import com.upc.TuCine.TuCine.repository.ShowtimeRepository;
import com.upc.TuCine.TuCine.service.impl.ShowtimeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ShowtimeHourTest {

    @Mock
    private ShowtimeRepository showtimeRepository;
    @Mock
    private FilmRepository filmRepository;
    @Mock
    private BusinessRepository businessRepository;
    @Mock
    private PromotionRepository promotionRepository;
    @InjectMocks
    private ShowtimeServiceImpl showtimeService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateShowtimeWithConflict() {
        // Crear un showtime existente
        Showtime existingShowtime = new Showtime();
        existingShowtime.setDate(LocalDate.of(2023, 10, 10));
        existingShowtime.setTime(LocalTime.of(18, 0));

        // Configurar el mock para que devuelva el showtime existente
        when(showtimeRepository.findByDateAndTime(existingShowtime.getDate(), existingShowtime.getTime()))
                .thenReturn(Optional.of(existingShowtime));

        // Crear un nuevo showtime con la misma fecha y hora
        ShowtimeDto newShowtimeDto = new ShowtimeDto();
        newShowtimeDto.setDate(LocalDate.of(2023, 10, 10));
        newShowtimeDto.setTime(LocalTime.of(18, 0));
        newShowtimeDto.setFilm(new Film());
        newShowtimeDto.setBusiness(new Business());
        newShowtimeDto.setPromotion(new Promotion());

        // Verificar que se lanza la excepción de conflicto de horario al crear el nuevo showtime
        assertThrows(ValidationException.class, () -> {
            showtimeService.createShowtime(newShowtimeDto);
        });
    }
}