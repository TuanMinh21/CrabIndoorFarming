package com.iot.crabindoorfarming.authentication.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.iot.crabindoorfarming.authentication.forgotpassword.ForgotPasswordActivity;
import com.iot.crabindoorfarming.authentication.signup.SignUpActivity;
import com.iot.crabindoorfarming.common.GlobalFunction;
import com.iot.crabindoorfarming.databinding.ActivitySignInBinding;
import com.iot.crabindoorfarming.mainsreen.MainActivity;

public class SignInActivity extends AppCompatActivity implements SignInView {

    private ActivitySignInBinding viewBinding;
    private SignInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        initialView();
    }

    private void initialView() {
        GlobalFunction.setContext(getApplicationContext());
        presenter = new SignInPresenter(this);
        viewBinding.buttonSignUp.setOnClickListener(view -> GlobalFunction.startActivity(SignInActivity.this, SignUpActivity.class));
        viewBinding.tvForgotPassword.setOnClickListener(view -> GlobalFunction.startActivity(SignInActivity.this, ForgotPasswordActivity.class));
        viewBinding.buttonSignIn.setOnClickListener(view -> {
            String email = viewBinding.editTextEmail.getText().toString().trim();
            String password = viewBinding.editTextPassword.getText().toString().trim();
            presenter.signIn(email, password);
        });
    }

    @Override
    public void signInSuccess() {
        GlobalFunction.startActivity(SignInActivity.this, MainActivity.class);
    }

    @Override
    public void signInFailed() {
        Toast.makeText(this, "Sign In failed, please try again", Toast.LENGTH_SHORT).show();
//        GlobalFunction.showToast("Sign In failed, please try again");
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
}