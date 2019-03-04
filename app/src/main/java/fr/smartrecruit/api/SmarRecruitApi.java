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

import fr.smartrecruit.controller.ApplicationAdapter;
import fr.smartrecruit.controller.OffersAdapter;
import fr.smartrecruit.data.Applicant;
import fr.smartrecruit.data.DataConstants;
import fr.smartrecruit.data.JobOffer;

public class SmarRecruitApi {

    private final String USER_ID = Applicant.getApplicant().getId();

    private Context context;
    private List<JobOffer> offers = new ArrayList();
    private List<JobOffer> applications = new ArrayList();

    private OffersAdapter offersAdapter;
    private ApplicationAdapter applicationsAdapter;

    public SmarRecruitApi(Context context){
        this.context = context;
    }

    public void requestOffers(){
        Log.d("toto", "tototototoototototot");
        offers.clear();
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/offers";
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

    public void requestApplicantApplications(){
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/applications?applicant="+Applicant.getApplicant().getId();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                JsonArray resultsJsonObject = parser.parse(s).getAsJsonObject().get("job-applications").getAsJsonArray();
                for (int i=0; i<resultsJsonObject.size(); i++){
                    JsonObject jsonObject = resultsJsonObject.get(i).getAsJsonObject();
                    JobOffer jobOffer = getJobOffer(jsonObject);
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

    public void applyToOffer(JobOffer offer, Applicant applicant){
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL +"/apply?applicant="+USER_ID+"&appid="+offer.getId();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonParser parser = new JsonParser();
                String response = parser.parse(s).getAsJsonObject().get("response").getAsString();
                if ("success".equals(response))
                    Toast.makeText(context, "Application Sent !", Toast.LENGTH_SHORT).show();
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
        jobOffer.setContract(jsonObject.get("contract").getAsString());
        jobOffer.setDescription(jsonObject.get("description").getAsString());
        jobOffer.setImg(jsonObject.get("img").getAsString());
        return jobOffer;
    }

    public void setApiAdapter(OffersAdapter adapter){
        this.offersAdapter = adapter;
    }

    public void setApiAdapter(ApplicationAdapter adapter){
        this.applicationsAdapter = adapter;
    }
}
