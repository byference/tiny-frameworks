package io.github.byference.json.modole;

import io.github.byference.json.TokenType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Token
 *
 * @author byference
 * @since 2020-04-19
 */
@Data
@NoArgsConstructor
public class Token {

    /**
     * tokenType
     */
    private TokenType tokenType;

    /**
     * token content
     */
    private String value;

    public Token(TokenType tokenType, String value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    public Token(TokenType tokenType, char ch) {
        this.tokenType = tokenType;
        this.value = String.valueOf(ch);
    }
}
