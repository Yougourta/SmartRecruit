package fr.smartrecruit.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.controller.candidat.ApplicationsAdapter;
import fr.smartrecruit.controller.candidat.AppointmentsAdapter;
import fr.smartrecruit.controller.candidat.FavoritesAdapter;
import fr.smartrecruit.controller.candidat.OffersAdapter;
import fr.smartrecruit.controller.recruiter.MyAppointmentsAdapter;
import fr.smartrecruit.controller.recruiter.MyOffersAdapter;
import fr.smartrecruit.controller.recruiter.MyScheduledAppointmentsAdapter;
import fr.smartrecruit.data.Applicant;
import fr.smartrecruit.data.Appointment;
import fr.smartrecruit.data.DataConstants;
import fr.smartrecruit.data.JobOffer;
import fr.smartrecruit.data.RecAppointment;
import fr.smartrecruit.data.Recruiter;

public class SmarRecruitApi {

    private final String USER_ID = Applicant.getApplicant().getId();
    private final String RECRUITER_ID = Recruiter.getRecruiter().getId();

    private Context context;
    private List<JobOffer> offers = new ArrayList();
    private List<JobOffer> applications = new ArrayList();
    private List<JobOffer> favorites = new ArrayList();
    private List<RecAppointment> recruiterAppointments = new ArrayList();
    private List<Appointment> applicantAppointments = new ArrayList();
    private List<RecAppointment> recruiterScheduledAppointments = new ArrayList();
    private List<JobOffer> myOffers = new ArrayList();

    private OffersAdapter offersAdapter;
    private ApplicationsAdapter applicationsAdapter;
    private FavoritesAdapter favoritesAdapter;
    private MyAppointmentsAdapter recruiterAppointmentsAdapter;
    private MyOffersAdapter myOffersAdapter;
    private MyScheduledAppointmentsAdapter myScheduledAppointmentsAdapter;
    private AppointmentsAdapter appointmentsAdapter;

    public SmarRecruitApi(Context context){
        this.context = context;
    }


