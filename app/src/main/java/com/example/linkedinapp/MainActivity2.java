package com.example.linkedinapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity2 extends AppCompatActivity {
//    TabLayout tabLayout;
//    Fragment fragment = null;
//    ShapeableImageView profile_img;
//
//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//
//        tabLayout = findViewById(R.id.tablayout);
////        profile_img = findViewById(R.id.profile_img); // Initialize profile_img here
//
//        // Setting up TabLayout with icons and titles
//        tabLayout.getTabAt(0).setIcon(R.drawable.home_icon).setText("Home");
//        tabLayout.getTabAt(1).setIcon(R.drawable.network_icon).setText("MyNetwork");
//        tabLayout.getTabAt(2).setIcon(R.drawable.post_icon).setText("Post");
//        tabLayout.getTabAt(3).setIcon(R.drawable.notifications_icon).setText("Notifications");
//        tabLayout.getTabAt(4).setIcon(R.drawable.suitcase).setText("Jobs");
//
//        profile_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity2.this, "Profile image clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Set the default fragment
//        if (savedInstanceState == null) {
//            fragment = new HomeFragment();
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.framelayout, fragment)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit();
//        }
//
//        // TabLayout tab selection listener
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()) {
//                    case 0:
//                        fragment = new HomeFragment();
//                        break;
//                    case 1:
//                        fragment = new FragmentAbout();
//                        break;
//                    case 2:
//                        fragment = new FragmentProfile();
//                        break;
//                    case 3:
//                        fragment = new ContactsUs();
//                        break;
//                    case 4:
//                        fragment = new FragmentJob();
//                        break;
//                }
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.framelayout, fragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                // Optional: handle tab unselection
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                // Optional: handle tab reselection
//            }
//        });
//
//        // Handle system window insets
//        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}
