package com.iot.crabindoorfarming.authentication.forgotpassword;

import android.util.Patterns;

import com.iot.crabindoorfarming.common.GlobalFunction;

public class ForgotPasswordPresenter {
    private final ForgotPasswordView view;

    public ForgotPasswordPresenter(ForgotPasswordView view) {
        this.view = view;
    }

    void requestResetEmail(String email) {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.invalidEmail();
        }
        else {
            GlobalFunction.getFirebaseAuth().sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    view.requestSuccessful();
                }
                else {
                    view.requestFailed(task);
                }
            });
        }
    }
}
