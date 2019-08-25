package com.example.android.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.app.LoaderManager.LoaderCallbacks;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderCallbacks<List<Publication>> {

    private static final String LOG_TAG = MainActivity.class.getName();

    /** Constant value for the publication loader ID. */
    private static final int PUBLICATION_LOADER_ID = 1;

    /** URL for publications data */
    private static final String REQUEST_URL = "http://content.guardianapis.com/search?q=debates&show-tags=contributor&api-key=test";

    /** TextView that is displayed when the list is empty or there is not internet connection*/
    private TextView emptyStateTextView;

    /** Adapter for the list of publications */
    private PublicationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publication_activity);

        // Find a reference to the {@link ListView} in the activity_main layout
        ListView publicationListView = (ListView) findViewById(R.id.list);

        emptyStateTextView = (TextView) findViewById(R.id.empty_view);
        publicationListView.setEmptyView(emptyStateTextView);

        // Create a new adapter that takes an empty list of publications as input
        adapter = new PublicationAdapter(this, new ArrayList<Publication>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        publicationListView.setAdapter(adapter);

        // Set an item click listener on the ListView and send an intent to a web browser
        // to open a website with more information about the publication.
        publicationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current publication that was clicked on
                Publication currentPublication = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri publicationUri = Uri.parse(currentPublication.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, publicationUri);

                startActivity(websiteIntent);
            }
        });
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(PUBLICATION_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            emptyStateTextView.setText(R.string.no_internet_connection);
        }
    }


    @Override
    public Loader<List<Publication>> onCreateLoader(int i, Bundle bundle) {

        // Create a new loader for the given URL
        return new PublicationLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Publication>> loader, List<Publication> publications) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No publications found."
        emptyStateTextView.setText(R.string.no_data_found);

        // Clear the adapter of previous publication data
        adapter.clear();

        // If there is a valid list of {@link Publication}s, then add them to the adapter's
        // data set. This will update the ListView
        if (publications != null && !publications.isEmpty()) {
            adapter.addAll(publications);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Publication>> loader) {
        // Loader reset, so we can clear out our existing data.
        adapter.clear();
    }
}
