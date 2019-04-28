package com.gmail.nsalahub.servises.impl;

import com.gmail.nsalahub.reposetory.StatementRepository;
import com.gmail.nsalahub.reposetory.impl.StatementRepositoryImpl;
import com.gmail.nsalahub.servises.BaseCreatorService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseCreatorServiceImpl implements BaseCreatorService {

    private static BaseCreatorServiceImpl instance;

    private BaseCreatorServiceImpl() {
    }

    public static BaseCreatorServiceImpl getInstance() {
        if (instance == null) {
            instance = new BaseCreatorServiceImpl();
        }
        return instance;
    }

    ConnectionServiceImpl connectionService = ConnectionServiceImpl.getInstance();
    StatementRepository createTableRepository = StatementRepositoryImpl.getInstance();



    @Override
    public void createTables() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getPath()))) {
            System.out.println("-----------------------Start Read file----------------");
            try (Connection connection = connectionService.getConnection()) {
                String line = bufferedReader.readLine();
                while (line != null) {
                    System.out.println(line);
                    createTableRepository.executeStatement(connection, line);
                    line = bufferedReader.readLine();
                }
            } catch (SQLException e) {
                System.out.println("sql problem create table-----------");
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getPath(){
        String path = this.getClass().getResource("sql.sql").getPath();
        return path;
    }
}
