package com.hoomgroom.delivery.enums;

import lombok.Getter;
import lombok.Generated;

@Getter
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    private final String value;

    @Generated
    private Gender(String value) { this.value = value; }
}
