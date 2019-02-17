package fr.smartrecruit.data;

import java.io.Serializable;

public class JobOffer implements Serializable {
    private String id;
    private String company;
    private String position;
    private String location;
    private String contract;
    private String description;
    private String img;
    private String datePosted;

    public JobOffer(){}

    public JobOffer(String company, String position, String location, String description, String datePosted, String img) {
        this.company = company;
        this.position = position;
        this.location = location;
        this.description = description;
        this.datePosted = datePosted;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

    public String getLocation() {
        return location;
    }

    public String getContract() {
        return contract;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public boolean equals(JobOffer offer) {
        return this.id.equals(offer.getId());
    }
}
