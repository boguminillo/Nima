package com.example.nima.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.nima.data.model.LoggedInUser;
import com.example.nima.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private final MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    LoggedInUser user = null;


    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                user = new LoggedInUser(Objects.requireNonNull(mAuth.getCurrentUser()).getUid(), mAuth.getCurrentUser().getEmail());
                loginResult.setValue(new LoginResult(new LoggedInUserView(user.getDisplayName())));
            } else {
                loginResult.setValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    /**
     * Username validation check
     *
     * @param username the username to validate
     * @return true if the username is valid, false otherwise
     */
    private boolean isUserNameValid(String username) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
    }

    /**
     * Password validation check
     *
     * @param password the password to validate
     * @return true if the password is valid, false otherwise
     */
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() >= 6;
    }
}