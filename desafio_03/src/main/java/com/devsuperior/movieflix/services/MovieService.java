package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        Movie entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return new MovieDetailsDTO(entity);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Page<MovieDetailsDTO> findByGenre(Long genreId, Pageable pageable) {
        Page<MovieProjection> page = repository.searchMovies(genreId, pageable);
        List<Long> movieIds = page.map(MovieProjection::getId).toList();

        List<Movie> entities = repository.searchMoviesWithGenre(movieIds);
        entities = (List<Movie>) Utils.replace(page.getContent(), entities);

        List<MovieDetailsDTO> dtos = entities.stream().map(MovieDetailsDTO::new).toList();

        return new PageImpl<>(dtos, page.getPageable(), page.getTotalElements());
    }

}
