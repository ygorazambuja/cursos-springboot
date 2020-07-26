package com.azambuja.cursospringboot.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

  public static List<Integer> stringToIntegerArray(String string) {
    return Arrays
      .stream(string.split(","))
      .map(Integer::parseInt)
      .collect(Collectors.toList());
  }

  public static String stringToDecodedString(String name) {
    try {
      return URLDecoder.decode(name, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }
}
