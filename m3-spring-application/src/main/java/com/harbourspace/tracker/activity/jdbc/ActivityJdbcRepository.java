package com.harbourspace.tracker.activity.jdbc;

import com.harbourspace.tracker.activity.model.Activity;
import com.harbourspace.tracker.activity.model.NewActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ActivityJdbcRepository {

    private Logger logger = LoggerFactory.getLogger(ActivityJdbcRepository.class);

    protected final JdbcTemplate jdbcTemplate;

    public ActivityJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Activity> selectAll() {
        logger.debug("Selecting all activity");
        return jdbcTemplate.query("SELECT * FROM activity",
                (resultSet, index) -> new Activity(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("type"),
                        resultSet.getString("name"),
                        resultSet.getDouble("kcal_per_minute")
                ));
    }

    public Activity selectById(Long id) {
        logger.debug("Selecting activity " + id);
        return jdbcTemplate.query("SELECT * FROM activity WHERE id = ? LIMIT 1",
                this::rowMapper, id
        ).stream().findFirst().orElse(null);
    }

    public List<Activity> selectByUserId(Long userId) {
        logger.debug("Selecting activity " + userId);
        return jdbcTemplate.query("SELECT * FROM activity WHERE user_id = ?",
                new Object[]{userId}, (resultSet, index) -> new Activity(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("type"),
                        resultSet.getString("name"),
                        resultSet.getDouble("kcal_per_minute")
                ));
    }

    public Activity selectByType(String type) {
        logger.debug("Selecting activity " + type);
        return jdbcTemplate.query(
                "SELECT * FROM activity WHERE type = ? LIMIT 1",
                this::rowMapper, type
        ).stream().findFirst().orElse(null);
    }

    public Activity insert(NewActivity activity) {
        logger.debug("Inserting new activity: " + activity);
        return jdbcTemplate.query(
                "SELECT * FROM FINAL TABLE (INSERT INTO activity (name) VALUES (?))",
                this::rowMapper, activity.name()
        ).stream().findFirst().orElse(null);
    }

    public Activity update(Activity activity) {
        logger.debug("Updating activity: " + activity);
        return jdbcTemplate.query(
                "SELECT * FROM FINAL TABLE (UPDATE activity SET name = ? WHERE id = ?)",
                this::rowMapper, activity.name(), activity.id()
        ).stream().findFirst().orElse(null);
    }

    public void delete(Long id) {
        logger.debug("Deleting activity " + id);
        jdbcTemplate.update("DELETE FROM activity WHERE id = ?", id);
    }

    private Activity rowMapper(ResultSet rs, int i) throws SQLException {
        return new Activity(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("type"),
                rs.getString("name"),
                rs.getDouble("kcal_per_minute")
        );
    }
}
