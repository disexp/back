package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.*;
import com.upc.TuCine.TuCine.exception.ResourceNotFoundException;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.*;
import com.upc.TuCine.TuCine.repository.*;
import com.upc.TuCine.TuCine.service.FilmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ContentRatingRepository contentRatingRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    FilmServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    private FilmDto EntityToDto(Film film){
        return modelMapper.map(film, FilmDto.class);
    }

    private Film DtoToEntity(FilmDto filmDto){
        return modelMapper.map(filmDto, Film.class);
    }

    @Override
    public List<FilmDto> getAllFilms() {
        List<Film> films = filmRepository.findAll();
        return films.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public FilmDto getFilmById(Integer id) {
        Film film = filmRepository.findById(id).orElse(null);
        if (film == null) {
            return null;
        }
        return EntityToDto(film);
    }

    @Override
    public FilmDto createFilm(FilmDto filmDto) {

        validateFilm(filmDto);
        existsFilmByTitle(filmDto.getTitle());

        ContentRating contentRating = contentRatingRepository.findById(filmDto.getContentRating().getId()).orElse(null);
        filmDto.setContentRating(contentRating);

        Film film = DtoToEntity(filmDto);
        return EntityToDto(filmRepository.save(film));
    }

    @Override
    public ContentRatingDto getContentRatingByFilmId(Integer id) {
        Film film = filmRepository.findById(id).orElse(null);
        if (film == null) {
            return null;
        }
        ContentRating contentRating = film.getContentRating();
        return modelMapper.map(contentRating, ContentRatingDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategoriesByFilmId(Integer id) {
        Film film = filmRepository.findById(id).orElse(null);
        if (film == null) {
            return null;
        }
        List<CategoryDto> categories = film.getCategories().stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
        return categories;
    }

    @Override
    public List<ActorDto> getAllActorsByFilmId(Integer id) {
        Film film = filmRepository.findById(id).orElse(null);
        if (film == null) {
            return null;
        }
        List<ActorDto> actors = film.getActors().stream()
                .map(actor -> modelMapper.map(actor, ActorDto.class))
                .collect(Collectors.toList());
        return actors;
    }

    @Override
    public void addActorToFilm(Integer idFilm, Integer idActor) {
        Film film = filmRepository.findById(idFilm).orElseThrow(() -> new ResourceNotFoundException("No se encuentra la película con id: " + idFilm));
        Actor actor = actorRepository.findById(idActor).orElseThrow(() -> new ResourceNotFoundException("No se encuentra el actor con id: " + idActor));
        film.getActors().add(actor);
        filmRepository.save(film);
    }

    @Override
    public void addCategoryToFilm(Integer idFilm, Integer idCategory) {
        Film film = filmRepository.findById(idFilm).orElseThrow(() -> new ResourceNotFoundException("No se encuentra la película con id: " + idFilm));
        Category category = categoryRepository.findById(idCategory).orElseThrow(() -> new ResourceNotFoundException("No se encuentra la categoría con id: " + idCategory));

        film.getCategories().add(category);
        filmRepository.save(film);

    }

    @Override
    public List<ShowtimeDto> getAllShowtimesByFilmId(Integer id) {
        Film film = filmRepository.findById(id).orElse(null);
        if (film == null) {
            return null;
        }
        List<ShowtimeDto> showtimes = showtimeRepository.findAllByFilm_id(film.getId()).stream()
                .map(showtime -> modelMapper.map(showtime, ShowtimeDto.class))
                .collect(Collectors.toList());
        return showtimes;
    }

    void validateFilm(FilmDto film) {

        if(film.getTitle() == null || film.getTitle().isEmpty()) {
            throw new ValidationException("El nombre de la película no puede estar vacío");
        }
        if(film.getDuration() == null || film.getDuration() <= 0) {
            throw new ValidationException("La duración de la película no puede ser menor o igual a 0");
        }
        if(film.getSynopsis() == null || film.getSynopsis().isEmpty()) {
            throw new ValidationException("La sinopsis de la película no puede estar vacía");
        }
        if(film.getYear() == null || film.getYear() <= 0) {
            throw new ValidationException("El año de la película no puede estar vacío");
        }
        if(film.getContentRating() == null) {
            throw new ValidationException("La clasificación de la película no puede estar vacío");
        }

    }
    void existsFilmByTitle(String title) {
        if (filmRepository.existsFilmByTitle(title)) {
            throw new ValidationException("No se puede agregar la película, puesto que una con su mismo titulo ya existe");
        }
    }
}
