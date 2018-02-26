package com.example.ricky52194.gitreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String processData(String filename) {
        String jsonString = "";
        boolean isFilePresent = JsonUtils.isFilePresent(this, filename);

        if (isFilePresent) {
            jsonString = JsonUtils.read(this, filename);

            Log.i("JSON", "JSON was present");

        } else {
            Log.i("JSON", "JSON file not present. Creating......");
            InputStream inputStream = null;

            try {
                inputStream = getApplicationContext().getAssets().open("gitReference.json");
            } catch (Exception ex) {
                System.out.println("Exception Occurred");
            }
            jsonString = JsonUtils.parseJsonToString(inputStream);
            boolean isFileCreated = JsonUtils.create(this, filename, jsonString);
            if (isFileCreated) {
                Log.i("JSON", "Created the filesystem JSON");
            } else {

            }
        }
        return jsonString;
    }

}
