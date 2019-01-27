package fr.smartrecruit.data;

public class JobOffer {
    private String company;
    private String position;
    private String location;
    private String description;
    private String img;
    private String datePosted;

    public JobOffer(String company, String position, String location, String description, String datePosted, String img) {
        this.company = company;
        this.position = position;
        this.location = location;
        this.description = description;
        this.datePosted = datePosted;
        this.img = img;
    }

    public String getCompany() {
        return company;
    }

    public String getType() {
        return position;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public String getDatePosted() {
        return datePosted;
    }
}
