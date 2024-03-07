package com.harbourspace.lesson06;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeTask {

    public static void main(String[] args) {

        ZonedDateTime localDateTimeNow = ZonedDateTime.now();
        System.out.println("Local Date Time: " + localDateTimeNow); // 2024-01-14T09:44:26.147022+01:00[Asia/Bangkok]

        ZonedDateTime tokyoDateTimeNow = localDateTimeNow.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println("Tokyo Date Time: " + tokyoDateTimeNow); // 2024-01-14T09:44:26.150263+07:00[Asia/Tokyo]

        Duration timeDifference = Duration.between(localDateTimeNow.toLocalTime(), tokyoDateTimeNow.toLocalTime());
        System.out.println("Time Difference: " + timeDifference.abs());
    }
}
