package com.example.user.delta3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends Activity {


    EditText nameTxt, phoneTxt;
        ImageView contactImageImgView;
        List<Contacts> Contacts = new ArrayList<Contacts>();
        ListView contactListView;
        Uri imageUri = Uri.parse("android.resource://com.example.user.delta3/drawable/nouser.png");
        public Databasehelper dbHandler;
        int longClickedItemIndex;
        ArrayAdapter<Contacts> contactAdapter;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTxt = (EditText) findViewById(R.id.editname);
        phoneTxt = (EditText) findViewById(R.id.editphone);

        contactListView = (ListView) findViewById(R.id.listview);
        contactImageImgView = (ImageView) findViewById(R.id.editimage);
        dbHandler = new Databasehelper(getApplicationContext());

        registerForContextMenu(contactListView);





final Button addBtn = (Button) findViewById(R.id.addbutton);
        addBtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Contacts contact = new Contacts(dbHandler.getContactsCount(), String.valueOf(nameTxt.getText()), String.valueOf(phoneTxt.getText()), imageUri);

        dbHandler.createContact(contact);
        Contacts.add(contact);
        contactAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), String.valueOf(nameTxt.getText()) + " has been added to your Contacts!", Toast.LENGTH_SHORT).show();
        return;

             }
        });



        contactImageImgView.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Contact Image"), 1);
        }

        });

        if (dbHandler.getContactsCount() != 0)
        Contacts.addAll(dbHandler.getAllContacts());

        populateList();
        }




public void onActivityResult(int reqCode, int resCode, Intent data) {
        if (resCode == RESULT_OK) {
        if (reqCode == 1) {
        imageUri = data.getData();
        contactImageImgView.setImageURI(data.getData());
        }
        }
        }

private void populateList() {
        contactAdapter = new ContactListAdapter();
        contactListView.setAdapter(contactAdapter);
        }

private class ContactListAdapter extends ArrayAdapter<Contacts> {
    public ContactListAdapter() {
        super (MainActivity.this, R.layout.contact_layout, Contacts);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null)
            view = getLayoutInflater().inflate(R.layout.contact_layout, parent, false);

        Contacts currentContact = Contacts.get(position);

        TextView name = (TextView) view.findViewById(R.id.contactname);
        name.setText(currentContact.getName());
        TextView phone = (TextView) view.findViewById(R.id.contactnumber);
        phone.setText(currentContact.getPhone());

        ImageView ivContactImage = (ImageView) view.findViewById(R.id.contactimage);
        ivContactImage.setImageURI(currentContact.getUri());

        return view;
    }
}


}

