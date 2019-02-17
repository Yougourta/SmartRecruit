package fr.smartrecruit.data;

import java.util.ArrayList;
import java.util.List;

public class Applicant {
    private String id;
    private String usr;
    private String pwd;
    private ApplicantProfile profile;
    private List<JobOffer> appliedOffers;
    private static Applicant applicant;

    private Applicant (String id, String usr,  String pwd, List<JobOffer> appliedOffers){
        this.id = id;
        this.usr = usr;
        this.pwd = pwd;
        this.appliedOffers = appliedOffers;
    }

    public static Applicant getApplicant(){
        if(applicant==null)
            applicant = new Applicant("usr29183", "SmartRecruit", "has-1234", new ArrayList<JobOffer>());
        return  applicant;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public String getUsr() {
        return usr;
    }

    public ApplicantProfile getProfile() {
        return profile;
    }

    public List<JobOffer> getAppliedOffers() {
        return appliedOffers;
    }
}