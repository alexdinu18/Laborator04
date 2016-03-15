package ro.pub.cs.systems.pdsd.lab04.contactsmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class ContactsManagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);
        
        final Button show = (Button)findViewById(R.id.show);
        final Button save = (Button)findViewById(R.id.save);
        final Button cancel = (Button)findViewById(R.id.cancel);
        
        final EditText name =(EditText)findViewById(R.id.name);
        final EditText phoneNumber =(EditText)findViewById(R.id.phoneNumber);
        final EditText email =(EditText)findViewById(R.id.email);
        final EditText position =(EditText)findViewById(R.id.position);
        final EditText website =(EditText)findViewById(R.id.website);
        final EditText company =(EditText)findViewById(R.id.company);
        final EditText address =(EditText)findViewById(R.id.address);
        final EditText messengerID =(EditText)findViewById(R.id.messengerID);
        
        show.setOnClickListener(new View.OnClickListener() {
       	 
            @Override
            public void onClick(View v) {
            	LinearLayout l = (LinearLayout)findViewById(R.id.layout2);
            	if (l.getVisibility() == View.VISIBLE) {
	            	l.setVisibility(View.GONE);
	            	show.setText("Show Additional Fields");
            	}
            	else {
            		l.setVisibility(View.VISIBLE);
	            	show.setText("Hide Additional Fields");
            	}
            }
          });
        
        save.setOnClickListener(new View.OnClickListener() {
       	 
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            	intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            	if (name != null) {
            	  intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
            	}
            	if (phoneNumber != null) {
            	  intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber.getText().toString());
            	}
            	if (email != null) {
            	  intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText().toString());
            	}
            	if (address != null) {
            	  intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address.getText().toString());
            	}
            	if (position != null) {
            	  intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, position.getText().toString());
            	}
            	if (company != null) {
            	  intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company.getText().toString());
            	}
            	ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
            	if (website != null) {
            	  ContentValues websiteRow = new ContentValues();
            	  websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
            	  websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website.getText().toString());
            	  contactData.add(websiteRow);
            	}
            	if (messengerID != null) {
            	  ContentValues imRow = new ContentValues();
            	  imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
            	  imRow.put(ContactsContract.CommonDataKinds.Im.DATA, messengerID.getText().toString());
            	  contactData.add(imRow);
            	}
            	intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
            	startActivity(intent);
            }
          });
        
        cancel.setOnClickListener(new View.OnClickListener() {
          	 
            @Override
            public void onClick(View v) {
            	finish();
            }
          });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contacts_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
