package com.upc.TuCine.TuCine;

import com.upc.TuCine.TuCine.model.Film;
import com.upc.TuCine.TuCine.service.impl.FilmServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilmIsOldTest {
	/* Una película al publicarse, para prevenir copyright, debe ser al menos
	* 2 años antiguo. Para ello, se resta el año actual con el año de la película
	* que se está probando. Si resulta ser mayor o igual que 2, se permite.*/
	private final FilmServiceImpl filmService = new FilmServiceImpl();

	@Test
	public void testFilmIsOld() {
		Film oldFilm = new Film();
		Film recentFilm = new Film();
		oldFilm.setYear(2015);
		recentFilm.setYear(2023);

		Assertions.assertTrue(filmService.isFilmOld(oldFilm));
		Assertions.assertFalse(filmService.isFilmOld(recentFilm));
	}
}