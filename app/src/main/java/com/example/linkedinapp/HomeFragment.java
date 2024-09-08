package com.example.linkedinapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FloatingActionButton addPost;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        addPost = view.findViewById(R.id.add_post);

        postList = new ArrayList<>();
        postAdapter = new PostAdapter(postList);
        recyclerView.setAdapter(postAdapter);

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddPostDialog();
            }
        });

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Posts");

        fetchPosts();

        return view;
    }

    private void fetchPosts() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Post post = snapshot.getValue(Post.class);
                    postList.add(post);
                }
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "Failed to read posts", databaseError.toException());
            }
        });
    }

    private void addPost(Post post) {
        DatabaseReference newPostRef = myRef.push();
        post.setId(newPostRef.getKey()); // Set the generated key as the post ID
        newPostRef.setValue(post)
                .addOnSuccessListener(aVoid -> Log.d("TAG", "Post added"))
                .addOnFailureListener(e -> Log.e("TAG", "Failed to add post", e));
    }

    private void showAddPostDialog() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Inflate the custom layout for the dialog
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_box, null);
        builder.setView(dialogView);

        // Get references to the EditText and Button in the dialog layout
        EditText postUsername = dialogView.findViewById(R.id.username);
        EditText postContent = dialogView.findViewById(R.id.content);
        Button submitPost = dialogView.findViewById(R.id.submit_post);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();

        // Set the submit button action
        submitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = postUsername.getText().toString();
                String postText = postContent.getText().toString();

                if (!username.isEmpty() && !postText.isEmpty()) {
                    // Create a new post object with the entered username and content
                    Post post = new Post(username, postText, 0, 0, 0);
                    addPost(post);
                    fetchPosts(); // Refresh the list after adding a new post
                    dialog.dismiss();
                } else {
                    if (username.isEmpty()) {
                        postUsername.setError("Username can't be empty!");
                    }
                    if (postText.isEmpty()) {
                        postContent.setError("Post content can't be empty!");
                    }
                }
            }
        });


        dialog.show();
    }
}
