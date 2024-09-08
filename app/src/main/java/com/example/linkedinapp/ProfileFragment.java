package com.example.linkedinapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.imageview.ShapeableImageView;

public class ProfileFragment extends Fragment {

    private ImageView backgroundImage;
    private ShapeableImageView profilePicture;
    private TextView personName;
    private TextView headline;
    private Button connectButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize the views
        backgroundImage = view.findViewById(R.id.background_image);
        profilePicture = view.findViewById(R.id.profile_picture);
        personName = view.findViewById(R.id.person_name);
        headline = view.findViewById(R.id.headline);
        connectButton = view.findViewById(R.id.connect_button);

        // Set user data (in a real app, you would fetch this from a database)
        setProfileData();

        // Set button click action
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle connect button click
                handleConnect();
            }
        });

        return view;
    }

    // Method to set profile data
    private void setProfileData() {
        // Set the background image (assuming the drawable is already added)
        backgroundImage.setImageResource(R.drawable.background);

        // Set the profile picture
        profilePicture.setImageResource(R.drawable.profile);

        // Set the person's name
        personName.setText("Khalid Hussain"); // Replace with dynamic data

        // Set the person's headline
        headline.setText("Software Engineer at ABC Corp"); // Replace with dynamic data
    }

    // Method to handle the connect button click
    private void handleConnect() {
        // You can define what happens when the Connect button is clicked
        // For now, just display a simple message
        connectButton.setText("Requested");
    }
}
