package br.com.mkacunha.datetime.datetimeserver.infrastructure;

import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

public class Preconditions {

    private Preconditions() {
    }

    public static void checkRequiredField(String value, String message) {
        if (!hasText(value)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkRequiredField(long value, String message) {
        if (value <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkRequiredField(List value, String message) {
        if (isNull(value) || value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkRequiredField(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

}
