package com.example.jacksonb.connections;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity
{
    Button travelButton;
    Button contactsButton;
    Button troubleshootingButton;
    Button signOutButton;
    Button documentsButton;
    Button passwordGeneratorButton;

    SharedPreferences vpnSetupPref;
    SharedPreferences.Editor vpnSetupPrefsEditor;

    String[] quickLinks = {"ADP", "Wells Fargo", "TransAmerica", "Business cards", "BW Gear",
            "Tap and Die Chart", "FS Resource CD"};
    String[] travelOptions = {"Concur", "Delta", "United", "American", "FlightAware", "Hilton",
            "IHG", "Marriot", "National", "Enterprise"};

    String dezideURL = "https://bwpapersystems.dezide.com/login";
    String businessCardURL = "http://www.bwcorp.net/BCOF/default.aspx";
    String bwGearURL = "http://bw-gear.com/";
    String fsResourceCD = "http://fscd.marquipwardunited.net/start.htm";
    String knowledgeSharingURL = "http://fs.barry-wehmiller.com";
    String pdfWebURL = "http://pdfweb.marquipwardunited.net/default.aspx";

    File drillAndTapFile;

    Boolean isVPNSetup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        travelButton = (Button) findViewById(R.id.travel_button);
        contactsButton = (Button) findViewById(R.id.contacts_button);
        troubleshootingButton = (Button) findViewById(R.id.troubleshooting_button);
        signOutButton = (Button) findViewById(R.id.sign_out_button);
        documentsButton = (Button) findViewById(R.id.documents_button);
        passwordGeneratorButton = (Button) findViewById(R.id.timegrid_button);

        vpnSetupPref = getApplicationContext().getSharedPreferences("isVPNSetup", Context.MODE_PRIVATE);
        vpnSetupPrefsEditor = vpnSetupPref.edit();



        signOutButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        if(networkStatus())
                            signOutIntent();

                        else
                            noNetworkMessage();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        travelButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        travelIntent();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        contactsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        contactsIntent();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        troubleshootingButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        if(networkStatus())
                            troubleshootingIntent();

                        else
                            noNetworkMessage();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        documentsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        if(networkStatus())
                            documentsIntent();

                        else
                            noNetworkMessage();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        passwordGeneratorButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        passwordGeneratorIntent();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });



    }

    public void contactsIntent()
    {
        Intent contactsIntent = new Intent(MainActivity.this, ContactsActivity.class);
        startActivity(contactsIntent);
    }

    public void signOutIntent()
    {
        Intent troubleshootingIntent = new Intent(MainActivity.this, SignOutActivity.class);
        startActivity(troubleshootingIntent);
    }

    public void travelIntent()
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(this);

        dialogBox.setItems(travelOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if(which == 0)
                {
                    //Concur intent
                    Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.concur.breeze");

                    if (launchIntent != null)
                        startActivity(launchIntent);

                    else
                    {
                        // Bring user to the market or let them choose an app
                        launchIntent = new Intent(Intent.ACTION_VIEW);
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchIntent.setData(Uri.parse("market://details?id=" + "com.concur.breeze"));
                        startActivity(launchIntent);
                    }
                }

                if(which == 1)
                {
                    //Delta intent
                    Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.delta.mobile.android");

                    if (launchIntent != null)
                        startActivity(launchIntent);

                    else
                    {
                        // Bring user to the market or let them choose an app
                        launchIntent = new Intent(Intent.ACTION_VIEW);
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchIntent.setData(Uri.parse("market://details?id=" + "com.delta.mobile.android"));
                        startActivity(launchIntent);
                    }
                }

                if(which == 2)
                {
                    //United intent
                    Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.united.mobile.android");

                    if (launchIntent != null)
                        startActivity(launchIntent);

                    else
                    {
                        // Bring user to the market or let them choose an app
                        launchIntent = new Intent(Intent.ACTION_VIEW);
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchIntent.setData(Uri.parse("market://details?id=" + "com.united.mobile.android"));
                        startActivity(launchIntent);
                    }
                }

                if(which == 3)
                {
                    //American intent
                    Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.aa.android");

                    if (launchIntent != null)
                        startActivity(launchIntent);

                    else
                    {
                        // Bring user to the market or let them choose an app
                        launchIntent = new Intent(Intent.ACTION_VIEW);
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchIntent.setData(Uri.parse("market://details?id=" + "com.aa.android"));
                        startActivity(launchIntent);
                    }
                }

                if(which == 4)
                {
                    //Flight Aware intent
                    Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.flightaware.android.liveFlightTracker");

                    if (launchIntent != null)
                        startActivity(launchIntent);

                    else
                    {
                        // Bring user to the market or let them choose an app
                        launchIntent = new Intent(Intent.ACTION_VIEW);
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchIntent.setData(Uri.parse("market://details?id=" + "com.flightaware.android.liveFlightTracker"));
                        startActivity(launchIntent);
                    }
                }

                if(which == 5)
                {
                    //Hilton intent
                    Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.hilton.android.hhonors");

                    if (launchIntent != null)
                        startActivity(launchIntent);

                    else
                    {
                        // Bring user to the market or let them choose an app
                        launchIntent = new Intent(Intent.ACTION_VIEW);
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchIntent.setData(Uri.parse("market://details?id=" + "com.hilton.android.hhonors"));
                        startActivity(launchIntent);
                    }
                }

                if(which == 6)
                {
                    //IHG intent
                    Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.ihg.apps.android");

                    if (launchIntent != null)
                        startActivity(launchIntent);

                    else
                    {
                        // Bring user to the market or let them choose an app
                        launchIntent = new Intent(Intent.ACTION_VIEW);
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchIntent.setData(Uri.parse("market://details?id=" + "com.ihg.apps.android"));
                        startActivity(launchIntent);
                    }
                }

                if(which == 7)
                {
                    //Marriot intent
                    Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.marriott.mrt");

                    if (launchIntent != null)
                        startActivity(launchIntent);

                    else
                    {
                        // Bring user to the market or let them choose an app
                        launchIntent = new Intent(Intent.ACTION_VIEW);
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchIntent.setData(Uri.parse("market://details?id=" + "com.marriott.mrt"));
                        startActivity(launchIntent);
                    }
                }

                if(which == 8)
                {
                    //National intent
                    Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.ehi.national.mobile");

                    if (launchIntent != null)
                        startActivity(launchIntent);

                    else
                    {
                        // Bring user to the market or let them choose an app
                        launchIntent = new Intent(Intent.ACTION_VIEW);
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchIntent.setData(Uri.parse("market://details?id=" + "com.ehi.national.mobile"));
                        startActivity(launchIntent);
                    }
                }

                if(which == 9)
                {
                    //Enterprise intent
                    Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.ehi.enterprise.android");

                    if (launchIntent != null)
                        startActivity(launchIntent);

                    else
                    {
                        // Bring user to the market or let them choose an app
                        launchIntent = new Intent(Intent.ACTION_VIEW);
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchIntent.setData(Uri.parse("market://details?id=" + "com.ehi.enterprise.android"));
                        startActivity(launchIntent);
                    }
                }
            }

        });
        dialogBox.show();
    }



    public void documentsIntent()
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(this);

        dialogBox.setMessage("Would you like to search using the knowledge sharing website or PDF Web?");
        dialogBox.setPositiveButton("Knowledge Sharing", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Intent knowledgeSharingIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(knowledgeSharingURL));
                startActivity(knowledgeSharingIntent);
            }
        });
        dialogBox.setNegativeButton("PDF Web", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if(isVPNActive())
                {
                    Intent intranetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdfWebURL));
                    startActivity(intranetIntent);
                }
                else
                    vpnInitialSetup();
            }
        });
        dialogBox.show();
    }

    public void troubleshootingIntent()
    {
        Intent troubleshootingIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dezideURL));
        startActivity(troubleshootingIntent);
    }

    public void passwordGeneratorIntent()
    {
        Intent timegridIntent = new Intent(MainActivity.this, PasswordGeneratorActivity.class);
        startActivity(timegridIntent);
    }

    public boolean networkStatus()
    {
        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public void noNetworkMessage()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Network Status");
        builder.setMessage("Please enable data or connect to wifi to proceed");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}});
        builder.show();
    }

    public boolean isVPNActive()
    {
        boolean vpnStatus = false;
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        vpnStatus = cm.getNetworkInfo(ConnectivityManager.TYPE_VPN).isConnectedOrConnecting();
        return vpnStatus;
    }

    public void vpnInitialSetup()
    {
        Intent ciscoIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.cisco.anyconnect.vpn.android.avf");

        if(ciscoIntent == null)
        {
            vpnSetupPrefsEditor.putBoolean("isVPNSetup", false);

            AlertDialog.Builder VPNSetupDialog = new AlertDialog.Builder(this);

            VPNSetupDialog.setTitle("VPN Setup");
            VPNSetupDialog.setMessage("In order to access some Marquip resources, a VPN connection is required. We don't detect Cisco on your device. " +
                    "Write down the VPN information below, and then click on continue to be redirected to the Cisco App and follow the steps:\n\n" +
                    "Click 'Connection'\nthen 'Add New'\n\n" +
                    "Description:\n\tSTL DataCenter\n\nServer Address:\n\t209.130.192.2\n\nLeave Advanced Preferences the way they are, then hit 'Done'\n" +
                    "Press back to get to the home screen of AnyConnect and turn 'AnyConnect VPN' on\n" +
                    "Press 'Change Settings'\nand uncheck 'Block Untrusted Servers'\n" +
                    "Go back to the home screen and turn 'AnyConnect VPN' on again and hit 'Continue'\n" +
                    "Change 'WebVPN'\n to 'SSL VPN Client'\n then enter BWCINC\\ followed by your network username and password. " +
                    "This setup will be saved and you can now get on VPN resources");
            VPNSetupDialog.setPositiveButton("Continue", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    Intent ciscoIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.cisco.anyconnect.vpn.android.avf");
                    isVPNSetup = vpnSetupPref.getBoolean("isVPNSetup", false);

                    if (isVPNSetup)
                        startActivity(ciscoIntent);

                    else
                    {
                        vpnSetupPrefsEditor.putBoolean("isVPNSetup", true);
                        // Bring user to the market or let them choose an app
                        ciscoIntent = new Intent(Intent.ACTION_VIEW);
                        ciscoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ciscoIntent.setData(Uri.parse("market://details?id=" + "com.cisco.anyconnect.vpn.android.avf"));
                        startActivity(ciscoIntent);
                    }
                }
            });
            VPNSetupDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {}});

            VPNSetupDialog.show();
        }

        else
        {
            startActivity(ciscoIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        final AlertDialog.Builder dialogBox = new AlertDialog.Builder(this);

        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            dialogBox.setTitle("Quick Links");
            dialogBox.setItems(quickLinks, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    if(i == 0)
                    {
                        // ADP intent
                        Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.adpmobile.android");

                        if (launchIntent != null)
                            startActivity(launchIntent);

                        else
                        {
                            // Bring user to the market or let them choose an app
                            launchIntent = new Intent(Intent.ACTION_VIEW);
                            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            launchIntent.setData(Uri.parse("market://details?id=" + "com.adpmobile.android"));
                            startActivity(launchIntent);
                        }
                    }

                    if(i == 1)
                    {
                        //Well fargo intent
                        Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.wf.wellsfargomobile");

                        if (launchIntent != null)
                            startActivity(launchIntent);

                        else
                        {
                            // Bring user to the market or let them choose an app
                            launchIntent = new Intent(Intent.ACTION_VIEW);
                            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            launchIntent.setData(Uri.parse("market://details?id=" + "com.wf.wellsfargomobile"));
                            startActivity(launchIntent);
                        }
                    }

                    if(i == 2)
                    {
                        // TransAmerica intent
                        Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("diversified.DiversifiedMobile");

                        if (launchIntent != null)
                            startActivity(launchIntent);

                        else
                        {
                            // Bring user to the market or let them choose an app
                            launchIntent = new Intent(Intent.ACTION_VIEW);
                            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            launchIntent.setData(Uri.parse("market://details?id=" + "diversified.DiversifiedMobile"));
                            startActivity(launchIntent);
                        }
                    }

                    if(i == 3)
                    {
                        // Business card intent
                        if(isVPNActive())
                        {
                            Intent launchIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(businessCardURL));
                            startActivity(launchIntent);
                        }
                        else
                            vpnInitialSetup();
                    }

                    if(i == 4)
                    {
                        // BW Gear intent
                        Intent launchIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(bwGearURL));
                        startActivity(launchIntent);
                    }

                    if(i == 5)
                    {
                        // Drill and tap chart
                        try
                        {
                            drillAndTapFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Drill and Tap Chart.jpg");

                            if(!drillAndTapFile.exists())
                            {
                                drillAndTapFile.createNewFile();
                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tap_drill_chart);
                                OutputStream os = new FileOutputStream(drillAndTapFile);
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                                os.flush();
                                os.close();
                            }

                            if(drillAndTapFile.exists())
                            {
                                Intent launchIntent = new Intent(Intent.ACTION_VIEW);
                                launchIntent.setDataAndType(Uri.fromFile(drillAndTapFile), "image/jpeg");
                                startActivity(launchIntent);
                            }


                        }
                        catch (Exception e){e.printStackTrace();}
                    }

                    if(i == 6)
                    {
                        if(isVPNActive())
                        {
                            Intent launchIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fsResourceCD));
                            startActivity(launchIntent);
                        }
                        else
                            vpnInitialSetup();
                    }
                }
            });
            dialogBox.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
