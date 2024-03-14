package com.harbourspace.tracker.exercise.jdbc;

import com.harbourspace.tracker.authorization.AuthorizationService;
import com.harbourspace.tracker.error.AuthorizationException;
import com.harbourspace.tracker.exercise.ExerciseService;
import com.harbourspace.tracker.exercise.model.Exercise;
import com.harbourspace.tracker.exercise.model.NewExercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExerciseJdbcService implements ExerciseService {

    private final Logger logger = LoggerFactory.getLogger(ExerciseJdbcService.class);

    private final ExerciseJdbcRepository exerciseRepository;
    private final AuthorizationService authorizationService;

    public ExerciseJdbcService(ExerciseJdbcRepository exerciseRepository, AuthorizationService authorizationService) {
        this.exerciseRepository = exerciseRepository;
        this.authorizationService = authorizationService;
    }

    @Override
    public List<Exercise> getExercises() {
        if (authorizationService.isSystem()) {
            logger.debug("Getting all exercises");
            return exerciseRepository.selectAll();
        } else throw unauthorized();
    }

    @Override
    public Exercise getExerciseById(long id) {
        if (authorizationService.isSystem()) {
            logger.debug("Getting exercise " + id);
            return exerciseRepository.selectById(id);
        } else throw unauthorized();
    }

    @Override
    public Exercise getExerciseByName(String name) {
        if (authorizationService.isSystem()) {
            logger.debug("Getting exercise " + name);
            return exerciseRepository.selectByName(name);
        } else throw unauthorized();
    }

    @Override
    public Exercise createExercise(NewExercise exercise) {
        if (authorizationService.isSystem()) {
            logger.debug("Creating new exercise: " + exercise);
            return exerciseRepository.insert(exercise);
        } else throw unauthorized();
    }


    @Override
    public Exercise updateExercise(Exercise exercise) {
        if (authorizationService.isSystem()) {
            logger.debug("Updating exercise: " + exercise);
            return exerciseRepository.update(exercise);
        } else throw unauthorized();
    }

    @Override
    public void deleteExercise(long id) {
        if (authorizationService.isSystem()) {
            logger.debug("Deleting exercise " + id);
            exerciseRepository.delete(id);
        } else throw unauthorized();
    }

    private AuthorizationException unauthorized() {
        var authorizationException = new AuthorizationException("Exercise is not authorized for this operation.");
        logger.error(authorizationException.getMessage());
        return authorizationException;
    }
}
