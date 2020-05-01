package io.github.byference.json.test;

import io.github.byference.json.JSON;
import io.github.byference.json.mock.JSONDataStore;
import io.github.byference.json.modole.JsonObject;

/**
 * JsonParseTest
 *
 * @author byference
 * @since 2020-05-01
 */
public class JsonParseTest {

    public static void main(String[] args) {
        String jsonStr = JSONDataStore.getJsonData();
        JsonObject jsonObject = new JSON(jsonStr).parseJsonObject();
        System.out.println(jsonObject);
    }
}
