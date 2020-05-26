package com.example.hackathon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    public  static final String TAG = "GeofenceBroadCastRcv";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"You've entered a red zone!",Toast.LENGTH_LONG).show();
        NotificationHelper notificationHelper = new NotificationHelper(context);
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if(geofencingEvent.hasError())
        {
            Log.d(TAG,"On Receive: Error Receiving Geofence Error.......");
            return;
        }
        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
      //  Location location = geofencingEvent.getTriggeringLocation();
        for(Geofence geofence: geofenceList)
             Log.d(TAG,"On Receive....."+geofence.getRequestId());
        int transitionType  = geofencingEvent.getGeofenceTransition();
        switch(transitionType)
        {
            case  Geofence.GEOFENCE_TRANSITION_ENTER:
                Toast.makeText(context,"You've entered a red zone!",Toast.LENGTH_LONG).show();
                notificationHelper.sendHighPriorityNotification("Entered in Red zone","hey this is to inform you that you have been entered in the Red zone area",MapsActivity.class);
                break;
            case  Geofence.GEOFENCE_TRANSITION_DWELL:
                Toast.makeText(context,"You've entered a red zone!",Toast.LENGTH_LONG).show();
                notificationHelper.sendHighPriorityNotification("Dwelling in Red zone","hey this is to inform you that you are dwelling in the Red zone area",MapsActivity.class);
                break;
            case  Geofence.GEOFENCE_TRANSITION_EXIT:
                Toast.makeText(context,"You've entered a red zone!",Toast.LENGTH_LONG).show();
                notificationHelper.sendHighPriorityNotification("Exited from Red zone","hey this is to inform you that you have been exited from  the Red zone area",MapsActivity.class);
                break;
        }
    }
}
