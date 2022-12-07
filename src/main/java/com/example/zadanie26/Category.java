package com.example.zadanie26;

public enum Category {
    CAKE("Ciasta", "Słodkie przekąski", "cake.jpg"),
    DINNER("Dania główne", "Obiad pełnowartościowy", "dinner.jpg"),
    SALAD("Sałatki", "Lekkie przekąski", "salad.jpg");

    private String displayName;
    private String description;
    private String img;

    Category(String displayName, String description, String img) {
        this.displayName = displayName;
        this.description = description;
        this.img = img;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }
}
