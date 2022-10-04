package com.example.nima.data;

import com.example.nima.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        // TODO: handle loggedInUser authentication
        if (username.equals("nima") && password.equals("123")) {
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } else {
            return new Result.Error(new IOException("Error logging in"));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}