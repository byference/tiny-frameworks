package io.github.byference.json.modole;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * JsonObject
 *
 * @author byference
 * @since 2020-05-01
 */
@SuppressWarnings("unchecked")
public class JsonObject implements Serializable {

    private static final long serialVersionUID = 7572739533787998722L;

    private final Map<String, Object> store = new HashMap<>();

    public <T> T get(String key) {
        return (T) store.get(key);
    }

    public void put(String key, Object value) {
        store.put(key, value);
    }

    public void remove(String key) {
        store.remove(key);
    }

}
