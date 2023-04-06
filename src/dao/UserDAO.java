package dao;


import dto.UserDTO;
import exception.user.*;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<UserDTO> selectAll() throws UserLoadFailureException;

    public List<UserDTO> selectByClass(long id) throws UserLoadFailureException;

    Optional<UserDTO> selectOne(String id) throws UserLoadFailureException;

    int join(UserDTO dto) throws UserJoinFailureException;

    int update(UserDTO dto) throws UserUpdateFailureException;

    int delete(String id) throws UserDeleteFailureException;


}
