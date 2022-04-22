package com.example.boxandgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.boxandgo.databinding.ActivityHomepageBinding;

import java.util.Calendar;

public class Homepage extends AppCompatActivity {

    ActivityHomepageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       replaceFragment(new HomeFragment());


        binding.bottomNavigationView2.setOnItemSelectedListener(item ->{

            switch(item.getItemId()){

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.upload:
                    replaceFragment(new UploadFragment());
                    break;
                case R.id.liked:
                    replaceFragment(new LikedFragment());
                    break;
            }
            return true;
        });

        //Alarm sensor
        start_Sensing_new();
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }

    public void start_Sensing_new() {  //testing for upload frequency.

        Log.d("MyActivity", "Alarm On");
        Calendar calendar = Calendar.getInstance();
        long currentTime = System.currentTimeMillis();
        calendar.setTimeInMillis(currentTime);

        //Creating a pending intent for sendNotification class.
        Intent intent = new Intent(this, sendNotification.class);
        PendingIntent pendingIntent = null;


        pendingIntent = PendingIntent.getBroadcast(this, 40, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        //Generating object of alarmManager using getSystemService method. Here ALARM_SERVICE is used to receive alarm manager with intent at a time.
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);

        //this method creates a repeating, exactly timed alarm
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 3, pendingIntent); //1 minute apart
        Log.d("===Sensing alarm===", "One time alert alarm has been created. This alarm will send to a broadcast sensing receiver.");


    }
}