package com.dbmigration.demo.utility;

public enum ResponseMessage {

    USER_MIGRATED("User is migrated.."),
    USER_MIGRATION_FAILED("User failed to migrate...");


    private String message;

    private  ResponseMessage(String message){
        this.message=message;
    }

    public String getMessage(){
        return this.message;
    }
}
