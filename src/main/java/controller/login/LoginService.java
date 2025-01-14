package controller.login;

import model.User;

public interface LoginService {
    boolean registerUser(User user);
    boolean loginUser(User user);

}
