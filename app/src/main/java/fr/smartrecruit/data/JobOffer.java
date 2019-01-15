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

    public void setType(String type) {
        this.type = type;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
