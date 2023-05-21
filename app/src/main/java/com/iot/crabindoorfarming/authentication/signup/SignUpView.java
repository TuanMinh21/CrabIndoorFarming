package com.iot.crabindoorfarming.authentication.signup;

public interface SignUpView {
    void signUpSuccess();

    void signUpFailed();

    void invalidEmail();

    void invalidPassword();

    void invalidConfirmPassword();

    void invalidUsername();
}
