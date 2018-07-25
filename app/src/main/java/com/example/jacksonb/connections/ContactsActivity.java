package com.example.jacksonb.connections;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class ContactsActivity extends AppCompatActivity
{
    private String[] contactsNameStringArray;
    private String[] contactsPhoneStringArray;
    private String[] contactsEmailStringArray;

    private ArrayList<String> contactsArrayList = new ArrayList<>();
    private ListView contactsListView;
    private ArrayAdapter contactsArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_layout);

        contactsListView = (ListView) findViewById(R.id.contacts_list_view);

        contactsNameStringArray = getResources().getStringArray(R.array.contactsNameStringArray);
        contactsPhoneStringArray = getResources().getStringArray(R.array.contactsPhoneStringArray);
        contactsEmailStringArray = getResources().getStringArray(R.array.contactsEmailStringArray);

        for (int i = 0; i < contactsNameStringArray.length; i++)
        {
            contactsArrayList.add(contactsNameStringArray[i]);
        }


        contactsArrayAdapter = new ArrayAdapter<>(this, R.layout.custom_list_view, contactsNameStringArray);
        contactsListView.setAdapter(contactsArrayAdapter);


        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d("test2", "" + position);
                final int positionGlobal = position;

                AlertDialog.Builder alertDialogContacts = new AlertDialog.Builder(ContactsActivity.this);
                alertDialogContacts.setTitle(contactsNameStringArray[position]);
                alertDialogContacts.setMessage("Phone: \n" + contactsPhoneStringArray[position] + "\n\n" + "Email: " + contactsEmailStringArray[position]);
                alertDialogContacts.setNegativeButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                        phoneIntent.setData(Uri.parse("tel:" + contactsPhoneStringArray[positionGlobal]));
                        startActivity(phoneIntent);
                    }
                });
                alertDialogContacts.setNeutralButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                alertDialogContacts.setPositiveButton("Email", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + contactsEmailStringArray[positionGlobal]));
                        startActivity(emailIntent);

                    }
                });
                alertDialogContacts.show();
            }
        });
    }
}
