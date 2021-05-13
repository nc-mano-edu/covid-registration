package com.edu.mano.covidregistration.tools;

import com.edu.mano.covidregistration.exception.baseExceptions.InvalidFilePathException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;

public final class AppUtility {

    private static String absolutePath = new File("").getAbsolutePath();

    public static String getContentFromResourceFile(String fileName) {
        try {
            InputStream inputStream = AppUtility.class.getClassLoader().getResourceAsStream(fileName);
            return readFromInputStream(inputStream);
        } catch (IOException e) {
            throw new InvalidFilePathException(e);
        }
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public static String getContentFromFile(String fullyQualifiedPath) {
        try {
            return new String(Files.readAllBytes(Paths.get(absolutePath + fullyQualifiedPath)));
        } catch (IOException e) {
            throw new InvalidFilePathException(e);
        }
    }

    public static void mergeNode(JsonNode origNode, JsonNode updateNode) {
        Iterator<String> fieldNames = updateNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode jsonNode = origNode.get(fieldName);
            if (null != jsonNode && (jsonNode.isObject())) {
                mergeNode(jsonNode, updateNode.get(fieldName));
            } else if (null != jsonNode && (jsonNode.isArray())) {
                mergeArray(jsonNode, updateNode.get(fieldName));
            } else if (updateNode.hasNonNull(fieldName)) {
                ((ObjectNode) origNode).set(fieldName, updateNode.get(fieldName));
            }
        }
    }

    public static void mergeArray(JsonNode mainNode, JsonNode updateNode) {
        for (int i = 0; i < updateNode.size(); i++) {
            JsonNode jsonNode = mainNode.get(i);
            if (null != jsonNode && jsonNode.isObject()) {
                mergeNode(jsonNode, updateNode.get(i));
            } else if (null != jsonNode && (jsonNode.isArray())) {
                mergeArray(jsonNode, updateNode.get(i));
            } else if (null == jsonNode) {
                ((ArrayNode) mainNode).add(updateNode.get(i));
            } else {
                ((ArrayNode) mainNode).set(i, updateNode.get(i));
            }
        }
    }

    public static Date getCurrentDate() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.of("Europe/Samara")).toInstant());
    }

    public static void validateEquals(String expected, String actual) throws JSONException {
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
    }
}
