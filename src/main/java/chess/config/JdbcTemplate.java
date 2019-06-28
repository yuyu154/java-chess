package chess.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class JdbcTemplate {
    private final DbConnector dbConnector;

    public JdbcTemplate(final DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void executeUpdate(final String sql, final List<Object> objects) {
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            for (final Object object : objects) {
                ps.setObject(index++, object);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> T executeQuery(final String sql, final List<Object> objects, final RowMapper<T> rowMapper) {
        try (Connection con = dbConnector.getConnection();
             PreparedStatement ps = createPreparedStatement(con, sql, objects);
             ResultSet rs = ps.executeQuery()) {
            return rowMapper.mapRow(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T executeQuery(final String sql, final RowMapper<T> rowMapper) {
        return executeQuery(sql, Collections.emptyList(), rowMapper);
    }

    private PreparedStatement createPreparedStatement(
            final Connection conn, final String sql, final List<Object> objects) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        int index = 1;
        for (final Object object : objects) {
            ps.setObject(index++, object);
        }
        return ps;
    }
}
