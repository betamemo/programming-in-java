package com.harbourspace.lesson06;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTask {

    public BigDecimal calculateArea(int radius, int decimalPlaces) {
        BigDecimal r = BigDecimal.valueOf(radius);
        BigDecimal pi = BigDecimal.valueOf(Math.PI);

        BigDecimal area = r.pow(2).multiply(pi);
        BigDecimal round = area.setScale(decimalPlaces, RoundingMode.HALF_EVEN);

        return round;
    }

}
