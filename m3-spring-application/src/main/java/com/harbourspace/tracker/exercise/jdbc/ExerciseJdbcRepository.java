package com.harbourspace.tracker.exercise.jdbc;

import com.harbourspace.tracker.exercise.model.Exercise;
import com.harbourspace.tracker.exercise.model.NewExercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExerciseJdbcRepository {

    private Logger logger = LoggerFactory.getLogger(ExerciseJdbcRepository.class);

    protected final JdbcTemplate jdbcTemplate;

    public ExerciseJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Exercise> selectAll() {
        logger.debug("Selecting all exercises");
        return jdbcTemplate.query("SELECT * FROM exercises", (resultSet, index) -> new Exercise(
                resultSet.getLong("id"),
                resultSet.getLong("user_id"),
                resultSet.getLong("activity_id"),
                resultSet.getTimestamp("start_time"),
                resultSet.getLong("duration"),
                resultSet.getLong("duration")
        ));
    }

    public Exercise selectById(Long id) {
        logger.debug("Selecting exercise " + id);
        return jdbcTemplate.query("SELECT * FROM exercises WHERE id = ? LIMIT 1", this::rowMapper, id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Exercise selectByName(String name) {
        logger.debug("Selecting exercise " + name);
        return jdbcTemplate.query("SELECT * FROM exercises WHERE name = ? LIMIT 1", this::rowMapper, name)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Exercise insert(NewExercise exercise) {
        logger.debug("Inserting new exercise: " + exercise);
        return jdbcTemplate.query(
                "SELECT * FROM FINAL TABLE (INSERT INTO exercises (activityId) VALUES (?))",
                this::rowMapper,
                exercise.activityId()
        ).stream().findFirst().orElse(null);
    }

    public Exercise update(Exercise exercise) {
        logger.debug("Updating exercise: " + exercise);
        return jdbcTemplate.query(
                "SELECT * FROM FINAL TABLE (UPDATE exercises SET name = ? WHERE id = ?)",
                this::rowMapper,
                exercise.activityId(),
                exercise.id()
        ).stream().findFirst().orElse(null);
    }

    public void delete(Long id) {
        logger.debug("Deleting exercise " + id);
        jdbcTemplate.update("DELETE FROM exercises WHERE id = ?", id);
    }

    private Exercise rowMapper(ResultSet rs, int i) throws SQLException {
        return new Exercise(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("activity_id"),
                rs.getTimestamp("start_time"),
                rs.getLong("duration"),
                rs.getLong("duration")
        );
    }
}
