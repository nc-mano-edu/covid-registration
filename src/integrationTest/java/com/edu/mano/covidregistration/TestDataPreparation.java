package com.edu.mano.covidregistration;

import com.edu.mano.covidregistration.tools.AppUtility;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.UUID;

import static java.time.format.DateTimeFormatter.ofPattern;

@Service
@Profile("integrationTest")
public class TestDataPreparation {
    static ZonedDateTime currentUtcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
    static String dateFormat = "yyyy-MM-dd";
    static String dateTimeFormat = "yyyy-MM-dd HH:mm:ss Z";

    public String getJson(String jsonTemplateFileName) {
        String inputJsonTemplate = AppUtility.getContentFromResourceFile(jsonTemplateFileName);
        return inputJsonTemplate;
    }

    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getRandomID() {
        return String.valueOf(new Random().nextInt());
    }

    public static String getJsonValue(String json, String jsonKey) {
        String jsonPath = "$[0]." + jsonKey;
        DocumentContext parsed = JsonPath.parse(json);
        return parsed.read(jsonPath);
    }

    public static String changeJsonValue(String json, String jsonKey, String value) {
        String jsonPath = "$[0]." + jsonKey;
        DocumentContext parsed = JsonPath.parse(json);
        return parsed.set(jsonPath, value).jsonString();
    }

    public static String getCurrentDate() {
        return currentUtcDateTime.format(ofPattern(dateFormat));
    }

    public static String getCurrentDateTime() {
        return currentUtcDateTime.format(ofPattern(dateTimeFormat));
    }

}
