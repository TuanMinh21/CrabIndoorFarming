package com.iot.crabindoorfarming.authentication.forgotpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.iot.crabindoorfarming.databinding.ActivityForgotPasswordBinding;

import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordView {

    private ActivityForgotPasswordBinding viewBinding;
    private ForgotPasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        initView();
    }

    private void initView() {
        viewBinding.backToLogin.setOnClickListener(view -> finish());
        presenter = new ForgotPasswordPresenter(this);
        viewBinding.buttonSendReq.setOnClickListener(view -> {
            String email = viewBinding.edtVerifyEmail.getText().toString().trim();
            presenter.requestResetEmail(email);
        });
    }

    @Override
    public void requestSuccessful() {
        Toast.makeText(ForgotPasswordActivity.this, "Please check your email", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestFailed(Task<Void> task) {
        Toast.makeText(ForgotPasswordActivity.this,
                "Error: " + Objects.requireNonNull(task.getException()).getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void invalidEmail() {
        viewBinding.edtVerifyEmail.requestFocus();
        viewBinding.edtVerifyEmail.setError("Invalid email");
    }
}