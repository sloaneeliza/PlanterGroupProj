package com.example.plantergroupproj;

public class Recipe {
    // updating stuff to be in a database
    private String title;
    private String shortDescription;
    private String fullDescription;

    private int imageResId;

    public static final String TITLE_KEY  = "TITLEV";
    public Recipe(String title, String shortDescription, String fullDescription, int imageResId) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.imageResId = imageResId;
    }
    public Recipe() {}

    public String getTitle() {
        return title;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public String getFullDescription() {
        return fullDescription;
    }
    public int getImageResId() {
        return imageResId;
    }
}
