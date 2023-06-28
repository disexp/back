package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.ShowtimeDto;
import com.upc.TuCine.TuCine.service.ShowtimeService;
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
    private ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/showtimes
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/showtimes")
    public ResponseEntity<List<ShowtimeDto>> getAllShowtimes() {
        return new ResponseEntity<>(showtimeService.getAllShowtimes(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/showtimes/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/showtimes/{id}")
    public ResponseEntity<ShowtimeDto> getShowtimeById(@PathVariable(value = "id") Integer id) {
        ShowtimeDto showtimeDto = showtimeService.getShowtimeById(id);
        if (showtimeDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(showtimeDto, HttpStatus.OK);
    }


    //URL: http://localhost:8080/api/TuCine/v1/showtimes
    //Method: POST
    @Transactional
    @PostMapping("/showtimes")
    public ResponseEntity<ShowtimeDto> createShowtime(@RequestBody ShowtimeDto showtimeDto) {
        ShowtimeDto createdShowtimeDto = showtimeService.createShowtime(showtimeDto);
        return new ResponseEntity<>(createdShowtimeDto, HttpStatus.CREATED);
    }


    //URL: http://localhost:8080/api/TuCine/v1/showtimes/{id}
    //Method: UPDATE
    @Transactional
    @PutMapping("/showtimes/{id}")
    public ResponseEntity<ShowtimeDto> updateShowtime(@PathVariable(value = "id") Integer id, @RequestBody ShowtimeDto showtimeDto) {
        ShowtimeDto updatedShowtimeDto = showtimeService.updateShowtime(id, showtimeDto);
        if (updatedShowtimeDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedShowtimeDto, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/showtimes/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/showtimes/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable(value = "id") Integer id) {
        ShowtimeDto deletedShowtimeDto = showtimeService.deleteShowtime(id);
        if (deletedShowtimeDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
