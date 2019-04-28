package com.gmail.nsalahub.reposetory;

import java.sql.Connection;
import java.util.List;

public interface StatementRepository {
    void executeStatement(Connection connection, String createTableSql);
}
