package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.PromotionDto;
import com.upc.TuCine.TuCine.model.Person;
import com.upc.TuCine.TuCine.model.Promotion;
import com.upc.TuCine.TuCine.repository.PromotionRepository;
import com.upc.TuCine.TuCine.service.PromotionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private ModelMapper modelMapper;

    PromotionServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    private PromotionDto EntityToDto(Promotion promotion){
        return modelMapper.map(promotion, PromotionDto.class);
    }

    private Promotion DtoToEntity(PromotionDto promotionDto){
        return modelMapper.map(promotionDto, Promotion.class);
    }

    @Override
    public List<PromotionDto> getAllPromotions() {
        List<Promotion> promotions= promotionRepository.findAll();
        return promotions.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PromotionDto createPromotion(PromotionDto promotionDto) {
        validatePromotion(promotionDto);
        existsPromotionByTitle(promotionDto.getTitle());
        Promotion promotion = DtoToEntity(promotionDto);
        return EntityToDto(promotionRepository.save(promotion));
    }

    @Override
    public PromotionDto updatePromotion(Integer id, PromotionDto promotionDto) {
        Promotion promotionToUpdate = promotionRepository.findById(id).orElse(null);
        if (promotionToUpdate == null) {
            return null; // O lanzar una excepción si lo prefieres
        }
        validatePromotion(promotionDto);

        // Actualizar los campos de la promoción existente
        promotionToUpdate.setTitle(promotionDto.getTitle());
        promotionToUpdate.setDescription(promotionDto.getDescription());
        promotionToUpdate.setStartDate(promotionDto.getStartDate());
        promotionToUpdate.setEndDate(promotionDto.getEndDate());
        promotionToUpdate.setDiscount(promotionDto.getDiscount());

        // Guardar la promoción actualizada en el repositorio
        Promotion updatedPromotion = promotionRepository.save(promotionToUpdate);

        return EntityToDto(updatedPromotion);
    }

    @Override
    public PromotionDto deletePromotion(Integer id) {
        Promotion promotionToDelete = promotionRepository.findById(id).orElse(null);
        if (promotionToDelete == null) {
            return null; // O lanzar una excepción si lo prefieres
        }
        promotionRepository.delete(promotionToDelete);
        return EntityToDto(promotionToDelete);
    }
    private void validatePromotion(PromotionDto promotion) {
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

    @Override
    public void existsPromotionByTitle(String title) {
        if (promotionRepository.existsPromotionByTitle(title)) {
            throw new RuntimeException("Ya existe una promoción con el título " + title);
        }
    }
}
