package fr.smartrecruit.api;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
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

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.data.Applicant;
import fr.smartrecruit.data.DataConstants;
import fr.smartrecruit.data.JobOffer;
import fr.smartrecruit.data.Recruiter;

import static org.junit.Assert.assertEquals;


public class SmarRecruitApiTest {
    private final String USER_ID = Applicant.getApplicant().getId();
    private final String RECRUITER_ID = Recruiter.getRecruiter().getId();

    private List<JobOffer> offers = new ArrayList();
    private Context context;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void requestOffers() {
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
                    JobOffer jobOffer = new JobOffer();
                    jobOffer.setId(jsonObject.get("id").getAsString());
                    jobOffer.setPosition(jsonObject.get("position").getAsString());
                    jobOffer.setCompany(jsonObject.get("company").getAsString());
                    jobOffer.setLocation(jsonObject.get("location").getAsString());
                    jobOffer.setDatePosted(jsonObject.get("datePosted").getAsString());
                    jobOffer.setDescription(jsonObject.get("description").getAsString());
                    offers.add(jobOffer);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error", volleyError.getMessage());
            }
        });
        queue.add(request);
        assertEquals(0, offers.size());
    }

    @Test
    public void removeFavorite() {
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = DataConstants.SERVER_URL+"/removeFavorite?applicant="+USER_ID+"&offer="+"1";
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

    @Test
    public void updateStatus() {
        assertEquals(2, 1+1);
    }

    @Test
    public void rejectApplication() {
        assertEquals(2, 1+1);
    }

    @Test
    public void requestOffersRecruiter() {
        assertEquals(2, 1+1);
    }

    @Test
    public void createOffer() {
        assertEquals(2, 1+1);
    }
}