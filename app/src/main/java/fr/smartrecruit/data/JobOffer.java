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
    private String interest;
    private String status;


    public JobOffer(){}

    // To Delete after fixing appointments data source
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

    public String getInterest() {
        return interest;
    }

    public OfferStatus getStatus() {
        switch (status){
            case DataConstants.APP_RDV_ATT:
                return new OfferStatus("EN ATTENTE", "#f1c40f");
            case DataConstants.APP_RDV_RECU:
                return new OfferStatus("RENDEZ-VOUS", "#3498db");
            case DataConstants.APP_ACC:
                return new OfferStatus("ACCEPTE", "#2ecc71");
            case DataConstants.APP_REF:
                return new OfferStatus("REFUSE", "#e74c3c");
            default:
                return null;
        }
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

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
