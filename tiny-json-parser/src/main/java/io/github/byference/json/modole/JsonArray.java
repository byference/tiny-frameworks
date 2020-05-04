package io.github.byference.json.modole;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * JsonArray
 *
 * @author byference
 * @since 2020-05-01
 */
public class JsonArray implements Iterable<Object>, Serializable {

    private static final long serialVersionUID = 6562362443074164745L;

    List<Object> store  = new ArrayList<>();

    public void add(Object object) {
        store.add(object);
    }

    public Object get(int index) {
        return store.get(index);
    }

    @Override
    public Iterator<Object> iterator() {
        return store.iterator();
    }
}
