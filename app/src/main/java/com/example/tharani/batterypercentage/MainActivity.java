package com.example.tharani.batterypercentage;
/*import is libraries imported for writing the code
* AppCompatActivity is base class for activities
* Bundle handles the orientation of the activity
*/

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /*onCreate is the first method in the life cycle of an activity
    savedInstance passes data to super class,data is pull to store state of application
  * setContentView is used to set layout for the activity
  *R is a resource and it is auto generate file
  * activity_main assign an integer value*/
    //Declaring variables
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.battery); // Initializing object by ID
        /*
         BroadcastReceiver is an Android component which allows you to register for system or
         application events.
         A receiver can be registered via the AndroidManifest.xml file
          The implementing class for a receiver extends the BroadcastReceiver class. If the event for which the broadcast receiver has registered happens,
          The onReceive() method of the receiver is called by the Android system.
         */
        BroadcastReceiver batteryReceiver = new BroadcastReceiver() {  //Creating object using new
            @Override
            public void onReceive(Context context, Intent intent) {
                /*Context allows access to application-specific resources and classes
                UnregisterReceiver will unregister a previously registered BroadcastReceiver.*/
                context.unregisterReceiver(this);
                //gives reference
                //Getting the information about the battery
                // here add the value from the getIntExtra instead of the actual integer from the other activity
                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                //The BatteryManager broadcasts an action whenever the device is connected or disconnected from power
                int level = -1;
                //Checks the current level of battery
                if (currentLevel >= 0 && scale > 0) { //using  if Statement because it decides whether a certain statement will excecute or not
                    level = (currentLevel * 100) / scale;//display the current level of battery percentage in mobile
                }
                textView.setText("Battery Remaining:" + level + "%"); // Setting text for textView
            }
        };
        /*
         * ACTION_BATTERY_CHANGED is a broadcast containing the charging state, level, and other
           information about the battery.
         * IntentFilter specifies the types of intents to which a broadcast receiver can respond.
         */
        IntentFilter batteryFilter = new IntentFilter //Creating the object of
                (Intent.ACTION_BATTERY_CHANGED);
        // Calling registerReceiver() to register the receiver
        registerReceiver(batteryReceiver, batteryFilter);
    }

}


