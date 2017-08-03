package com.example.vaishnavj.phonesy;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by user on 3/5/2017.
 */

public class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {
    JSONObject jObject;



    Context context ;
    ParserTask(Context c)
    {
        context = c;
    }


    // Invoked by execute() method of this object
    @Override
    protected List<HashMap<String, String>> doInBackground(String... jsonData) {


        List<HashMap<String, String>> places = null;
        Place_JSON placeJson = new Place_JSON();

        try {
            jObject = new JSONObject(jsonData[0]);

            places = placeJson.parse(jObject);

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
        return places;
    }

    // Executed after the complete execution of doInBackground() method
    @Override
    protected void onPostExecute(List<HashMap<String, String>> list) {

        Log.i("Map", "list size: " + list.size());

        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences("Phone",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        // Clears all the existing markers;
      //  mGoogleMap.clear();
if(list.size() == 0){
    AudioManager audio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
    audio.setRingerMode(2);
    Log.i("Normal","done");
    editor.putString("state","on");
    editor.commit();
}
        else
{
    AudioManager audio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
    audio.setRingerMode(0);
    Log.i("silent","done");
    editor.putString("state","on");
    editor.commit();
}

        for (int i = 0; i < list.size(); i++) {

            // Creating a marker
          //  MarkerOptions markerOptions = new MarkerOptions();

            // Getting a place from the places list
            HashMap<String, String> hmPlace = list.get(i);


            // Getting latitude of the place
            double lat = Double.parseDouble(hmPlace.get("lat"));

            // Getting longitude of the place
            double lng = Double.parseDouble(hmPlace.get("lng"));

            // Getting name
            String name = hmPlace.get("place_name");

            Log.i("Map", "place: " + name);
            Toast.makeText(context,name,Toast.LENGTH_SHORT).show();

            // Getting vicinity
            String vicinity = hmPlace.get("vicinity");
            Log.i("Map", "Vicinity"+ vicinity);

           // LatLng latLng = new LatLng(lat, lng);

            // Setting the position for the marker
         //   markerOptions.position(latLng);

           // markerOptions.title(name + " : " + vicinity);

           // markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

            // Placing a marker on the touched position
            //Marker m = mGoogleMap.addMarker(markerOptions);

        }
    }
    public class Place_JSON {

        /**
         * Receives a JSONObject and returns a list
         */
        public List<HashMap<String, String>> parse(JSONObject jObject) {

            JSONArray jPlaces = null;
            try {
                /** Retrieves all the elements in the 'places' array */
                jPlaces = jObject.getJSONArray("results");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            /** Invoking getPlaces with the array of json object
             * where each json object represent a place
             */
            return getPlaces(jPlaces);
        }

        private List<HashMap<String, String>> getPlaces(JSONArray jPlaces) {
            int placesCount = jPlaces.length();
            List<HashMap<String, String>> placesList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> place = null;

            /** Taking each place, parses and adds to list object */
            for (int i = 0; i < placesCount; i++) {
                try {
                    /** Call getPlace with place JSON object to parse the place */
                    place = getPlace((JSONObject) jPlaces.get(i));
                    placesList.add(place);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return placesList;
        }

        /**
         * Parsing the Place JSON object
         */
        private HashMap<String, String> getPlace(JSONObject jPlace) {

            HashMap<String, String> place = new HashMap<String, String>();
            String placeName = "-NA-";
            String vicinity = "-NA-";
            String latitude = "";
            String longitude = "";
            String reference = "";

            try {
                // Extracting Place name, if available
                if (!jPlace.isNull("name")) {
                    placeName = jPlace.getString("name");
                }

                // Extracting Place Vicinity, if available
                if (!jPlace.isNull("vicinity")) {
                    vicinity = jPlace.getString("vicinity");
                }

                latitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lat");
                longitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");
                reference = jPlace.getString("reference");

                place.put("place_name", placeName);
                place.put("vicinity", vicinity);
                place.put("lat", latitude);
                place.put("lng", longitude);
                place.put("reference", reference);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return place;
        }
    }
}
