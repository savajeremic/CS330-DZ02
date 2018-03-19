package com.android.sava.cs330_dz02;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CharSequence[] items = { "FIT", "Fakultet za Menadzment", "Fakultet digitalnih umetnosti" };
    boolean[] itemsChecked = new boolean [items.length];

    ProgressDialog progressDialog;
    /** Poziva se prvim definisanjem aktivnosti. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        showDialog(0);
    }
    public void onClick2(View v)
    {
        Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:+381641234567"));
        startActivity(i);
    }

    public void onClick3(View v)
    {
        Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.google.rs/maps/@44.0255012,20.8522824,15z?hl=sr"));
        startActivity(i);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        final Intent i = new Intent("android.intent.action.VIEW");
        switch (id) {
            case 0:
                return new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("Dijalog sa malo teksta...")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,	int whichButton)
                                    {
                                        Toast.makeText(getBaseContext(),"OK je kliknut!", Toast.LENGTH_SHORT).show();
                                        startActivity(i);
                                    }
                                }
                        )
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton)
                                    {
                                        Toast.makeText(getBaseContext(),"Cancel je kliknut!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        )
                        .setMultiChoiceItems(items, itemsChecked,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        Toast.makeText(getBaseContext(),items[which] + (isChecked ? " checked!":" unchecked!"), Toast.LENGTH_SHORT).show();
                                        if(items[which]=="FIT"){
                                            i.setData(Uri.parse("http://www.metropolitan.ac.rs/osnovne-studije/fakultet-informacionih-tehnologija/"));
                                        }else if(items[which]=="Fakultet za Menadzment"){
                                            i.setData(Uri.parse("http://www.metropolitan.ac.rs/osnovne-studije/fakultet-za-menadzment/"));
                                        }
                                        else if(items[which]=="Fakultet digitalnih umetnosti"){
                                            i.setData(Uri.parse("http://www.metropolitan.ac.rs/fakultet-digitalnih-umetnosti-2/"));
                                        }
                                    }
                                }
                        ).create();
            case 1:
                progressDialog = new ProgressDialog(this);
                progressDialog.setIcon(R.mipmap.ic_launcher);
                progressDialog.setTitle("Preuzimanje datoteka...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                Toast.makeText(getBaseContext(),"OK je kliknut!", Toast.LENGTH_SHORT).show();
                            }
                        });
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                Toast.makeText(getBaseContext(),"Cancel je kliknut!", Toast.LENGTH_SHORT).show();
                            }
                        });
                return progressDialog;
        }
        return null;
    }
}
