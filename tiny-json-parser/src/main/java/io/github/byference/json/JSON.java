package io.github.byference.json;

import io.github.byference.json.modole.JsonObject;
import io.github.byference.json.modole.Token;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Objects;

import static io.github.byference.json.TokenType.*;

/**
 * JSON
 *
 * @author byference
 * @since 2020-04-25
 */
public class JSON {

    private final Iterator<Token> tokens;

    private Token previousToken;

    public JSON(String jsonStr) {
        this.tokens = new Tokenizer(jsonStr).tokenize().iterator();
    }


    /**
     * 语法分析
     * TODO parse JsonArray
     */
    public JsonObject parseJsonObject() {

        JsonObject jsonObject = new JsonObject();
        int expectToken = -1;
        Token token = null;
        String key = null;

        while (tokens.hasNext()) {

            initPreviousToken(token);
            token = tokens.next();
            TokenType tokenType = token.getTokenType();
            String tokenValue = token.getValue();

            switch (tokenType) {
                case BEGIN_OBJECT -> {
                    checkExpectToken(tokenType, expectToken);
                    if (key != null) {
                        jsonObject.put(key, parseJsonObject());
                    }
                    expectToken = STRING.getCode() | END_OBJECT.getCode();
                }
                case END_OBJECT -> {
                    checkExpectToken(tokenType, expectToken);
                    return jsonObject;
                }
                case STRING -> {
                    checkExpectToken(tokenType, expectToken);
                    TokenType previousTokenType = previousToken.getTokenType();
                    if (previousTokenType == SEP_COLON) {
                        // value
                        jsonObject.put(key, token.getValue());
                        expectToken = SEP_COMMA.getCode() | END_OBJECT.getCode();
                    } else {
                        // key
                        key = token.getValue();
                        expectToken = SEP_COLON.getCode();
                    }
                }
                case NUMBER -> {
                    checkExpectToken(tokenType, expectToken);
                    jsonObject.put(key, new BigDecimal(tokenValue));
                    expectToken = SEP_COMMA.getCode() | END_OBJECT.getCode();
                }
                case NULL -> {
                    checkExpectToken(tokenType, expectToken);
                    jsonObject.put(key, null);
                    expectToken = SEP_COMMA.getCode() | END_OBJECT.getCode();
                }
                case BOOLEAN -> {
                    checkExpectToken(tokenType, expectToken);
                    jsonObject.put(key, Boolean.valueOf(tokenValue));
                    expectToken = SEP_COMMA.getCode() | END_OBJECT.getCode();
                }
                case SEP_COLON -> {
                    checkExpectToken(tokenType, expectToken);
                    expectToken = NULL.getCode() | NUMBER.getCode() | BOOLEAN.getCode() | STRING.getCode() | BEGIN_OBJECT.getCode() | BEGIN_ARRAY.getCode();
                }
                case SEP_COMMA -> {
                    checkExpectToken(tokenType, expectToken);
                    expectToken = STRING.getCode();

                }
                case END_DOCUMENT -> {
                    checkExpectToken(tokenType, expectToken);
                    jsonObject.remove(null);
                    return jsonObject;
                }
                default -> throw new IllegalArgumentException("Parse error, invalid Json");
            }
        }
        return jsonObject;
    }

    private void initPreviousToken(Token token) {
        previousToken = Objects.requireNonNullElseGet(token, () -> new Token(BEGIN_OBJECT, '{'));
    }

    private void checkExpectToken(TokenType tokenType, int expectToken) {
        if (expectToken == -1) {
            return;
        }
        if ((tokenType.getCode() & expectToken) == 0) {
            throw new IllegalArgumentException("Parse error, invalid Json");
        }
    }

}
