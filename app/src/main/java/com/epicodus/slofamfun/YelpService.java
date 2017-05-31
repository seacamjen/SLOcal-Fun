package com.epicodus.slofamfun;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YelpService {
    public static final String TAG = YelpService.class.getSimpleName();

    public static void findActivities(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Log.v(TAG, url);

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.YELP_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Activity> processResults(Response response) {
        ArrayList<Activity> activities = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject yelpJSON = new JSONObject(jsonData);
                JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");
                for (int i = 0; i < businessesJSON.length(); i++) {
                    JSONObject activityJSON = businessesJSON.getJSONObject(i);
                    String name = activityJSON.getString("name");
                    String phone = activityJSON.optString("display_phone", "Phone not available");
                    String website = activityJSON.optString("url", "Website not available");
                    double rating = activityJSON.getDouble("rating");
                    String imageUrl = activityJSON.getString("image_url");
                    double latitude = activityJSON.getJSONObject("location")
                            .getJSONObject("coordinate").getDouble("latitiude");
                    double longitude = activityJSON.getJSONObject("location")
                            .getJSONObject("coordinate").getDouble("longitude");
                    ArrayList<String> address = new ArrayList<>();
                    JSONArray addressJSON = activityJSON.getJSONObject("location")
                            .getJSONArray("display_address");
                    for (int y = 0; y < addressJSON.length(); y++) {
                        address.add(addressJSON.get(y).toString());
                    }

                    ArrayList<String> categories = new ArrayList<>();
                    JSONArray categoriesJSON = activityJSON.getJSONArray("categories");

                    for (int y = 0; y < categoriesJSON.length(); y++) {
                        categories.add(categoriesJSON.getJSONArray(y).get(0).toString());
                    }

                    Activity activity = new Activity(name, imageUrl, website, rating, latitude, longitude, address, phone, categories);
                    activities.add(activity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return activities;
    }
}
