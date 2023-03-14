package com.dbMigration.demo.utility;

public enum ResponseMessage {

    USER_MIGRATED("User is migrated.."),
    USER_MIGRATION_FAILED("User failed to migrate..."),
    COMPANY_RECORD_DELETED("Company is deleted from record...."),
    ADDRESS_RECORD_SAVED("Address is saved...."),
    DEPARTMENT_RECORD_SAVED("department is saved...");


    private String message;

    private ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
