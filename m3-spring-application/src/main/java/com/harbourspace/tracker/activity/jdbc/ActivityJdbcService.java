package com.harbourspace.tracker.activity.jdbc;

import com.harbourspace.tracker.activity.ActivityService;
import com.harbourspace.tracker.activity.model.Activity;
import com.harbourspace.tracker.activity.model.NewActivity;
import com.harbourspace.tracker.authorization.AuthorizationService;
import com.harbourspace.tracker.error.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ActivityJdbcService implements ActivityService {

    private final Logger logger = LoggerFactory.getLogger(ActivityJdbcService.class);

    private final ActivityJdbcRepository activityRepository;
    private final AuthorizationService authorizationService;

    public ActivityJdbcService(ActivityJdbcRepository activityRepository, AuthorizationService authorizationService) {
        this.activityRepository = activityRepository;
        this.authorizationService = authorizationService;
    }

    @Override
    public List<Activity> getActivity() {
        if (authorizationService.isSystem()) {
            logger.debug("Getting all activity");
            return activityRepository.selectAll();
        } else throw unauthorized();
    }

    @Override
    public Activity getActivityById(long id) {
        if (authorizationService.isSystem()) {
            logger.debug("Getting activity " + id);
            return activityRepository.selectById(id);
        } else throw unauthorized();
    }

    @Override
    public List<Activity> getActivityByUserId(long userId) {
        if (authorizationService.isSystem()) {
            logger.debug("Getting activity " + userId);
            return activityRepository.selectByUserId(userId);
        } else throw unauthorized();
    }

    @Override
    public Activity getActivityByType(String type) {
        if (authorizationService.isSystem()) {
            logger.debug("Getting activity " + type);
            return activityRepository.selectByType(type);
        } else throw unauthorized();
    }

    @Override
    public Activity createActivity(NewActivity activity) {
        if (authorizationService.isSystem()) {
            logger.debug("Creating new activity: " + activity);
            return activityRepository.insert(activity);
        } else throw unauthorized();
    }


    @Override
    public Activity updateActivity(Activity activity) {
        if (authorizationService.isSystem()) {
            logger.debug("Updating activity: " + activity);
            return activityRepository.update(activity);
        } else throw unauthorized();
    }

    @Override
    public void deleteActivity(long id) {
        if (authorizationService.isSystem()) {
            logger.debug("Deleting activity " + id);
            activityRepository.delete(id);
        } else throw unauthorized();
    }

    private AuthorizationException unauthorized() {
        var authorizationException = new AuthorizationException("Activity is not authorized for this operation.");
        logger.error(authorizationException.getMessage());
        return authorizationException;
    }
}
