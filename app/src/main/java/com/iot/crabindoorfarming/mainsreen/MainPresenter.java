package com.iot.crabindoorfarming.mainsreen;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.iot.crabindoorfarming.common.GlobalFunction;
import com.iot.crabindoorfarming.model.Controller;

public class MainPresenter {
    private final MainView view;
    DatabaseReference ref = GlobalFunction.getFirebaseDatabase().getReference("controller");

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void setData(Controller controller) {
        ref.child(GlobalFunction.getDateTime()).setValue(controller);
    }

    public void getData() {

        Query queryLast = ref.orderByKey().limitToLast(1);

        queryLast.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DataSnapshot dataDateTime = null;

                for(DataSnapshot data : snapshot.getChildren()) {
                    dataDateTime = data;
                }

                if(dataDateTime == null) return;

                DataSnapshot lastData = null;

                for(DataSnapshot data : dataDateTime.getChildren()) {
                    lastData = data;
                }

                if(lastData != null) {
                    Controller controller = lastData.getValue(Controller.class);
                    if(controller != null) {
                        view.responseData(controller);
                    }
                }
                else {
                    view.getDataFailed();
                }

                Log.i("Value change", "Event DataChange");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
