package com.harbourspace.tracker.activity;

import com.harbourspace.tracker.activity.model.Activity;
import com.harbourspace.tracker.activity.model.NewActivity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    ResponseEntity<List<Activity>> getActivity() {
        return ResponseEntity.ok(activityService.getActivity());
    }

    @GetMapping("{id}")
    ResponseEntity<Activity> getActivityById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(activityService.getActivityById(id));
    }

    @GetMapping("{userId}")
    ResponseEntity<Activity> getActivityByUserId(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(activityService.getActivityByUserId(userId));
    }

    @GetMapping("{type}")
    ResponseEntity<Activity> getActivityByType(@PathVariable("type") String type) {
        return ResponseEntity.ok(activityService.getActivityByType(type));
    }

    @PostMapping
    ResponseEntity<Activity> createActivity(@RequestBody NewActivity activity) {
        return new ResponseEntity<>(activityService.createActivity(activity), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    ResponseEntity<Activity> updateActivity(
            @PathVariable("id") Long id,
            @RequestBody Activity activity
    ) {
        return ResponseEntity.ok(activityService.updateActivity(activity.copyWithId(id)));
    }

    @DeleteMapping("{id}")
    ResponseEntity<Object> deleteActivity(@PathVariable("id") Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.ok().build();
    }
}
