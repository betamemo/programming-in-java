package com.harbourspace.tracker.activity;

import com.harbourspace.tracker.activity.model.Activity;
import com.harbourspace.tracker.activity.model.NewActivity;

import java.util.List;

public interface ActivityService {

    List<Activity> getActivityByUserId();

    Activity getActivityById(long id);

    Activity createActivity(NewActivity activity);

    Activity updateActivity(Activity activity);

    void deleteActivity(long id);

}
