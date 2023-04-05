package dao;


import dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<UserDTO> selectAll();

    public List<UserDTO> selectByClass(long id);

    Optional<UserDTO> selectOne(String id);

    int join(UserDTO dto);

    int update(UserDTO dto);

    int delete(String id);


}
