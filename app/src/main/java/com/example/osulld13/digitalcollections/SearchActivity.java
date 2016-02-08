package com.example.osulld13.digitalcollections;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.SearchView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;
import java.util.List;


public class SearchActivity extends AppCompatActivity {

    private final String TAG = QueryManager.class.getSimpleName();

    private SearchView searchBar;
    private List<Document> documentsRetrieved;
    private QueryManager queryManager;
    private ResponseXMLParser responseXMLParser;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setUpToolbar();

        // Add progress bar to XML views and then call to make visible
        mProgressBar = (ProgressBar) findViewById(R.id.searchProgressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        // Get search bar and set listener for searching
        searchBar = (SearchView) findViewById(R.id.searchView);

        // Initialize Query constructor
        queryManager = new QueryManager();

        // Initialize response XML parser
        responseXMLParser = new ResponseXMLParser();

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                initializeOnQueryTextListener(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void initializeOnQueryTextListener(String query) {
        // Turn Progress Indicator on
        mProgressBar.setVisibility(View.VISIBLE);
        String solrQuery = queryManager.constructSolrQuery(query);

        InputStream responseStream = queryManager.queryDigitalRepositoryAsync((String) solrQuery);

        List<Document> documentList = null;

        try {
            documentList = responseXMLParser.parseSearchResponse(responseStream);
        } catch (java.io.IOException e){
            // Add error dialogue
            e.printStackTrace();
        } catch (XmlPullParserException e){
            // Add error dialogue
            e.printStackTrace();
        }

        documentsRetrieved = documentList;

        // Log Doc results for debugging
        if (documentsRetrieved != null) {
            for (Document doc : documentList) {
                Log.d(TAG, doc.toString());
            }
        }

        //Turn Progress indicator off
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private void setUpToolbar() {
        // Set up Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.search_activity_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
