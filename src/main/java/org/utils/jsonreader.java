package org.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class jsonreader {
    public static Map map = null;

    public static Map elementLocatorReader(String filepath)  {

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/" + filepath)));
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map elementLocatorReader(String filepath, String locator)  {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/" + filepath)));
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert map != null;
        return (Map) map.get(locator);
    }


    public static Map jsonStringToMap(String JsonString)  {
        Map map = null;
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
            Map envmap = jsonreader.elementLocatorReader("env.json");
            String[] route = path.split("/");
            Map temp = null;

            for (int i = 0; i < route.length; i++) {
                if (i < route.length - 1) {
                    temp = (Map) envmap.get(route[i]);
                }
                if (i == route.length - 1) {
                    assert temp != null;
                    val = temp.get(route[i]).toString();
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return val;
    }
}

