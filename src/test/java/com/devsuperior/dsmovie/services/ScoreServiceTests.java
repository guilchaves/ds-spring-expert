package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.config.AuthorizationServerConfig;
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
import org.mockito.stubbing.OngoingStubbing;
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
		MovieDTO result = service.saveScore(scoreDTO);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), movie.getId());
	}
	
	@Test
	public void saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId() {
		Mockito.doThrow(ResourceNotFoundException.class).when(movieRepository).findById(any());
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.saveScore(scoreDTO);
		});
	}
}
