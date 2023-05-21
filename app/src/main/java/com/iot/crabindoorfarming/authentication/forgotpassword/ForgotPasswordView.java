package com.iot.crabindoorfarming.authentication.forgotpassword;

import com.google.android.gms.tasks.Task;

public interface ForgotPasswordView {
    void requestSuccessful();

    void requestFailed(Task<Void> task);

    void invalidEmail();
}
