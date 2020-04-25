package io.github.byference.json;

/**
 * CharReader
 *
 * @author byference
 * @since 2020-04-19
 */
public class CharReader {

    private int pos;
    private int size;
    private final String jsonStr;
    private final char[] buffer;

    public CharReader(String jsonStr) {
        this.jsonStr = jsonStr;
        buffer = jsonStr.toCharArray();
        fillBuffer();
    }

    public char peek() {
        if (pos - 1 >= size) {
            return (char) -1;
        }
        return buffer[Math.max(0, pos - 1)];
    }

    public char next() {
        if (!hasMore()) {
            return (char) -1;
        }
        return buffer[pos++];
    }

    public void back() {
        pos = Math.max(0, --pos);
    }

    public boolean hasMore() {
        return pos < size;
    }

    void fillBuffer() {
        pos = 0;
        size = jsonStr.length();
    }
}
