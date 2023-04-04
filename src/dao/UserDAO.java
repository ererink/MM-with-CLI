package dao;


import dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<UserDTO> selectAll();

    Optional<UserDTO> selectOne(int id);

    String join(String id, String pw, String name);

    String update(UserDTO dto);
}
