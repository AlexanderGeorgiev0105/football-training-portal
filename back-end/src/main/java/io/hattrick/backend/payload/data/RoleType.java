package io.hattrick.backend.payload.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleType {
    GOAKEEPEER("GOAKEEPEER"),
    ATTACKER("ATTACKER"),
    CABLE("CABLE"),
    MACHINE("MACHINE"),
    DEFENDER("DEFENDER");

    private final String value;

    RoleType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static RoleType fromString(String value) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.value.equalsIgnoreCase(value)) {
                return roleType;
            }
        }
        throw new IllegalArgumentException("Invalid RoleType: " + value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
