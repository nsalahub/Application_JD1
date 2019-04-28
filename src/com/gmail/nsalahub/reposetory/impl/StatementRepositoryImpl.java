package com.gmail.nsalahub.reposetory.impl;

import com.gmail.nsalahub.reposetory.StatementRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementRepositoryImpl implements StatementRepository {


    private static StatementRepositoryImpl instance;

    private StatementRepositoryImpl() {
    }

    public static StatementRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new StatementRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void executeStatement(Connection connection, String createTableSql) {
        try (Statement statement = connection.createStatement()) {
            System.out.println("--------Statement------");
            statement.execute(createTableSql);
        } catch (SQLException e) {
            System.out.println("Exception Statement");
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }


}
