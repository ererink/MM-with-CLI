package service;

import dao.UserDAO;
import dao.UserDAOImpl;
import dto.UserDTO;
import exception.user.UserDeleteFailureException;
import exception.user.UserJoinFailureException;
import exception.user.UserLoadFailureException;
import exception.user.UserUpdateFailureException;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDAO dao;
    /**
     * DB 변경시 생성자 내 인자를 변경
     */
    private static final UserService instance = new UserServiceImpl(UserDAOImpl.getInstance());

    private UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    public static UserService getInstance() {
        return instance;
    }

    @Override
    public List<UserDTO> userSelectAll() throws UserLoadFailureException {
        List<UserDTO> users = null;
        try {
            users = dao.selectAll();
        }
        catch (UserLoadFailureException e) {
            throw new UserLoadFailureException();
        }
        return users;
    }
    @Override
    public List<UserDTO> selectByClass(long id) throws UserLoadFailureException {
        List<UserDTO> users = null;
        try {
            users = dao.selectByClass(id);
        } catch (UserLoadFailureException e) {
            throw new UserLoadFailureException();
        }
        return users;
    }

    @Override
    public Optional<UserDTO> userSelectOne(String id) throws UserLoadFailureException {
        try {
            return dao.selectOne(id);
        } catch (UserLoadFailureException e) {
            throw new UserLoadFailureException();
        }
    }

    @Override
    public int userUpdate(UserDTO userDTO) throws UserUpdateFailureException {
        try {
            return dao.update(userDTO);
        } catch (UserUpdateFailureException e) {
            throw new UserUpdateFailureException();
        }

    }

    @Override
    public int userDelete(String id) throws UserDeleteFailureException {
        try {
            return dao.delete(id);
        } catch (UserDeleteFailureException e) {
            e.printStackTrace();
            throw new UserDeleteFailureException();
        }
    }

    @Override
    public int addUser(UserDTO dto) throws UserJoinFailureException {
        try {
            return dao.join(dto);
        } catch (UserJoinFailureException e) {
            throw new UserJoinFailureException();
        }
    }

    @Override
    public boolean checkUser(UserDTO userDTO) {
        return false;
    }
}
