package org.yorm.db.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.yorm.YormTable;
import org.yorm.exception.YormException;

public class QueryDelete {

    private QueryDelete() {
    }

    public static void delete(DataSource ds, YormTable yormTable, int id) throws YormException {
        StringBuilder query = new StringBuilder("DELETE ");
        query.append(" FROM ")
            .append(yormTable.dbTable())
            .append(" WHERE id=?");
        try (Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new YormException("Error while deleting record with id:" + id + " from table:" + yormTable.dbTable(), e);
        }
    }

}
