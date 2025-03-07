package service.custom.impl;

import dbconnection.DBConnection;
import model.User;
import org.jasypt.util.text.BasicTextEncryptor;
import service.custom.LoginService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {
    private static LoginServiceImpl instance;
    private String key = "12345";

    private LoginServiceImpl() {
    }

    public static LoginServiceImpl getInstance() {
        return instance != null ? instance : new LoginServiceImpl();
    }

    @Override
    public boolean registerUser(User user) {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            if (!connection.createStatement().executeQuery("SELECT *" + "FROM users WHERE email='" + user.getEmail() + "'").next()) {
                PreparedStatement prepareStm = connection.prepareStatement("INSERT INTO users (username,email,password) VALUES (?,?,?)");
                prepareStm.setString(1, user.getUserName());
                prepareStm.setString(2, user.getEmail());
                prepareStm.setString(3, basicTextEncryptor.encrypt(user.getPassword()));
                return prepareStm.executeUpdate() > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean loginUser(User user) {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);
        try {
            ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM users WHERE email='" + user.getEmail() + "'");
            return rst.next() && basicTextEncryptor.decrypt(rst.getString(4)).equals(user.getPassword());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
