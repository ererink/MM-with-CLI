package service;

import dao.UserDAO;
import dao.UserDAOImpl;
import dto.UserDTO;

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
    public List<UserDTO> userSelectAll() {
        List<UserDTO> users = null;
        try {
            users = dao.selectAll();

        } catch (RuntimeException e) {

        }
        return users;
    }
    @Override
    public List<UserDTO> selectByClass(long id) {
        List<UserDTO> users = null;
        try {
            users = dao.selectByClass(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return users;
    }

    @Override
    public Optional<UserDTO> userSelectOne(String id) {
        Optional<UserDTO> user = null;
        try {
            user = dao.selectOne(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return user;
    }

    @Override
    public int userUpdate(UserDTO userDTO) {
        try {
            return dao.update(userDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    public int userDelete(String id) {
        try {
            return dao.delete(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public int addUser(UserDTO dto) {
        try {
            return dao.join(dto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean checkUser(UserDTO userDTO) {
        return false;
    }
}
