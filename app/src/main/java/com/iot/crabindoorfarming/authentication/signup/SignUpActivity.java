package com.iot.crabindoorfarming.authentication.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.iot.crabindoorfarming.common.GlobalFunction;
import com.iot.crabindoorfarming.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    private ActivitySignUpBinding viewBinding;
    private SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        initialView();
    }

    private void initialView() {
        presenter = new SignUpPresenter(this);
        viewBinding.backToLogin.setOnClickListener(view -> finish());
        viewBinding.buttonSignup.setOnClickListener(view -> {
            String username = viewBinding.editTextUsername.getText().toString().trim();
            String email = viewBinding.editTextEmail.getText().toString().trim();
            String password = viewBinding.editTextPassword.getText().toString().trim();
            String confirmPassword = viewBinding.editTextConfirmPassword.getText().toString().trim();
            presenter.signUp(username, email, password, confirmPassword);
        });
    }

    @Override
    public void signUpSuccess() {
        Toast.makeText(this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
//        GlobalFunction.showToast("Sign Up Successfully");
//        finish();
    }

    @Override
    public void signUpFailed() {
        Toast.makeText(this, "Sign Up failed, please try again", Toast.LENGTH_SHORT).show();
//        GlobalFunction.showToast("Please check email, password or connection");
    }

    @Override
    public void invalidEmail() {
        viewBinding.editTextEmail.requestFocus();
        viewBinding.editTextEmail.setError("Invalid email");
    }

    @Override
    public void invalidPassword() {
        viewBinding.editTextPassword.requestFocus();
        viewBinding.editTextPassword.setError("Invalid password");
    }

    @Override
    public void invalidConfirmPassword() {
        viewBinding.editTextConfirmPassword.requestFocus();
        viewBinding.editTextConfirmPassword.setError("Confirm password is not match");
    }

    @Override
    public void invalidUsername() {
        viewBinding.editTextUsername.requestFocus();
        viewBinding.editTextUsername.setError("Invalid username");
    }
}