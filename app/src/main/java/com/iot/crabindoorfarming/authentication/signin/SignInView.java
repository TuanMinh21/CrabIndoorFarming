package com.iot.crabindoorfarming.authentication.signin;

public interface SignInView {
    void signInSuccess();

    void signInFailed();

    void invalidEmail();

    void invalidPassword();
}
