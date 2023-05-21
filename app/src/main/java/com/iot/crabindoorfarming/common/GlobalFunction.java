package com.iot.crabindoorfarming.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GlobalFunction {

    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static Toast mToast;

    public static void setContext(Context context) {
        mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }

    public static void startActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static FirebaseAuth getFirebaseAuth() {
        return mAuth;
    }

    public static FirebaseDatabase getFirebaseDatabase() {
        return database;
    }

    public static void showToast(String msg) {
        mToast.setText(msg);
        mToast.show();
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        Date now = Calendar.getInstance().getTime();
        return dateFormat.format(now);
    }
}
