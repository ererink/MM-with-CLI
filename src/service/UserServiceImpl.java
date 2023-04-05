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
        return dao.selectAll();
    }
    @Override
    public List<UserDTO> selectByClass(long id) {
        return dao.selectByClass(id);
    }

    @Override
    public Optional<UserDTO> userSelectOne(String id) {
        return dao.selectOne(id);
    }

    @Override
    public int userUpdate(UserDTO userDTO) {
        return dao.update(userDTO);
    }

    @Override
    public int userDelete(String id) {
        return dao.delete(id);
    }

    @Override
    public int addUser(UserDTO dto) {
        return dao.join(dto);
    }

    @Override
    public boolean checkUser(UserDTO userDTO) {
        return false;
    }
}
