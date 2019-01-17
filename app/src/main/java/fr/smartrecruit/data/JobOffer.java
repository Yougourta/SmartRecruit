package fr.smartrecruit.data;

public class JobOffer {
    private String company;
    private String type;
    private String location;
    private String description;
    private int img;

    public JobOffer(String company, String type, String location, String description, int img) {
        this.company = company;
        this.type = type;
        this.location = location;
        this.description = description;
        this.img = img;
    }

    public String getCompany() {
        return company;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public int getImg() {
        return img;
    }
}
