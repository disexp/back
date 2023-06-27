package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.PromotionDto;
import com.upc.TuCine.TuCine.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }
    //URL: http://localhost:8080/api/TuCine/v1/promotions
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/promotions")
    public ResponseEntity<List<PromotionDto>> getAllPromotions() {
        return new ResponseEntity<List<PromotionDto>>(promotionService.getAllPromotions(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/promotions
    //Method: POST
    @Transactional
    @PostMapping("/promotions")
    public ResponseEntity<PromotionDto> createPromotion(@RequestBody PromotionDto promotionDto){
        return new ResponseEntity<>(promotionService.createPromotion(promotionDto), HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/TuCine/v1/promotions/{id}
    //Method: PUT
    @Transactional
    @PutMapping("/promotions/{id}")
    public ResponseEntity<PromotionDto> updatePromotion(@PathVariable Integer id, @RequestBody PromotionDto promotionDto) {
        PromotionDto updatedPromotionDto = promotionService.updatePromotion(id, promotionDto);
        if (updatedPromotionDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedPromotionDto, HttpStatus.OK);
    }


    //URL: http://localhost:8080/api/TuCine/v1/promotions/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/promotions/{id}")
    public ResponseEntity<PromotionDto> deletePromotion(@PathVariable Integer id) {
        PromotionDto deletedPromotionDto = promotionService.deletePromotion(id);
        if (deletedPromotionDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedPromotionDto, HttpStatus.OK);
    }

}
