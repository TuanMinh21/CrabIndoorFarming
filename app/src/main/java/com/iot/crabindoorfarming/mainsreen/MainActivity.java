package com.iot.crabindoorfarming.mainsreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.iot.crabindoorfarming.R;
import com.iot.crabindoorfarming.databinding.ActivityMainBinding;
import com.iot.crabindoorfarming.model.Controller;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainView {

    private ActivityMainBinding viewBinding;
    private Controller controller;
    private MainPresenter presenter;
    private boolean isAboveOneTime = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        initialView();
    }

    private void initialView() {
        presenter = new MainPresenter(this);
        presenter.getData();
        viewBinding.logOut.setOnClickListener(view -> finish());
        viewBinding.buttonPumper1.setOnClickListener(this);
        viewBinding.buttonPumper2.setOnClickListener(this);
        viewBinding.switchControl.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    private void setView() {
        if(controller == null) {
            Toast.makeText(MainActivity.this, "controller is null", Toast.LENGTH_SHORT).show();
            controller = new Controller("12", "30", "good condition", "auto", "0", "1");
        }
        viewBinding.phValue.setText(controller.getPH());
        viewBinding.temperatureValue.setText(controller.getTemperature() + getString(R.string.sample_temperature_value));

        if(controller.getCondition().toLowerCase().equals(getString(R.string.bad_condition))) {
            viewBinding.condition.setText(getString(R.string.bad_condition));
            viewBinding.condition.setTextColor(getResources().getColor(R.color.custom_red));
        }
        else {
            viewBinding.condition.setText(getString(R.string.good_condition));
            viewBinding.condition.setTextColor(getResources().getColor(R.color.custom_green));
        }

        if(controller.getPumper1().equals("1"))
            viewBinding.buttonPumper1.setText(getString(R.string.on));
        else
            viewBinding.buttonPumper1.setText(getString(R.string.off));

        if(controller.getPumper2().equals("1"))
            viewBinding.buttonPumper2.setText(getString(R.string.on));
        else
            viewBinding.buttonPumper2.setText(getString(R.string.off));

        if(controller.getControlState().equals("off")) {
            viewBinding.switchControl.setChecked(false);
            viewBinding.buttonPumper1.setEnabled(false);
            viewBinding.buttonPumper2.setEnabled(false);
        }
        else {
            viewBinding.switchControl.setChecked(true);
            viewBinding.buttonPumper1.setEnabled(true);
            viewBinding.buttonPumper2.setEnabled(true);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_pumper1:
                if(controller.getPumper1().equals("1")) {
                    controller.setPumper1("0");
                }
                else {
                    controller.setPumper1("1");
                }
                break;
            case R.id.button_pumper2:
                if(controller.getPumper2().equals("1")) {
                    controller.setPumper2("0");
                }
                else {
                    controller.setPumper2("1");
                }
                break;
            case R.id.switch_control:
                if(viewBinding.switchControl.isChecked()) {
                    controller.setControlState("auto");
                }
                else {
                    controller.setControlState("off");
                }
                break;
            default:
                break;
        }
        presenter.setData(controller);
    }

//    @Override
//    public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
//        if(state) {
//            controller.setControlState("auto");
//        }
//        else {
//            controller.setControlState("off");
//        }
//
//        if(isAboveOneTime)
//            presenter.setData(controller);
//        else
//            isAboveOneTime = true;
//    }

    @Override
    public void onBackPressed() { }

    @Override
    public void responseData(@NonNull Controller controller) {
        this.controller = controller;
        setView();
    }

    @Override
    public void getDataFailed() {
        Toast.makeText(this, "Get data failed!", Toast.LENGTH_SHORT).show();
    }
}