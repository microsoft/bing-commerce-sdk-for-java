// Copyright (c) Microsoft Corporation.
// Licensed under the MIT license.

/**
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.bing.commerce.ingestion.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for ResponsePushStatus.
 */
public enum ResponsePushStatus {
    /** Enum value Queued. */
    QUEUED("Queued"),

    /** Enum value Error. */
    ERROR("Error");

    /** The actual serialized value for a ResponsePushStatus instance. */
    private String value;

    ResponsePushStatus(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a ResponsePushStatus instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed ResponsePushStatus object, or null if unable to parse.
     */
    @JsonCreator
    public static ResponsePushStatus fromString(String value) {
        ResponsePushStatus[] items = ResponsePushStatus.values();
        for (ResponsePushStatus item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
