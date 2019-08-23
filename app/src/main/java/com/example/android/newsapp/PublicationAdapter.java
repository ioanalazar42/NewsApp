package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * A {@link PublicationAdapter} knows how to create a list item layout for each publication
 * in the data source (a list of {@link Publication} objects).
 *
 * These list item layouts will be provided to an adapter view to be displayed to the user.
 */
public class PublicationAdapter extends ArrayAdapter<Publication> {

    /**
     * Constructs a new {@link PublicationAdapter}.
     *
     * @param context of the app
     * @param publications is the list of publications, which is the data source of the adapter
     */
    public PublicationAdapter(Context context, List<Publication> publications) {
        super(context, 0, publications);
    }

    /**
     * Returns a list item view that displays information about the publication
     * at the given position in the list of earthquakes.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.publication_list_item, parent, false);
        }
        // Find the publication at the given position in the list of publications
        Publication currentPublication = getItem(position);

        // Find the TextView with view ID title
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        // Display the title of the current publication in that TextView
        titleView.setText(currentPublication.getTitle());


        // Find the TextView with view ID section
        TextView sectionView = (TextView) listItemView.findViewById(R.id.section);
        // Display the section that the current publication belongs to in that TextView
        sectionView.setText(currentPublication.getSection());

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}
