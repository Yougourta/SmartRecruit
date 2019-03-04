package fr.smartrecruit.data;

import java.util.ArrayList;
import java.util.List;

public class Recruiter {
    private String id;
    private String usr;
    private String pwd;
    private static Recruiter recruiter;

    private Recruiter (String id, String usr,  String pwd, List<JobOffer> appliedOffers){
        this.id = id;
        this.usr = usr;
        this.pwd = pwd;
    }

    public static Recruiter getRecruiter(){
        if(recruiter==null)
            recruiter = new Recruiter("1", "SmartRecruitRecruiter", "hash-1234", new ArrayList<JobOffer>());
        return recruiter;
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
}
