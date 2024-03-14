package com.harbourspace.tracker.exercise;

import com.harbourspace.tracker.exercise.model.Exercise;
import com.harbourspace.tracker.exercise.model.NewExercise;

import java.util.List;

public interface ExerciseService {

    List<Exercise> getExercises();

    Exercise getExerciseById(long id);

    Exercise getExerciseByName(String name);

    Exercise createExercise(NewExercise exercise);

    Exercise updateExercise(Exercise exercise);

    void deleteExercise(long id);
}
