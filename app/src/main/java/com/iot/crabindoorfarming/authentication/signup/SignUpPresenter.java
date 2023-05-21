package com.iot.crabindoorfarming.authentication.signup;

import android.util.Patterns;

import com.google.firebase.database.FirebaseDatabase;
import com.iot.crabindoorfarming.common.GlobalFunction;
import com.iot.crabindoorfarming.model.User;

public class SignUpPresenter {
    private final SignUpView view;
    private boolean isFailed = false;

    public SignUpPresenter(SignUpView view) {
        this.view = view;
    }

    public void signUp(String username, String email, String password, String confirmPassword) {

        if(username.equals("")) {
            view.invalidUsername();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.invalidEmail();
            isFailed = true;
        }
        if(password.length() < 6) {
            view.invalidPassword();
            isFailed = true;
        }
        if(confirmPassword.equals("") || !password.equals(confirmPassword)) {
            view.invalidConfirmPassword();
            isFailed =true;
        }

        if(!isFailed) {
            GlobalFunction.getFirebaseAuth()
                    .createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if(task.isSuccessful()) {
                            User.getInstance().setUser(email, password);
                            FirebaseDatabase.getInstance()
                                    .getReference("users")
                                    .child(username)
                                    .setValue(User.getInstance());
                            view.signUpSuccess();
                        }
                        else
                            view.signUpFailed();
                    });
        }
    }
}
