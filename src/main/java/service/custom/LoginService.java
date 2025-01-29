package service.custom;

import model.User;
import service.SuperService;

public interface LoginService extends SuperService {
    boolean registerUser(User user);
    boolean loginUser(User user);

}
