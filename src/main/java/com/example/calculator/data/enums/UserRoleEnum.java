package com.example.calculator.data.enums;

public enum UserRoleEnum {
    ADMIN("Admin"),
    MODERATOR("Moderator"),
    USER("User");

    private final String displayText;

    UserRoleEnum(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
