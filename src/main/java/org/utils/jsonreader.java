package org.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class jsonreader {
    public static Map<String, Object> jsonFileReader(String filepath) throws IOException {
        Map<String, Object> map = null;
        try {
        String jsonString = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/"+filepath)));
        ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


    public static Map<String, Object> jsonStringReader(String JsonString) throws IOException {
        Map<String, Object> map = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(JsonString, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String getValueFromEnvParams(String path) throws IOException {
        String val = null;
        try {
            Map<String, Object> envmap = jsonreader.jsonFileReader("env.json");
            String[] route = path.split("/");
            Map<String, Object> temp = null;

            for (int i = 0; i < route.length; i++) {
                if (i < route.length - 1) {
                    temp = (Map<String, Object>) envmap.get(route[i]);
                }
                if (i == route.length - 1) {
                    val = temp.get(route[i]).toString();
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return val;
    }
}

