package com.cyklodev.snippet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    String numberCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkVersion();

    }

    protected void checkVersion(){

        SharedPreferences mPreferences;
        SharedPreferences.Editor prefEditor;

        mPreferences = getApplicationContext().getSharedPreferences("currentApplicationVersion", Context.MODE_PRIVATE);

        PackageInfo packageInfo = null;
        try {
            packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionCode = packageInfo.versionCode;
        numberCode = packageInfo.versionName;


        Integer currentApplicationVersion = mPreferences.getInt("currentApplicationVersion", 0);

        if(versionCode > currentApplicationVersion){

            prefEditor = mPreferences.edit();
            prefEditor.putInt("currentApplicationVersion",versionCode);
            prefEditor.commit();

            //Toast.makeText(getApplicationContext(), "App updated to : " + versionCode, Toast.LENGTH_LONG).show();


            String title = getApplicationContext().getApplicationContext().getString(R.string.whats_new_title) +  " v " + numberCode ;
            String message = getApplicationContext().getApplicationContext().getString(R.string.whats_new_message);



            AlertDialog.Builder whatsNew = new AlertDialog.Builder(MainActivity.this);
            whatsNew.setTitle(title)
                    .setMessage( message )
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();

        }

        /*
        else {
            Toast.makeText(getApplicationContext(), "App up to date (" + versionCode + ")" , Toast.LENGTH_LONG).show();
        }
        */

    }

}
