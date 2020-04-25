package io.github.byference.json.mock;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * JSONDataStore
 *
 * @author byference
 * @since 2020-03-12
 */
public class JSONDataStore {

    private JSONDataStore() {}

    /**
     * get json data
     */
    public static String getJsonData() {
        try {
            Object content = JSONDataStore.class.getResource("/data.json").getContent();
            if (content instanceof BufferedInputStream) {
                try (BufferedInputStream bufferedInputStream = (BufferedInputStream) content;
                     InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
                     BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    StringBuilder json = new StringBuilder();
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        json.append(line);
                        line = bufferedReader.readLine();
                    }
                    return json.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{}";
    }

}
