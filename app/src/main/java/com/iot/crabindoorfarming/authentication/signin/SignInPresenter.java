package com.iot.crabindoorfarming.authentication.signin;

import android.util.Patterns;

import com.iot.crabindoorfarming.common.GlobalFunction;
import com.iot.crabindoorfarming.model.User;

public class SignInPresenter {
    private final SignInView view;
    private boolean isFailed = false;

    public SignInPresenter(SignInView view) {
        this.view = view;
    }

    public void signIn(String email, String password) {

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
           view.invalidEmail();
           isFailed = true;
        }
        if(password.length() < 6) {
            view.invalidPassword();
            isFailed = true;
        }

        if(!isFailed) {
            GlobalFunction.getFirebaseAuth()
                    .signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if(task.isSuccessful()) {
                            User.getInstance().setUser(email, password);
                            view.signInSuccess();
                        }
                        else
                            view.signInFailed();
                    });
        }
    }
}
