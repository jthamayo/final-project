package io.github.jthamayo.backend.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AllergyType {
    PEANUTS, TREE_NUTS, MILK, EGGS, GLUTEN, INSECT_STINGS, MEDICATIONS, OTHER;

    @JsonCreator
    public static AllergyType fromString(String key) {
	return key == null ? null : AllergyType.valueOf(key.toUpperCase());
    }
}
