package chess.service;

import chess.dao.RoomDao;
import chess.dao.RoomRepository;
import chess.domain.chess.Piece;
import chess.domain.entity.Room;
import chess.domain.entity.RoomStatus;
import chess.dto.RoomDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class RoomService {
    private static final boolean ONGOING_STATUS = false;

    private RoomDao roomDao;
    private RoomRepository roomRepository;

    public RoomService(final RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room openRoom() {
        return roomRepository.add();
    }

    public long latestId() {
//		return roomDao.getLatestId().orElseThrow(IllegalArgumentException::new);
        try {
            return roomRepository.getLatestId();
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    public void updateStatus(final long roomId, final Piece.Color color) {
//        roomDao.updateStatus(roomId, color.getName());
        roomRepository.checkWinner(roomId, color.getName());
    }

    public List<RoomDto> findAllByOngoing() {
        List<Room> rooms = roomRepository.findAllByStatus(RoomStatus.AVAILABLE);
        return convertToDto(rooms);
    }

    private List<RoomDto> convertToDto(List<Room> rooms) {
        return rooms.stream()
                .map(room -> new RoomDto(room.getId(), room.getStatus(), room.getWinner()))
                .collect(toList());
    }

    public void deleteAll() {
        roomRepository.deleteAll();
    }
}
