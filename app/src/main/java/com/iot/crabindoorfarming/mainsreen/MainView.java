package com.iot.crabindoorfarming.mainsreen;

import androidx.annotation.NonNull;

import com.iot.crabindoorfarming.model.Controller;

public interface MainView {
    void responseData(@NonNull Controller controller);

    void getDataFailed();
}
