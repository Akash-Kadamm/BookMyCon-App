package com.dbmigration.demo.utility;

public enum ResponseMessage {

    USER_ALREADY_MIGRATED("User is migrated."),
    USER_MIGRATION_FAILED("User failed to migrate."),
    COMPANY_RECORD_DELETED("Company is deleted from record."),
    ADDRESS_RECORD_SAVED("Address is saved."),
    CARD_DETAILS_NOT_FOUND("Card details for this card detail is not present."),
    CARD_DETAILS_NOT_SAVED("card details not saved."),
    CARD_DETAILS_ARE_DELETED("Card details are deleted."),
    CARD_DETAILS_SAVED("Card details saved."),
    CARD_DETAILS_UPDATED("Card details updated."),
    USER_NOT_FOUND("User is not found."),
    USER_DELETED("User deleted."),
    ADDRESS_RECORD_DELETED("Address deleted."),
    DEPARTMENT_RECORD_DELETED("department is deleted."),
    DEPARTMENT_RECORD_SAVED("department is saved.");


    private String message;

    private ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
    }
