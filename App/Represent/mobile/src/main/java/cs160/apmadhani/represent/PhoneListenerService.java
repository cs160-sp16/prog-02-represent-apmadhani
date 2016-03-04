package cs160.apmadhani.represent;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 * Modified by Akshay Madhani
 */
public class PhoneListenerService extends WearableListenerService {

//   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
private static final String TOAST = "/send_toast";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.wtf("T", "in PhoneListenerService, got: " + messageEvent.getPath());
        if( messageEvent.getPath().equalsIgnoreCase( "/member" ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            if (value.equals("new")) {
                Intent intent = new Intent(this, CongressionalMobile.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //you need to add this flag since you're starting a new activity from a service
                intent.putExtra("congressmen", new String[]{"John McCain", "Bernie Sanders", "Marco Rubio"});
                Log.d("T", "about to start watch CongressionalActivity with "+ value);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, DetailedView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //you need to add this flag since you're starting a new activity from a service
                intent.putExtra("congressmen", value);
                Log.d("T", "about to start watch CongressionalActivity with " + value);
                startActivity(intent);
            }
        }
    }
}
