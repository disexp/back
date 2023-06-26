package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.Showtime;
import com.upc.TuCine.TuCine.repository.BusinessRepository;
import com.upc.TuCine.TuCine.repository.FilmRepository;
import com.upc.TuCine.TuCine.repository.PromotionRepository;
import com.upc.TuCine.TuCine.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")
public class ShowtimeController {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    public ShowtimeController(
            ShowtimeRepository showtimeRepository,
            FilmRepository filmRepository,
            BusinessRepository businessRepository,
            PromotionRepository promotionRepository
    ) {
        this.showtimeRepository = showtimeRepository;
        this.filmRepository = filmRepository;
        this.businessRepository = businessRepository;
        this.promotionRepository = promotionRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/showtimes
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/showtimes")
    public ResponseEntity<List<Showtime>> getAllShowtimes() {
        return new ResponseEntity<List<Showtime>>(showtimeRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/showtimes
    //Method: POST
    @Transactional
    @PostMapping("/showtimes")
    public ResponseEntity<Showtime> createShowtime(@RequestBody Showtime showtime){
        validateShowtime(showtime);
        existsFilmById(showtime.getFilm_id().getId());
        existsBusinessById(showtime.getBusiness_id().getId());
        //existsPromotionById(showtime.getPromotion_id().getId());
        return new ResponseEntity<>(showtimeRepository.save(showtime), HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/TuCine/v1/showtimes/{id}
    //Method: UPDATE
    @Transactional
    @PutMapping("/showtimes/{id}")
    public ResponseEntity<Showtime> updateShowtime(@PathVariable Integer id, @RequestBody Showtime showtime) {
        Showtime showtimeDB = showtimeRepository.findById(id).orElse(null);
        if (showtimeDB == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        showtimeDB.setDate(showtime.getDate());
        showtimeDB.setTime(showtime.getTime());
        showtimeDB.setPrice(showtime.getPrice());
        showtimeDB.setFilm_id(showtime.getFilm_id());
        showtimeDB.setBusiness_id(showtime.getBusiness_id());
        showtimeDB.setPromotion_id(showtime.getPromotion_id());
        return new ResponseEntity<>(showtimeRepository.save(showtimeDB), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/showtimes/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/showtimes/{id}")
    public ResponseEntity<Showtime> deleteShowtime(@PathVariable Integer id) {
        Showtime showtimeDB = showtimeRepository.findById(id).orElse(null);
        if (showtimeDB == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        showtimeRepository.delete(showtimeDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private void validateShowtime(Showtime showtime) {
        if (showtime.getDate() == null) {
            throw new RuntimeException("La fecha es obligatoria");
        }
        if (showtime.getTime() == null ) {
            throw new RuntimeException("La hora es obligatoria");
        }
        if (showtime.getPrice() == null ) {
            throw new RuntimeException("El precio es obligatorio");
        }

    }

    private void existsFilmById(Integer id) {
        if (!filmRepository.existsById(id)) {
            throw new RuntimeException("La película no existe");
        }
    }

    private void existsBusinessById(Integer id) {
        if (!businessRepository.existsById(id)) {
            throw new RuntimeException("El negocio no existe");
        }
    }

    private void existsPromotionById(Integer id) {
        if (!promotionRepository.existsById(id)) {
            throw new RuntimeException("La promoción no existe");
        }
    }
}
