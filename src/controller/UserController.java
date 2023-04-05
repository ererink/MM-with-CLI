package controller;

import dto.ROLE;
import dto.UserDTO;
import service.UserService;
import service.UserServiceImpl;
import view.UserView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserController {

    private final UserService userService;
    /**
     * DB 변경시 생성자 내 인자를 변경
     */
    private static final UserController instance = new UserController(UserServiceImpl.getInstance());
    private UserController(UserService userService) {
        this.userService = userService;
    }


    public static UserController getInstance() {
        return instance;
    }

    public static void main() {
        UserView.main();
    }


    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        try {
            users = userService.userSelectAll();
            if (users.size() <= 0) {
                // 2023/04/05_yeoooo : NoUserFoundException 으로 대체
                // failView 출력
                throw new RuntimeException();
            }
            //Successview 추가
        } catch (RuntimeException e) {
            //SQL Exception을 처리할 예외 추가 필요
            //Failview 추가
        }


        return users;
    }

    /**
     *
     * @param id(class_id)
     * @return
     */
    public void getAllUsersByClass(long id) {
        try {
            List<UserDTO> users = userService.selectByClass(id);
            if (users.size() <= 0) {
                // 2023/04/05_yeoooo : NoUserFoundException 으로 대체
                throw new RuntimeException();
            }
            //SuccessView
        } catch (RuntimeException e) {
            //SQL Exception을 처리할 예외 추가 필요
            //Failview 추가
        }
    }

    /**
     *
     * @param id(user_id)
     * @return
     */
    public void getOneUser(String id) {
        try {
            Optional<UserDTO> user = userService.userSelectOne(id);
            if (user.isEmpty()) {
                // 2023/04/05_yeoooo : NoUserFoundException 으로 대체
                throw new RuntimeException();
            }
            //SuccessView
        } catch (RuntimeException e) {
            //SQL Exception 대응 예외
            //FailView
        }

    }

    public void join(String id, String pw, String name) {
        if (id.length() <= 0 || pw.length() <= 0 || name.length() <= 0) {
            // 2023/04/05_yeoooo : InvalidInputException 으로 대체
            throw new RuntimeException();
        }
        UserDTO user = new UserDTO(id, pw, name);
        try {
            userService.addUser(user);
            //SuccessView

        } catch (RuntimeException e) {
            // 2023/04/05_yeoooo : IllegalStateException ? 으로 대체
            //FailView
        }

    }

    /**
     * userDao.update() 를 통해 가입 승인이 되지 않은(Class column이 N인 유저)를
     * 인가 처리
     * 및
     * 관리자로 변경
     *
     * @return
     */
    public void authorizeUser(String id, int class_id) {
        try {
            UserDTO user = userService.userSelectOne(id).get();
            UserDTO target = new UserDTO(user.getUser_id(),
                    user.getUser_pw(),
                    user.getName(),
                    ROLE.U,
                    class_id);
            userService.userUpdate(target);
        } catch (RuntimeException e) {
            // 2022/04/05_yeoooo: IllegalStateException 으로 대체


        }
    }

    public void update(String id, UserDTO target) {
        try {
            UserDTO user = userService.userSelectOne(id).get();
            UserDTO to = new UserDTO(id,
                    target.getUser_pw(),
                    target.getName(),
                    user.getRole(),
                    user.getClass_id()
            );
            userService.userUpdate(to);
        } catch (RuntimeException e) {

        }
    }

    public void delete(String id) {
        try {
            userService.userDelete(id);
            //Success View
        } catch (RuntimeException e) {
            // 2022/04/05_yeoooo: IllegalStateException으로 대체
            // 위와 마찬가지로 이곳에는 FailView를 보여줘야 함.

        }

    }

    public boolean checkUser() {
        return false;
    }





}
