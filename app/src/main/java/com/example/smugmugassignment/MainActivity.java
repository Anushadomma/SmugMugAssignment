package com.example.smugmugassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.smugmugassignment.Adapter.RecyclerViewAdapter;
import com.example.smugmugassignment.model.DataModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DataModel> recyclerDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerDataArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.idRecycler);

        // get data from assesrts folder
        try {
            JSONObject object = new JSONObject(readJSON());
            JSONArray array = object.getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);
                String trackName = jsonObject.getString("trackName");
                String releaseDate = jsonObject.getString("releaseDate");
                String shortDescription = jsonObject.getString("shortDescription");
                String artworkUrl100 = jsonObject.getString("artworkUrl100");
                // String email = jsonObject.getString("email");

                DataModel model = new DataModel();
                model.setTrackName(trackName);
                model.setReleaseDate(releaseDate);
                model.setShortDescription(shortDescription);
                model.setArtworkUrl100(artworkUrl100);
                recyclerDataArrayList.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(recyclerDataArrayList, MainActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
    }

    public String readJSON() {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }




}