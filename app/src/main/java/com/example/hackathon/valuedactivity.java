package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class valuedactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuedactivity);

    }

    public void openGeoFence(View view) {
        Intent a = new Intent(valuedactivity.this, MapsActivity.class);
        startActivity(a);
    }

    public void openReqDonation(View view) {
        Intent a = new Intent(valuedactivity.this, AddDonReq.class);
        startActivity(a);
    }

    public void openRDonationRequested(View view) {
        Intent a = new Intent(valuedactivity.this, ShowDonReq.class);
        startActivity(a);
    }

    public void openCoronaInfo(View view) {
        Intent a = new Intent(valuedactivity.this, CoronaInfo.class);
        startActivity(a);
    }


    public void openMedHelp(View view) {

        try {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.vmac.chatbot");
            startActivity(launchIntent);
        } catch (Exception e) {
            AlertDialog.Builder builder
                    = new AlertDialog
                    .Builder(valuedactivity.this);

            builder.setMessage("There was no app found to open this");
            builder.setTitle("Alert !");
            builder.setCancelable(false);
            builder
                    .setPositiveButton(
                            "Okay",
                            new DialogInterface
                                    .OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which)
                                {
                                    dialog.cancel();
                                }
                            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public void onExit(View view) {
        finishAffinity();
        System.exit(0);
    }


}
