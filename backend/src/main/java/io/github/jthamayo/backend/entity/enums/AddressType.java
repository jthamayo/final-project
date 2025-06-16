package io.github.jthamayo.backend.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AddressType {
    HOME, WORK;
    
    
    @JsonCreator
    public static AddressType fromString(String key) {
        return key == null ? null : AddressType.valueOf(key.toUpperCase());
    }
}