    /** Applicant requests section **/
    public void requestOffers(){
        offers.clear();
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/offers?applicant="+USER_ID;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                JsonArray resultsJsonObject = parser.parse(s).getAsJsonObject().get("job-offers").getAsJsonArray();
                for (int i=0; i<resultsJsonObject.size(); i++){
                    JsonObject jsonObject = resultsJsonObject.get(i).getAsJsonObject();
                    JobOffer jobOffer = getJobOffer(jsonObject);
                    offers.add(jobOffer);
                }
                offersAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }
    public void requestFavorites(){
        favorites.clear();
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/favorites?applicant="+USER_ID;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                JsonArray resultsJsonObject = parser.parse(s).getAsJsonObject().get("job-offers").getAsJsonArray();
                for (int i=0; i<resultsJsonObject.size(); i++){
                    JsonObject jsonObject = resultsJsonObject.get(i).getAsJsonObject();
                    JobOffer jobOffer = getJobOffer(jsonObject);
                    favorites.add(jobOffer);
                }
                favoritesAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }
    public void requestApplications(){
        applications.clear();
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/applications?applicant="+USER_ID;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                JsonArray resultsJsonObject = parser.parse(s).getAsJsonObject().get("job-applications").getAsJsonArray();
                for (int i=0; i<resultsJsonObject.size(); i++){
                    JsonObject jsonObject = resultsJsonObject.get(i).getAsJsonObject();
                    JobOffer jobOffer = getJobOffer(jsonObject);
                    jobOffer.setStatus(jsonObject.get("etat").getAsString());
                    applications.add(jobOffer);
                }
                applicationsAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }
    public void removeFavorite(String idOffer){
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/removeFavorite?applicant="+USER_ID+"&offer="+idOffer;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                String response = parser.parse(s).getAsJsonObject().get("response").getAsString();
                if ("success".equals(response))
                    Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
                else if ("error".equals(response))
                    Toast.makeText(context, "An error occurred x(", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }
    public void updateStatus(String idOffer, final String status){
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/updateStatus?status="+status+"&applicant="+USER_ID+"&offer="+idOffer;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                String response = parser.parse(s).getAsJsonObject().get("response").getAsString();
                if ("success".equals(response)){
                    switch (status){
                        case DataConstants.DELETED:
                            Toast.makeText(context, "Offer removed", Toast.LENGTH_SHORT).show();
                            break;
                        case DataConstants.APP_RDV_ATT:
                            Toast.makeText(context, "Application sent", Toast.LENGTH_SHORT).show();
                            break;
                        case DataConstants.APP_RDV_RECU:
                            Toast.makeText(context, "Appointment scheduled", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
                else if ("error".equals(response))
                    Toast.makeText(context, "An error occurred x(", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }
    public void addFavorite(String idOffer){
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/addFavorite?applicant="+USER_ID+"&offer="+idOffer;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                String response = parser.parse(s).getAsJsonObject().get("response").getAsString();
                if ("success".equals(response))
                    Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
                else if ("error".equals(response))
                    Toast.makeText(context, "An error occurred x(", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }
    public void requestApplicantScheduledAppointments(){
        applicantAppointments.clear();
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/applicantAppointments?applicant="+USER_ID;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                JsonArray resultsJsonObject = parser.parse(s).getAsJsonObject().get("job-appointments").getAsJsonArray();
                for (int i=0; i<resultsJsonObject.size(); i++){
                    JsonObject jsonObject = resultsJsonObject.get(i).getAsJsonObject();
                    Appointment appointment = getApplicantAppointment(jsonObject);
                    applicantAppointments.add(appointment);
                }
                appointmentsAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }

    /** Recruiter requests section **/
    public void requestRecruiterAppointments(){
        recruiterAppointments.clear();
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/appointmentRequests?recruiter="+RECRUITER_ID;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                JsonArray resultsJsonObject = parser.parse(s).getAsJsonObject().get("appointments-requests").getAsJsonArray();
                for (int i=0; i<resultsJsonObject.size(); i++){
                    JsonObject jsonObject = resultsJsonObject.get(i).getAsJsonObject();
                    RecAppointment appointment = getRecAppointment(jsonObject);
                    recruiterAppointments.add(appointment);
                }
                recruiterAppointmentsAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }
    public void rejectApplication(String offerId){
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/rejectApplication?applicant="+USER_ID+"&offer="+offerId;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                String response = parser.parse(s).getAsJsonObject().get("response").getAsString();
                if ("success".equals(response))
                    Toast.makeText(context, "Application Rejected", Toast.LENGTH_SHORT).show();
                else if ("error".equals(response))
                    Toast.makeText(context, "An error occurred x(", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }
    public void setAppointment(final String offer, String date, String time){
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/scheduleAppointment?recruiter="+RECRUITER_ID+"&applicant="+USER_ID+"&offer="+offer+"&date="+date+"&time="+time;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                String response = parser.parse(s).getAsJsonObject().get("response").getAsString();
                if ("success".equals(response))
                    updateStatus(offer, DataConstants.APP_RDV_RECU);
                else if ("error".equals(response))
                    Toast.makeText(context, "An error occurred x(", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }
    public void requestOffersRecruiter(){
        myOffers.clear();
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/offersRecuiter?recruiter="+USER_ID;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                JsonArray resultsJsonObject = parser.parse(s).getAsJsonObject().get("my-offers").getAsJsonArray();
                for (int i=0; i<resultsJsonObject.size(); i++){
                    JsonObject jsonObject = resultsJsonObject.get(i).getAsJsonObject();
                    JobOffer jobOfferRecruiter = getJobOffer(jsonObject);
                    myOffers.add(jobOfferRecruiter);
                }
                myOffersAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }
    public void requestRecruiterScheduledAppointments(){
        recruiterScheduledAppointments.clear();
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/recruiterAppointments?recruiter="+RECRUITER_ID;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                JsonArray resultsJsonObject = parser.parse(s).getAsJsonObject().get("job-appointments").getAsJsonArray();
                for (int i=0; i<resultsJsonObject.size(); i++){
                    JsonObject jsonObject = resultsJsonObject.get(i).getAsJsonObject();
                    RecAppointment appointment = getRecSchedAppointment(jsonObject);
                    recruiterScheduledAppointments.add(appointment);
                }
                myScheduledAppointmentsAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }
    public void createOffer(JobOffer offer){
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/addOffreRecuiter?recruiter="+RECRUITER_ID+"&offer="+offer.getId()+"&position="+offer.getPosition()+"&company="+offer.getCompany()+"&location="+offer.getLocation()+"&date="+offer.getDatePosted()+"&desc="+offer.getDescription();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                String response = parser.parse(s).getAsJsonObject().get("response").getAsString();
                if ("success".equals(response))
                    Toast.makeText(context, "Offer created successfully", Toast.LENGTH_SHORT).show();
                else if ("error".equals(response))
                    Toast.makeText(context, "An error occurred x(", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }

    /** Applicant methods **/
    public List<JobOffer> getFavoriteOffers(){
        return this.favorites;
    }
    public List<JobOffer> getApplications(){
        return this.applications;
    }
    public JobOffer getJobOffer(JsonObject jsonObject){
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(jsonObject.get("id").getAsString());
        jobOffer.setPosition(jsonObject.get("position").getAsString());
        jobOffer.setCompany(jsonObject.get("company").getAsString());
        jobOffer.setLocation(jsonObject.get("location").getAsString());
        jobOffer.setDatePosted(jsonObject.get("datePosted").getAsString());
        jobOffer.setDescription(jsonObject.get("description").getAsString());
        return jobOffer;
    }
    public List<Appointment> getApplicantScheduledAppointments(){ return  applicantAppointments; }
    public Appointment getApplicantAppointment(JsonObject jsonObject){
        Appointment appointment = new Appointment();
        appointment.setCompany(jsonObject.get("company").getAsString());
        appointment.setPosition(jsonObject.get("position").getAsString());
        appointment.setLocation(jsonObject.get("location").getAsString());
        appointment.setHour(jsonObject.get("hour").getAsString());
        appointment.setDay(jsonObject.get("day").getAsString());
        return appointment;
    }
    public List<JobOffer> getOffers(){
        return this.offers;
    }

    /** Recruiter methods**/
    public RecAppointment getRecAppointment(JsonObject jsonObject){
        RecAppointment appointment = new RecAppointment();
        appointment.setApplicant(jsonObject.get("applicant").getAsString());
        appointment.setPosition(jsonObject.get("position").getAsString());
        appointment.setOffer(jsonObject.get("offer").getAsString());
        return appointment;
    }
    public RecAppointment getRecSchedAppointment(JsonObject jsonObject){
        RecAppointment appointment = new RecAppointment();
        appointment.setApplicant(jsonObject.get("applicant").getAsString());
        appointment.setPosition(jsonObject.get("position").getAsString());
        appointment.setDay(jsonObject.get("day").getAsString());
        appointment.setHour(jsonObject.get("hour").getAsString());
        return appointment;
    }
    public List<RecAppointment> getRecruiterAppointments(){ return  recruiterAppointments; }
    public List<RecAppointment> getRecruiterScheduledAppointments(){ return  recruiterScheduledAppointments; }
    public List<JobOffer> getMyOffers(){
        return this.myOffers;
    }

    /** adapters **/
    public void setApiAdapter(OffersAdapter adapter){
        this.offersAdapter = adapter;
    }
    public void setApiAdapter(ApplicationsAdapter adapter){
        this.applicationsAdapter = adapter;
    }
    public void setApiAdapter(FavoritesAdapter adapter){
        this.favoritesAdapter = adapter;
    }
    public void setApiAdapter(MyAppointmentsAdapter adapter){this.recruiterAppointmentsAdapter = adapter; }
    public void setApiAdapter(MyOffersAdapter adapter){
        this.myOffersAdapter = adapter;
    }
    public void setApiAdapter(MyScheduledAppointmentsAdapter adapter){ this.myScheduledAppointmentsAdapter = adapter; }
    public void setApiAdapter(AppointmentsAdapter adapter){ this.appointmentsAdapter = adapter; }
}
