package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.android.newsapp.QueryUtils;

import java.util.List;

public class PublicationLoader extends AsyncTaskLoader<List<Publication>> {

    /** Tag for log messages */
    private static final String LOG_TAG = PublicationLoader.class.getName();

    /** Query URL */
    private String url;

    /**
     * Constructs a new {@link PublicationLoader}.
     *
     * @param context of the activity
     * @param requiredUrl to load data from
     */
    public PublicationLoader(Context context, String requiredUrl) {
        super(context);
        url = requiredUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Publication> loadInBackground() {
        if (url == null) {
            return null;
        }

        // Make network request then parse response and extract a list of publications.
        List<Publication> publications = QueryUtils.fetchPublicationData(url);
        return publications;
    }
}