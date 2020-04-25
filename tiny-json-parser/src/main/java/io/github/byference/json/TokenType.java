package io.github.byference.json;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {

    /**
     * BEGIN_OBJECT（{）
     */
    BEGIN_OBJECT(1),

    /**
     * END_OBJECT（}）
     */
    END_OBJECT(2),

    /**
     * BEGIN_ARRAY（[）
     */
    BEGIN_ARRAY(4),

    /**
     * END_ARRAY（]）
     */
    END_ARRAY(8),

    /**
     * NULL（null）
     */
    NULL(16),

    /**
     * NUMBER（数字）
     */
    NUMBER(32),

    /**
     * STRING（字符串）
     */
    STRING(64),

    /**
     * BOOLEAN（true/false）
     */
    BOOLEAN(128),

    /**
     * SEP_COLON（:）
     */
    SEP_COLON(256),

    /**
     * SEP_COMMA（,）
     */
    SEP_COMMA(512),

    /**
     * END_DOCUMENT
     */
    END_DOCUMENT(1024);

    /**
     * token code
     */
    private final int code;

}