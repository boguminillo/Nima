package com.example.nima.data;

import android.widget.Toast;

import com.example.nima.data.model.LoggedInUser;
import com.example.nima.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    LoggedInUser user = null;

    public Result<LoggedInUser> login(String username, String password) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(username, password);
        if (mAuth.getCurrentUser() != null) {
            user = new LoggedInUser(Objects.requireNonNull(mAuth.getCurrentUser()).getUid(), mAuth.getCurrentUser().getEmail());
            return new Result.Success<>(user);
        } else {
            return new Result.Error(new Exception("Error logging in"));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}