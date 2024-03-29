package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true, value = """
            SELECT * FROM (
                SELECT DISTINCT tb_movie.id, tb_movie.title, tb_genre.name
                FROM tb_movie
                INNER JOIN tb_genre ON tb_movie.genre_id = tb_genre.id
                WHERE (:genreId IS NULL OR tb_movie.genre_id = :genreId)
                ORDER BY LOWER(tb_movie.title)
            ) AS tb_result
            """, countQuery = """
             SELECT COUNT(*) FROM (
                SELECT DISTINCT tb_movie.id, tb_movie.title, tb_genre.name
                FROM tb_movie
                LEFT JOIN tb_genre ON tb_movie.genre_id = tb_genre.id
                WHERE (:genreId IS NULL OR tb_movie.genre_id = :genreId)
                ORDER BY LOWER(tb_movie.title)
            ) AS tb_result
            """)
    Page<MovieProjection> searchMovies(Long genreId, Pageable pageable);


    @Query("SELECT obj FROM Movie obj JOIN FETCH obj.genre WHERE obj.id IN :genreIds")
    List<Movie> searchMoviesWithGenre(List<Long> genreIds);
}
