package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.ShowtimeDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.*;
import com.upc.TuCine.TuCine.repository.BusinessRepository;
import com.upc.TuCine.TuCine.repository.FilmRepository;
import com.upc.TuCine.TuCine.repository.PromotionRepository;
import com.upc.TuCine.TuCine.repository.ShowtimeRepository;
import com.upc.TuCine.TuCine.service.ShowtimeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ShowtimeServiceImpl() {
        this.modelMapper = new ModelMapper();
    }

    private ShowtimeDto EntityToDto(Showtime showtime){
        return modelMapper.map(showtime, ShowtimeDto.class);
    }

    private Showtime DtoToEntity(ShowtimeDto showtimeDto){
        return modelMapper.map(showtimeDto, Showtime.class);
    }

    @Override
    public List<ShowtimeDto> getAllShowtimes() {
        List<Showtime> showtimes = showtimeRepository.findAll();
        return showtimes.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShowtimeDto getShowtimeById(Integer id) {
        Showtime showtime = showtimeRepository.findById(id).orElse(null);
        if (showtime == null) {
            return null; // O lanzar una excepción si lo prefieres
        }
        return EntityToDto(showtime);
    }

    @Override
    public ShowtimeDto createShowtime(ShowtimeDto showtimeDto) {

        Film film = filmRepository.findById(showtimeDto.getFilm().getId()).orElse(null);
        showtimeDto.setFilm(film);

        Business business = businessRepository.findById(showtimeDto.getBusiness().getId()).orElse(null);
        showtimeDto.setBusiness(business);

        Promotion promotion;
        try {
            promotion = promotionRepository.findById(showtimeDto.getPromotion().getId()).orElse(null);
        } catch (Exception e) {
            promotion= null;
        }
        showtimeDto.setPromotion(promotion);

        validateShowtime(showtimeDto);
        existsFilmById(showtimeDto.getFilm().getId());
        existsBusinessById(showtimeDto.getBusiness().getId());
        if (showtimeDto.getPromotion() != null) {
            existsPromotionById(showtimeDto.getPromotion().getId());
        }
        Showtime showtime = DtoToEntity(showtimeDto);
        Showtime createdShowtime = showtimeRepository.save(showtime);

        return EntityToDto(createdShowtime);
    }

    @Override
    public ShowtimeDto updateShowtime(Integer id, ShowtimeDto showtimeDto) {
        Showtime showtimeToUpdate = showtimeRepository.findById(id).orElse(null);
        if (showtimeToUpdate == null) {
            return null; // O lanzar una excepción si lo prefieres
        }
        validateShowtime(showtimeDto);
        existsFilmById(showtimeDto.getFilm().getId());
        existsBusinessById(showtimeDto.getBusiness().getId());
        existsPromotionById(showtimeDto.getPromotion().getId());

        Business business = businessRepository.findById(showtimeDto.getBusiness().getId()).orElse(null);
        showtimeDto.setBusiness(business);

        Promotion promotion = promotionRepository.findById(showtimeDto.getPromotion().getId()).orElse(null);
        showtimeDto.setPromotion(promotion);

        // Actualizar los campos del Showtime existente
        showtimeToUpdate.setDate(showtimeDto.getDate());
        showtimeToUpdate.setTime(showtimeDto.getTime());
        showtimeToUpdate.setPrice(showtimeDto.getPrice());
        showtimeToUpdate.setFilm(showtimeDto.getFilm());
        showtimeToUpdate.setBusiness(showtimeDto.getBusiness());
        showtimeToUpdate.setPromotion(showtimeDto.getPromotion());

        // Guardar el Showtime actualizado en el repositorio
        Showtime updatedShowtime = showtimeRepository.save(showtimeToUpdate);

        return EntityToDto(updatedShowtime);
    }

    @Override
    public ShowtimeDto deleteShowtime(Integer id) {
        Showtime showtimeToDelete = showtimeRepository.findById(id).orElse(null);
        if (showtimeToDelete == null) {
            return null; // O lanzar una excepción si lo prefieres
        }
        showtimeRepository.delete(showtimeToDelete);
        return EntityToDto(showtimeToDelete);
    }

    private void validateShowtime(ShowtimeDto showtime) {
        if (showtime.getDate() == null) {
            throw new ValidationException("La fecha es obligatoria");
        }
        if (showtime.getTime() == null ) {
            throw new ValidationException("La hora es obligatoria");
        }
        if (showtime.getPrice() == null ) {
            throw new ValidationException("El precio es obligatorio");
        }
        if (showtime.getFilm() == null ) {
            throw new ValidationException("La película es obligatoria");
        }
        if (showtime.getBusiness() == null ) {
            throw new ValidationException("El negocio es obligatorio");
        }

    }

    private void existsFilmById(Integer id) {
        if (!filmRepository.existsById(id)) {
            throw new ValidationException("La película no existe");
        }
    }

    private void existsBusinessById(Integer id) {
        if (!businessRepository.existsById(id)) {
            throw new ValidationException("El negocio no existe");
        }
    }

    private void existsPromotionById(Integer id) {
        if (!promotionRepository.existsById(id)) {
            throw new ValidationException("La promoción no existe");
        }
    }

}
