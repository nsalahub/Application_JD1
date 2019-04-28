package com.gmail.nsalahub.reposetory.impl;

import com.gmail.nsalahub.reposetory.LoginRepository;
import com.gmail.nsalahub.reposetory.model.PermissionEnum;
import com.gmail.nsalahub.reposetory.model.Role;
import com.gmail.nsalahub.reposetory.model.User;
import com.gmail.nsalahub.reposetory.model.UserRegistration;
import com.gmail.nsalahub.servises.exeptions.LoginException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginRepositoryImpl implements LoginRepository {

    private static LoginRepositoryImpl instance;

    private LoginRepositoryImpl() {
    }

    public static LoginRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new LoginRepositoryImpl();
        }
        return instance;
    }

    @Override
    public UserRegistration getUserByEmail(Connection connection, String email) throws LoginException {
        UserRegistration user = null;
        String sqlRequest = " SELECT users.id, users.role_id, users.password, role.name AS role_name," +
                "permission.name AS  permission_name FROM users JOIN role ON users.role_id=role.id " +
                "JOIN role_permission ON role.id=role_permission.role_id " +
                "JOIN permission ON role_permission.permission_id=permission.id WHERE users.email =?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            System.out.println("-----------Select User from database----------");
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = getUserFromDataBase(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Select permission from user problem---------");
            throw new LoginException(e);
        }
        return user;
    }

    private UserRegistration getUserFromDataBase(ResultSet resultSet) throws SQLException {
        UserRegistration user = new UserRegistration();
        Role role = new Role();
        user.setPassword(resultSet.getString("password"));
        PermissionEnum permissionEnum = PermissionEnum.valueOf(resultSet.getString("permission_name"));
        List<PermissionEnum> permissionEnumList = new ArrayList<>();
        permissionEnumList.add(permissionEnum);
        role.setId(resultSet.getLong("role_id"));
        role.setName(resultSet.getString("role_name"));
        role.setPermissionEnums(permissionEnumList);
        user.setRole(role);
        return user;
    }
}
