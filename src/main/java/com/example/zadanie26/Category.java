package com.example.zadanie26;

public enum Category {
    CAKE("Ciasta"), DINNER("Dania główne"), SALAD("Sałatki");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
