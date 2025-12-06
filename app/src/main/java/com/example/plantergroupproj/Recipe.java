package com.example.plantergroupproj;

public class Recipe {
    private String title;
    private String shortDescription;
    private String fullDescription;
    private String plantName;
    private int imageResId;
    private String id;

    // Constructors
    public Recipe(String title, String shortDescription, String fullDescription, int imageResId, String plantName) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.imageResId = imageResId;
        this.plantName = plantName;
    }
    public Recipe() {}

    // Getters
    public String getTitle() { return title; }
    public String getShortDescription() { return shortDescription; }
    public String getFullDescription() { return fullDescription; }
    public int getImageResId() { return imageResId; }
    public String getPlantName() { return plantName; }
    public String getId() { return id; }

    // Setter for Firebase ID
    public void setId(String id) { this.id = id; }
}
