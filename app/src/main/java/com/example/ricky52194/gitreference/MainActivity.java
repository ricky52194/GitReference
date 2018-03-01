package com.example.ricky52194.gitreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private customAdapter adapter, svAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.ListView01);
        final ArrayList<GitReference> gitItems = populateData(getString(R.string.appGitReference_json));
        adapter = new customAdapter(this, gitItems);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);

        //Adding SearchView
        SearchView search = findViewById(R.id.search);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String str) {
                int strLen = str.length();
                ArrayList<GitReference> searchList = new ArrayList<>();
                if (TextUtils.isEmpty(str)){
                    listView.clearTextFilter();
                }else{
                    listView.setFilterText(str);
                }
                for(GitReference g: gitItems){
                    if (strLen <= g.getSection().length()) {
                        if (g.getSection().toLowerCase().contains(str.toLowerCase())) {
                            searchList.add(g);
                        }
                    }
                }
                svAdapter = new customAdapter(getApplicationContext(), searchList);
                listView.setAdapter(svAdapter);
                return false;
            }
        });
    }

    public ArrayList<GitReference> populateData(String jsonFileName) {
        String jsonString = processData(jsonFileName);
        return JsonUtils.populateGitReferences(jsonString);
    }

    public String processData(String filename) {
        String jsonString = "";
        boolean isFilePresent = JsonUtils.isFilePresent(this, filename);

        if (isFilePresent) {
            jsonString = JsonUtils.read(this, filename);

            Log.i(getString(R.string.JSON_tag), getString(R.string.JSON_present_tag));

        } else {
            Log.i(getString(R.string.JSON_tag), getString(R.string.JSON__not_present_tag));
            InputStream inputStream = null;

            try {
                inputStream = getApplicationContext().getAssets().open(getString(R.string.gitReference_json_tag));
            } catch (Exception ex) {
                System.out.println(getString(R.string.exception_occurred_tag));
            }
            jsonString = JsonUtils.parseJsonToString(inputStream);
            boolean isFileCreated = JsonUtils.create(this, filename, jsonString);
            if (isFileCreated) {
                Log.i(getString(R.string.JSON_tag), getString(R.string.filesystemCreatedTag));
            } else {
                Log.i(getString(R.string.JSON_tag), getString(R.string.filesystemNotCreatedTag));
            }
        }
        return jsonString;
    }

}
