package com.health.care.lab.appointment.util;

import java.util.Random;

public class CommonUtil {

  public CommonUtil() {
  }

  public static String generateRandomNumber(String type) {
    Random random = new Random();
    int min = (int) Math.pow(10, 3 - 1);
    int max = (int) Math.pow(10, 3) - 1;
    String number = type + String.valueOf(min + random.nextInt(max - min + 1));
    return number;
  }
}
