package io.github.byference.json;

import io.github.byference.json.modole.JsonObject;
import io.github.byference.json.modole.Token;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Objects;

import static io.github.byference.json.TokenType.BEGIN_OBJECT;
import static io.github.byference.json.TokenType.SEP_COLON;

/**
 * JSON
 *
 * @author byference
 * @since 2020-04-25
 */
public class JSON {

    private final Iterator<Token> tokens;

    private final JsonObject jsonObject;

    private Token previousToken;


    public JSON(String jsonStr) {
        this.tokens = new Tokenizer(jsonStr).tokenize().iterator();
        this.jsonObject = new JsonObject();
    }


    /**
     * 语法分析
     * TODO parse JsonArray
     */
    public JsonObject parseJsonObject() {

        Token token = null;
        String key = null;
        Object value = null;

        while (tokens.hasNext()) {

            initPreviousToken(token);
            token = tokens.next();
            TokenType tokenType = token.getTokenType();
            String tokenValue = token.getValue();

            switch (tokenType) {

                case BEGIN_OBJECT -> {
                    jsonObject.put(key, parseJsonObject());
                }
                case END_OBJECT -> {
                    return jsonObject;
                }
                case STRING -> {
                    TokenType previousTokenType = previousToken.getTokenType();
                    if (previousTokenType == SEP_COLON) {
                        // key
                        value = token.getValue();
                        jsonObject.put(key, value);
                    } else {
                        // value
                        key = token.getValue();
                    }
                }
                case NUMBER -> {
                    jsonObject.put(key, new BigDecimal(tokenValue));
                }
                case NULL -> {
                    jsonObject.put(key, null);
                }
                case BOOLEAN -> {
                    jsonObject.put(key, Boolean.valueOf(tokenValue));
                }
                case SEP_COLON -> {
                }
                case SEP_COMMA -> {
                }
                case END_DOCUMENT -> {
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

}
