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

import fr.smartrecruit.controller.ApplicationsAdapter;
import fr.smartrecruit.controller.FavoritesAdapter;
import fr.smartrecruit.controller.OffersAdapter;
import fr.smartrecruit.data.Applicant;
import fr.smartrecruit.data.DataConstants;
import fr.smartrecruit.data.JobOffer;
import fr.smartrecruit.data.Recruiter;
import fr.smartrecruit.preview.PreviewAppointment;

public class SmarRecruitApi {

    private final String USER_ID = Applicant.getApplicant().getId();
    private final String RECRUITER_ID = Recruiter.getRecruiter().getId();

    private Context context;
    private List<JobOffer> offers = new ArrayList();
    private List<JobOffer> applications = new ArrayList();
    private List<JobOffer> favorites = new ArrayList();
    private List<PreviewAppointment> recruiterAppointments = new ArrayList();

    private OffersAdapter offersAdapter;
    private ApplicationsAdapter applicationsAdapter;
    private FavoritesAdapter favoritesAdapter;
    //private NotificationsAdapter notificationsAdapter;

    public SmarRecruitApi(Context context){
        this.context = context;
    }

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

    /*public void requestAppointmentsRequests(){
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
                    PreviewAppointment appointment = getAppointment(jsonObject);
                    recruiterAppointments.add(appointment);
                }
                notificationsAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
    }*/

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

    public List<JobOffer> getOffers(){
        return this.offers;
    }

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
        jobOffer.setImg(jsonObject.get("img").getAsString());
        return jobOffer;
    }

    public PreviewAppointment getAppointment(JsonObject jsonObject){
        PreviewAppointment appointment = new PreviewAppointment();
        appointment.setApplicant(jsonObject.get("applicant").getAsString());
        appointment.setLocation(jsonObject.get("location").getAsString());
        appointment.setOffer(jsonObject.get("offer").getAsString());
        appointment.setPosition(jsonObject.get("position").getAsString());
        return appointment;
    }

    public void setApiAdapter(OffersAdapter adapter){
        this.offersAdapter = adapter;
    }
    public void setApiAdapter(ApplicationsAdapter adapter){
        this.applicationsAdapter = adapter;
    }
    public void setApiAdapter(FavoritesAdapter adapter){
        this.favoritesAdapter = adapter;
    }
    //public void setApiAdapter(NotificationsAdapter adapter){this.notificationsAdapter = adapter; }
}
