package chess.config;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T mapRow(final ResultSet resultSet) throws SQLException;
}
