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

    protected void addPost(Post post) {
        myRef.child(post.getId()).setValue(post);
        Log.d("TAG", "Post added");
    }

    private void showAddPostDialog() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Inflate the custom layout for the dialog
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_box, null);
        builder.setView(dialogView);

        // Get references to the EditText and Button in the dialog layout
        EditText postContent = dialogView.findViewById(R.id.content);
        Button submitPost = dialogView.findViewById(R.id.submit_post);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();

        // Set the submit button action
        submitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postText = postContent.getText().toString();

                if (!postText.isEmpty()) {
                    Post post = new Post("Anonymous", postText, 0, 0, 0);
                    addPost(post);
                    fetchPosts();
                    dialog.dismiss();
                } else {
                    postContent.setError("Post content can't be empty!");
                }
            }
        });

        // Show the dialog
        dialog.show();
    }

    private void fetchPosts() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Post post = snapshot.getValue(Post.class);
                    if (post != null) {
                        postList.add(post);
                    }
                }
                postAdapter.notifyDataSetChanged();
                Log.d("TAG", "Number of posts: " + postList.size());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }
}

