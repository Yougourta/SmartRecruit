package fr.smartrecruit.viewmodel;

import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.data.JobOffer;

public class ApplicationsViewModel {
    private List<JobOffer> offers = new ArrayList();
    private final String DUMMY_TEXT = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    private final String DUMMY_IMAGE = "https://cdn.pixabay.com/photo/2016/03/28/00/37/flat-1284770_960_720.png";
    private final String DUMMY_COMPANY = "Company #";
    private final String DUMMY_POSITION = "Offer #";
    private final String DUMMY_LOCATION = "Location #";
    private final String DUMMY_POSTED_DATE = "Posted 1 day ago";

    public List<JobOffer> getRandomOffers(){
        JobOffer offer = new JobOffer(DUMMY_COMPANY, DUMMY_POSITION, DUMMY_LOCATION, DUMMY_TEXT, DUMMY_POSTED_DATE, DUMMY_IMAGE);
        for(int i=0; i<1; i++){ offers.add(offer); }
        return offers;
    }

    public List<JobOffer> getOffers(){ return offers; }

    public JobOffer createRandomOffer(){
        return new JobOffer("Added row", "Added row","Added row", DUMMY_TEXT, DUMMY_POSTED_DATE, "http://www.bluthemes.com/themes/calypso/wp-content/uploads/sites/17/2015/01/101.jpg");
    }
}
