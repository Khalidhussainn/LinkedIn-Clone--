package com.example.linkedinapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextUsername, editTextPassword, editTextConfirmPassword;
    private CheckBox checkBoxPrivacyPolicy;
    private Button buttonSignUp;
    private TextView textViewLoginPrompt;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); // Ensure this layout file exists

        // Initialize UI elements
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        checkBoxPrivacyPolicy = findViewById(R.id.checkBoxPrivacyPolicy);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLoginPrompt = findViewById(R.id.textViewLoginPrompt);

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference("user SignUp data");

        // Set click listener for the Sign Up button
        buttonSignUp.setOnClickListener(v -> handleSignUp());

        // Set click listener for the Login prompt
        textViewLoginPrompt.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class); // Ensure LoginActivity exists
            startActivity(intent);
        });
    }

    private void handleSignUp() {
        String email = editTextEmail.getText().toString().trim();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "All fields must be filled out", Toast.LENGTH_LONG).show();
        } else if (!isValidName(username)) {
            Toast.makeText(SignUpActivity.this, "Name must contain only letters", Toast.LENGTH_LONG).show();
        } else if (!isValidEmail(email)) {
            Toast.makeText(SignUpActivity.this, "Incorrect email address", Toast.LENGTH_LONG).show();
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
        } else if (!isValidPassword(password)) {
            Toast.makeText(SignUpActivity.this, "Password must be at least 8 characters, including letters, numbers, and special symbols", Toast.LENGTH_LONG).show();
        } else if (!checkBoxPrivacyPolicy.isChecked()) {
            Toast.makeText(SignUpActivity.this, "You must agree to the privacy policy", Toast.LENGTH_LONG).show();
        } else {
            // Save data to Firebase Realtime Database
            String userId = databaseReference.push().getKey();
            User user = new User(email, username, password); // Corrected User instantiation

            if (userId != null) {
                databaseReference.child(userId).setValue(user)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        }
    }

    private boolean isValidName(String name) {
        return Pattern.matches("^[a-zA-Z]+$", name);
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", email);
    }

    private boolean isValidPassword(String password) {
        return Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", password);
    }
}
