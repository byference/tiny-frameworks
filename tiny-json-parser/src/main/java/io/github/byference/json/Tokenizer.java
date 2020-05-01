package io.github.byference.json;

import io.github.byference.json.modole.Token;
import io.github.byference.json.util.Assert;

import java.util.LinkedList;

/**
 * Tokenizer
 *
 * @author byference
 * @since 2020-04-19
 */
public class Tokenizer {

    private final String jsonStr;

    private final LinkedList<Token> tokens = new LinkedList<>();

    private final char[] numberArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public Tokenizer(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public LinkedList<Token> tokenize() {

        Assert.isNotNull(jsonStr, "source json string can not be empty");
        CharReader charReader = new CharReader(jsonStr);

        while (charReader.hasMore()) {
            char ch = charReader.next();
            if (ch == ' ') {
                continue;
            }
            switch (ch) {
                case '{' -> tokens.add(new Token(TokenType.BEGIN_OBJECT, ch));
                case '}' -> tokens.add(new Token(TokenType.END_OBJECT, ch));
                case '[' -> tokens.add(new Token(TokenType.BEGIN_ARRAY, ch));
                case ']' -> tokens.add(new Token(TokenType.END_ARRAY, ch));
                case ',' -> tokens.add(new Token(TokenType.SEP_COMMA, ch));
                case ':' -> tokens.add(new Token(TokenType.SEP_COLON, ch));
                case 'n' -> tokens.add(readNull(charReader));
                case 't', 'f' -> tokens.add(readBoolean(charReader));
                case '"' -> tokens.add(readString(charReader));
                case '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> tokens.add(readNumber(charReader));
            }
        }
        tokens.add(new Token(TokenType.END_DOCUMENT, null));
        return tokens;
    }

    private Token readNumber(CharReader charReader) {

        StringBuilder number = new StringBuilder();
        if (charReader.peek() == '-') {
            number.append("-");
        } else {
            charReader.back();
        }

        char ch;
        while (isDigit(ch = charReader.next())) {
            number.append(ch);
        }
        charReader.back();
        return new Token(TokenType.NUMBER, number.toString());
    }

    private Token readString(CharReader charReader) {
        // TODO 暂时不处理转义 & Unicode 场景
        StringBuilder string = new StringBuilder();

        char ch;
        while ((ch = charReader.next()) != '"') {
            string.append(ch);
        }
        return new Token(TokenType.STRING, string.toString());
    }

    private Token readNull(CharReader charReader) {
        if (charReader.peek() == 'n' && charReader.next() == 'u' && charReader.next() == 'l' && charReader.next() == 'l') {
            return new Token(TokenType.NULL, "null");
        }
        throw new IllegalArgumentException("Invalid json string");
    }

    private Token readBoolean(CharReader charReader) {
        if (charReader.peek() == 't' && charReader.next() == 'r' && charReader.next() == 'u' && charReader.next() == 'e') {
            return new Token(TokenType.BOOLEAN, "true");
        }
        if (charReader.peek() == 'f' && charReader.next() == 'a' && charReader.next() == 'l' && charReader.next() == 's' && charReader.next() == 'e') {
            return new Token(TokenType.BOOLEAN, "false");
        }
        throw new IllegalArgumentException("Invalid json string");
    }

    private boolean isDigit(char ch) {
        for (char c : numberArray) {
            if (c == ch) {
                return true;
            }
        }
        return false;
    }

}
