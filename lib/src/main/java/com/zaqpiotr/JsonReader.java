package com.zaqpiotr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

public class JsonReader {

  public static <T> List<T> jsonToList(String jsonContainsArray, Class<T> arrayElementType) {
    ObjectMapper objectMapper = JsonMapper.builder()
        .addModule(new JavaTimeModule())
        .build();
    try {
      TypeFactory typeFactory = TypeFactory.defaultInstance();
      return objectMapper.readValue(jsonContainsArray, typeFactory.constructCollectionType(List.class, arrayElementType));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static <T> T jsonToObject(String jsonContainsObject, Class<T> objectType) {
    ObjectMapper objectMapper = JsonMapper.builder()
        .addModule(new JavaTimeModule())
        .build();
    try {
      return objectMapper.readValue(jsonContainsObject, objectType);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String readJsonFileAndMergeAllLines(String filePath) {
    try {
      File resourcesDirectory = new File(filePath);
      return Files.readLines(resourcesDirectory, Charset.forName("utf-8"))
          .stream().parallel()
          .collect(Collectors.joining());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }

}