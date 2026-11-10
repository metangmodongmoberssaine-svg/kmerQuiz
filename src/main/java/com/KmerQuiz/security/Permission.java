package com.KmerQuiz.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public enum Permission {
    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    USER_CREATE("user:create"),
    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete"),

    QUIZ_CREATE("quiz:create"),
    QUIZ_READ("quiz:read"),
    QUIZ_UPDATE("quiz:update"),
    QUIZ_DELETE("quiz:delete"),

    QUESTION_CREATE("question:create"),
    QUESTION_READ("question:read"),
    QUESTION_UPDATE("question:update"),
    QUESTION_DELETE("question:delete"),

    PARTY_CREATE("party:create"),
    PARTY_READ("party:READ"),
    PARTY_UPDATE("party:update"),
    PARTY_DELETE("party:delete"),
    RESPONSE_CREATE("response:create"),
    RESPONSE_READ("response:read"),
    RESPONSE_UPDATE("response:update"),
    RESPONSE_DELETE("response:delete"),

    CATEGORY_CREATE("category:create"),
    CATEGORY_READ("category:read"),
    CATEGORY_UPDATE("category:update"),
    CATEGORY_DELETE("category:delete"),

    private final String permission;
}
