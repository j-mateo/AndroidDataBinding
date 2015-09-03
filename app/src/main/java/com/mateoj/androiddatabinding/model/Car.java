package com.mateoj.androiddatabinding.model;

/**
 * Created by jose on 9/3/15.
 */
public class Car {
    private String year;
    private String make;
    private String model;
    private String description;
    private String imageUrl;
    private String price;
    private String mileage;

    public Car(String year, String make, String model, String mileage, String price,
               String description, String image)
    {
        this.year = year;
        this.make = make;
        this.model = model;
        this.description = description;
        this.mileage = mileage;
        this.price = price;
        this.imageUrl = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getDisplayName() {
        return year + " " + make + " " + model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
