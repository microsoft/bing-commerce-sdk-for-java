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
 * Defines values for IndexFieldType.
 */
public enum IndexFieldType {
    /** Enum value Unknown. */
    UNKNOWN("Unknown"),

    /** Enum value String. */
    STRING("String"),

    /** Enum value Boolean. */
    BOOLEAN("Boolean"),

    /** Enum value Number. */
    NUMBER("Number"),

    /** Enum value ProductId. */
    PRODUCT_ID("ProductId"),

    /** Enum value DupId. */
    DUP_ID("DupId"),

    /** Enum value StaticRank. */
    STATIC_RANK("StaticRank"),

    /** Enum value Url. */
    URL("Url"),

    /** Enum value ImageUrl. */
    IMAGE_URL("ImageUrl"),

    /** Enum value Title. */
    TITLE("Title"),

    /** Enum value Description. */
    DESCRIPTION("Description"),

    /** Enum value Category. */
    CATEGORY("Category"),

    /** Enum value Price. */
    PRICE("Price"),

    /** Enum value Rating. */
    RATING("Rating"),

    /** Enum value Brand. */
    BRAND("Brand"),

    /** Enum value Model. */
    MODEL("Model"),

    /** Enum value Color. */
    COLOR("Color"),

    /** Enum value Size. */
    SIZE("Size"),

    /** Enum value Material. */
    MATERIAL("Material"),

    /** Enum value Gender. */
    GENDER("Gender"),

    /** Enum value AgeGroup. */
    AGE_GROUP("AgeGroup"),

    /** Enum value Array. */
    ARRAY("Array"),

    /** Enum value Dictionary. */
    DICTIONARY("Dictionary"),

    /** Enum value ExcludeFlag. */
    EXCLUDE_FLAG("ExcludeFlag"),

    /** Enum value Identifier. */
    IDENTIFIER("Identifier"),

    /** Enum value Object. */
    OBJECT("Object"),

    /** Enum value DocumentId. */
    DOCUMENT_ID("DocumentId"),

    /** Enum value Author. */
    AUTHOR("Author"),

    /** Enum value CreatedAt. */
    CREATED_AT("CreatedAt"),

    /** Enum value ModifiedAt. */
    MODIFIED_AT("ModifiedAt"),

    /** Enum value Paragraph. */
    PARAGRAPH("Paragraph"),

    /** Enum value SubHeading. */
    SUB_HEADING("SubHeading"),

    /** Enum value SectionHeader. */
    SECTION_HEADER("SectionHeader"),

    /** Enum value Address. */
    ADDRESS("Address"),

    /** Enum value RatingCount. */
    RATING_COUNT("RatingCount"),

    /** Enum value ReviewCount. */
    REVIEW_COUNT("ReviewCount"),

    /** Enum value RatingScale. */
    RATING_SCALE("RatingScale"),

    /** Enum value Amenities. */
    AMENITIES("Amenities"),

    /** Enum value StreetAddress. */
    STREET_ADDRESS("StreetAddress"),

    /** Enum value Locality. */
    LOCALITY("Locality"),

    /** Enum value SubRegion. */
    SUB_REGION("SubRegion"),

    /** Enum value AddressRegion. */
    ADDRESS_REGION("AddressRegion"),

    /** Enum value PostalCode. */
    POSTAL_CODE("PostalCode"),

    /** Enum value PostOfficeBoxNumber. */
    POST_OFFICE_BOX_NUMBER("PostOfficeBoxNumber"),

    /** Enum value Country. */
    COUNTRY("Country"),

    /** Enum value CountryIso. */
    COUNTRY_ISO("CountryIso"),

    /** Enum value Neighborhood. */
    NEIGHBORHOOD("Neighborhood"),

    /** Enum value OtherAreas. */
    OTHER_AREAS("OtherAreas"),

    /** Enum value PhoneNumber. */
    PHONE_NUMBER("PhoneNumber"),

    /** Enum value Barcode. */
    BARCODE("Barcode"),

    /** Enum value SecondaryImageUrls. */
    SECONDARY_IMAGE_URLS("SecondaryImageUrls");

    /** The actual serialized value for a IndexFieldType instance. */
    private String value;

    IndexFieldType(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a IndexFieldType instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed IndexFieldType object, or null if unable to parse.
     */
    @JsonCreator
    public static IndexFieldType fromString(String value) {
        IndexFieldType[] items = IndexFieldType.values();
        for (IndexFieldType item : items) {
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
