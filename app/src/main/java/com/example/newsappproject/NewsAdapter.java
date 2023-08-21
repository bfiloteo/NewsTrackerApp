package com.example.newsappproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Activity context, ArrayList<News> news) {

        super(context, 0, news);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984" | 4:26 P.M.) from a Date object.
     */
    private String formatDate(String dateStringUTC) {
        // Parse the dateString into a Date object
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'");
        Date dateObject = null;
        try {
            dateObject = simpleDateFormat.parse(dateStringUTC);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy  h:mm a", Locale.ENGLISH);
        String formattedDateUTC = df.format(dateObject);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = df.parse(formattedDateUTC);
            df.setTimeZone(TimeZone.getDefault());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(date);
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override

    /**
     * Creates the view to show each information for each news articles
     */

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Gets the current news article view
        final News currentNews = getItem(position);

        // Gets the text view called "Topic"
        TextView topicView =  listItemView.findViewById(R.id.Topic);

        // Sets the text view to the information that was retrieved from the JSON parsing
        topicView.setText(currentNews.getTopic());

        // Gets the text view called "Title"
        TextView titleView =  listItemView.findViewById(R.id.Title);

        // Sets the text view to the information that was retrieved from the JSON parsing
        titleView.setText(currentNews.getTitle());

        // Gets the text view called "Author"
        TextView authorView =  listItemView.findViewById(R.id.Author);

        // Determines if the current news article has an author.
        if(currentNews.getAuthor() == null) {
            // If there is no author information, the author text view will be set to "No Author Present"
            authorView.setText("No Author Present");
        } else {
            // If there is author information, the author text view will be set to the information that was received from the JSON parsing
            authorView.setVisibility(View.VISIBLE);
            authorView.setText(currentNews.getAuthor());
        }

        // Gets the text view called "Date"
        TextView dateView =  listItemView.findViewById(R.id.Date);

        // Sets the text by using the formatDate method
        dateView.setText(formatDate(currentNews.getDate()));

        // Finds the image view called "Thumbnail"
        ImageView thumbnailView =  listItemView.findViewById(R.id.Thumbnail);

        // Determines if the current news article has a thumbnail
        if (currentNews.getImageUrl() == null) {
            // If there is no thumbnail information, the image view will disappear
            thumbnailView.setVisibility(View.GONE);
        } else {
            // Uses Glide to set the image view to the current thumbnail article
            // I found Glide through a youtube video on how to load and cache images.
            // https://www.youtube.com/watch?v=eiP-vnSM0OM
            thumbnailView.setVisibility(View.VISIBLE);
            Glide.with(getContext())
                    .load(currentNews.getImageUrl())
                    .into(thumbnailView);
        }
        // Returns the listItemView
        return listItemView;
    }

}
