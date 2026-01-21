package io.hattrick.backend.payload.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FootballGroup {
    DRIBBLING("DRIBBLING"),
    SKILLS("SKILLS"),
    DEFENDING("DEFENDING"),
    SHOOTING("SHOOTING"),
    CELEBRATIONS("CELEBRATIONS"),
    PENALTIES("PENALTIES"),
    PASSING("PASSING"),
    FKICKS("FKICKS");

    private final String value;

    FootballGroup(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FootballGroup fromString(String value) {
        for (FootballGroup footballGroup : FootballGroup.values()) {
            if (footballGroup.value.equalsIgnoreCase(value)) {
                return footballGroup;
            }
        }
        throw new IllegalArgumentException("Invalid FootballGroup: " + value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
