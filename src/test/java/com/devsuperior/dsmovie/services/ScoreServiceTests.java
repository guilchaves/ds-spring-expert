package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.entities.ScoreEntity;
import com.devsuperior.dsmovie.entities.UserEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dsmovie.tests.MovieFactory;
import com.devsuperior.dsmovie.tests.ScoreFactory;
import com.devsuperior.dsmovie.tests.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class ScoreServiceTests {

    @InjectMocks
    private ScoreService service;

    @Mock
    private ScoreRepository repository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private UserService userService;

    private ScoreEntity score;
    private ScoreDTO scoreDTO;
    private UserEntity user;
    private MovieEntity movie;
    private MovieDTO movieDTO;
    private Long existingMovieId, nonExistingMovieId;


    @BeforeEach
    void setUp() throws Exception {
        user = UserFactory.createUserEntity();
        existingMovieId = 1L;
        nonExistingMovieId = 2L;
        score = ScoreFactory.createScoreEntity();
        scoreDTO = ScoreFactory.createScoreDTO();
        movie = MovieFactory.createMovieEntity();
        movieDTO = MovieFactory.createMovieDTO();

        Mockito.when(userService.authenticated()).thenReturn(user);
        Mockito.when(movieRepository.findById(existingMovieId)).thenReturn(Optional.of(movie));
        Mockito.when(movieRepository.findById(nonExistingMovieId)).thenReturn(Optional.empty());
        Mockito.when(repository.saveAndFlush(any())).thenReturn(score);
        Mockito.when(repository.save(any())).thenReturn(movie);
        Mockito.when(movieRepository.save(any())).thenReturn(movie);

    }

    @Test
    public void saveScoreShouldReturnMovieDTO() {
        movie.getScores().add(score);
        MovieDTO result = service.saveScore(scoreDTO);

        double sum = 0.0;
        for (ScoreEntity s : movie.getScores()) {
            sum = sum + s.getValue();
        }
        double avg = sum / movie.getScores().size();
        movie.setScore(avg);
        movie.setCount(movie.getScores().size());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(movie.getId(), result.getId());
        Assertions.assertEquals(movie.getScore(), result.getScore());
    }

    @Test
    public void saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId() {
    Mockito.when(movieRepository.findById(any())).thenReturn(Optional.empty());
    ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
        service.saveScore(scoreDTO);
    });

    Assertions.assertTrue(exception.getMessage().contains("Recurso não encontrado"));
    }
}
