package com.example.speechtotext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
//rebuilt this app for second time after reintstalling win10 when all projects were deleted
//dated December 2,2020
//at my own house bayalbas

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ImageButton button;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        button=findViewById(R.id.micBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                startActivityForResult(intent,77);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==77 && resultCode==RESULT_OK && data!=null)
        {
            arrayList=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            textView.setText(arrayList.get(0));
        }
    }

    @Override
    public void onBackPressed() {
        //dont forget to remove     "super.onBackPressed();"

        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(Html.fromHtml("<font color='#0000FF'>Exit</font>"));
        builder.setMessage(Html.fromHtml("<font color='#FF4500'>Are you sure to exit ?</font>"));
        builder.setCancelable(true);
        // noew lets setup yes no buttons
        builder.setPositiveButton(Html.fromHtml("<font color='#FF0000'>Yes😒</font>"), new DialogInterface.OnClickListener() {
            //change text color-----> Html.fromHtml("<font color='00000'>OK</font>"
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(Html.fromHtml("<font color='#00FF00'>No😊</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}