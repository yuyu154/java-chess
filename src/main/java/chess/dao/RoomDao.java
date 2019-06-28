package chess.dao;

import chess.config.DbConnector;
import chess.config.JdbcTemplate;
import chess.dto.RoomDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RoomDao {
	private final DbConnector dbConnector;
	private final JdbcTemplate jdbcTemplate;

	private RoomDao(final DbConnector dbConnector) {
		this.dbConnector = dbConnector;
		jdbcTemplate = new JdbcTemplate(dbConnector);
	}

	public static RoomDao from(final DbConnector dbConnector) {
		return new RoomDao(dbConnector);
	}

	public void add() {
		String sql = "INSERT INTO room () VALUES()";
		List<Object> objects = Collections.emptyList();
		jdbcTemplate.executeUpdate(sql, objects);
	}

	public Optional<RoomDto> findById(final long id) {
		String sql = "SELECT id, status, winner FROM room WHERE id = ?";
		final List<Object> objects = new ArrayList<>();
		objects.add(id);
		final RoomDto ret = jdbcTemplate.executeQuery(sql, objects, rs -> {
			if (rs.next()) {
				final RoomDto roomDto = new RoomDto();
				roomDto.setId(rs.getLong("id"));
				roomDto.setStatus(rs.getBoolean("status"));
				roomDto.setWinner(rs.getString("winner"));
				return roomDto;
			}
			return null;
		});
		return Optional.ofNullable(ret);
	}

	public List<RoomDto> findAllByStatus(final boolean status) {
		final String sql = "SELECT id, status, winner FROM room WHERE status = ?";
		final List<Object> objects = new ArrayList<>();
		objects.add(status);

		List<RoomDto> ret = jdbcTemplate.executeQuery(sql, objects, rs -> {
			final List<RoomDto> roomDtos = new ArrayList<>();
			while (rs.next()) {
				final RoomDto roomDto = new RoomDto();
				roomDto.setId(rs.getLong("id"));
				roomDto.setStatus(rs.getBoolean("status"));
				roomDto.setWinner(rs.getString("winner"));
				roomDtos.add(roomDto);
			}
			return roomDtos;
		});
		return ret;
	}

	public void updateStatus(final long id, final String winner) {
		String sql = "UPDATE room SET status = TRUE, winner = ? WHERE id = ?";
		final List<Object> objects = new ArrayList<>();
		objects.add(winner);
		objects.add(id);
		jdbcTemplate.executeUpdate(sql, objects);
	}

	public Optional<Long> getLatestId() {
		String sql = "SELECT id FROM room ORDER BY id DESC LIMIT 1";
		Long ret = jdbcTemplate.executeQuery(sql, rs -> {
				if (rs.next()) {
					return rs.getLong("id");
				}
				return null;
		});
		//TODO 복습 Optional
		return Optional.ofNullable(ret);
	}

	public void deleteAll() {
		String sql = "DELETE FROM room";
		List<Object> objects = Collections.EMPTY_LIST;
		jdbcTemplate.executeUpdate(sql, objects);
		initializeIncrement();
	}

	private void initializeIncrement() {
		String sql = "ALTER TABLE room ALTER COLUMN id RESTART WITH 1";
		List<Object> objects = Collections.EMPTY_LIST;
		jdbcTemplate.executeUpdate(sql, objects);
	}
}
