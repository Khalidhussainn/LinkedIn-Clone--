package com.example.linkedinapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabLayout;
import com.example.linkedinapp.FragmentNotifications;

public class Linkedinpage extends AppCompatActivity {
    private TabLayout tabLayout;
    private Fragment fragment = null;
    private ShapeableImageView profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_linkedinpage);

        tabLayout = findViewById(R.id.tablayout);
        profileImg = findViewById(R.id.profile_img);

        tabLayout.getTabAt(0).setIcon(R.drawable.home_icon).setText("Home");
        tabLayout.getTabAt(1).setIcon(R.drawable.network_icon).setText("MyNetwork");
        tabLayout.getTabAt(2).setIcon(R.drawable.post_icon).setText("Post");
        tabLayout.getTabAt(3).setIcon(R.drawable.notifications_icon).setText("Notifications");
        tabLayout.getTabAt(4).setIcon(R.drawable.suitcase).setText("Jobs");

        profileImg.setOnClickListener(v -> {
            ProfileFragment profileFragment = new ProfileFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout, profileFragment)
                    .addToBackStack(null)
                    .commit();
        });

        if (savedInstanceState == null) {
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new HomeFragment();
                        break;
                    case 1:
                        fragment = new NetworkFragment();
                        break;
                    case 2:
                        fragment = new PostFragment();
                        break;
                    case 3:
                        fragment = new FragmentNotifications();
                        break;
                    case 4:
                        fragment = new FragmentJobs();
                        break;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
