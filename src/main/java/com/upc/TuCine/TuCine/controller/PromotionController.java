package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.model.Promotion;
import com.upc.TuCine.TuCine.repository.PromotionRepository;
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
    private PromotionRepository promotionRepository;

    public PromotionController(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/promotions
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/promotions")
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        return new ResponseEntity<List<Promotion>>(promotionRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/promotions
    //Method: POST
    @Transactional
    @PostMapping("/promotions")
    public ResponseEntity<Promotion> createPromotion(@RequestBody Promotion promotion){
        validatePromotion(promotion);
        existsPromotionByTitle(promotion.getTitle());
        return new ResponseEntity<>(promotionRepository.save(promotion), HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/TuCine/v1/promotions/{id}
    //Method: PUT
    @Transactional
    @PutMapping("/promotions/{id}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable Integer id, @RequestBody Promotion promotion){
        Promotion promotionToUpdate = promotionRepository.findById(id).orElse(null);
        if(promotionToUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        validatePromotion(promotion);
        promotionToUpdate.setTitle(promotion.getTitle());
        promotionToUpdate.setDescription(promotion.getDescription());
        promotionToUpdate.setStartDate(promotion.getStartDate());
        promotionToUpdate.setEndDate(promotion.getEndDate());
        promotionToUpdate.setDiscount(promotion.getDiscount());
        return new ResponseEntity<>(promotionRepository.save(promotionToUpdate), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/promotions/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/promotions/{id}")
    public ResponseEntity<Promotion> deletePromotion(@PathVariable Integer id){
        Promotion promotionToDelete = promotionRepository.findById(id).orElse(null);
        if(promotionToDelete == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        promotionRepository.delete(promotionToDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validatePromotion(Promotion promotion) {
        if (promotion.getTitle() == null || promotion.getTitle().isEmpty()) {
            throw new RuntimeException("El titulo no puede ser nulo o estar vacío");
        }
        if (promotion.getDescription() == null || promotion.getDescription().isEmpty()) {
            throw new RuntimeException("La descripción no puede ser nula o estar vacía");
        }
        if (promotion.getStartDate() == null) {
            throw new RuntimeException("La fecha de inicio no puede ser nula");
        }
        if (promotion.getEndDate() == null) {
            throw new RuntimeException("La fecha de fin no puede ser nula");
        }
        if (promotion.getStartDate().isAfter(promotion.getEndDate())) {
            throw new RuntimeException("La fecha de inicio no puede ser mayor a la fecha de fin");
        }
        if(promotion.getDiscount() == null){
            throw new RuntimeException("El descuento no puede ser nulo");
        }
    }

    private void existsPromotionByTitle(String title) {
        if (promotionRepository.existsPromotionByTitle(title)) {
            throw new RuntimeException("Ya existe una promoción con el título " + title);
        }
    }

}
