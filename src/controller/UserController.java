package controller;

import dto.ROLE;
import dto.UserDTO;
import exception.common.InValidInputException;
import exception.user.*;
import service.UserService;
import service.UserServiceImpl;
import view.FailView;
import view.UserFailView;
import view.UserSuccessView;
import view.UserView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserController {

    private final UserService userService;

    private static final UserController instance = new UserController(UserServiceImpl.getInstance());
    private UserController(UserService userService) {
        this.userService = userService;
    }


    public static UserController getInstance() {
        return instance;
    }

    /**
     * UserController를 실행하는 함수
     */
    public static void main() {
        UserView.main();
    }


    /**@yeoooo
     *
     *
     * 전체 조회 결과에 따른 성공여부를 view로 출력
     *
     * null, SQL 예외 처리
     * @return
     */
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        try {
            users = userService.userSelectAll();
            if (users.size() <= 0) {
                throw new UserNotFoundException("유저가 존재하지 않습니다!");
            }
            //Successview 추가
        } catch (UserLoadFailureException e) {
            UserFailView.errorMessage(e.getMessage());
            //SQL Exception을 처리할 예외 추가 필요
            //Failview 추가
        } catch (UserNotFoundException e) {
            UserFailView.errorMessage(e.getMessage());
        }

        return users;
    }

    /**
     * @yeoooo
     *
     * class_id 에 속해있는 반 유저를 모두 조회하는 view를 출력
     *
     * null, SQL 예외 처리
     *
     * @param id(class_id)
     * @return view
     */
    public void getAllUsersByClass(long id) {
        try {
            List<UserDTO> users = userService.selectByClass(id);
            if (users.size() <= 0) {
                // 2023/04/05_yeoooo : NoUserFoundException 으로 대체
                throw new UserNotFoundException();
            }
            //SuccessView
        } catch (UserNotFoundException e) {
            //SQL Exception을 처리할 예외 추가 필요
            //Failview 추가
        } catch (UserLoadFailureException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * yeoooo
     *
     * 단건 조회 결과에 따른 성공 여부 출력
     *
     * 공백입력, SQL 예외 처리
     *
     * @param id(user_id)
     * @return view
     */
    public void getOneUser(String id) {
        try {
            Optional<UserDTO> user = userService.userSelectOne(id);
            if (user.isEmpty()) {
                // 2023/04/05_yeoooo : NoUserFoundException 으로 대체
                throw new UserNotFoundException();
            }
            System.out.println("\n-------------");
            UserSuccessView.messagePrint(" * 조회 완료 *");
            System.out.println("-------------");
            //SuccessView
        } catch (UserNotFoundException e) {
            UserFailView.errorMessage(e.getMessage());
            //FailView
        } catch (UserLoadFailureException e) {
            //SQL Exception 대응 예외
            UserFailView.errorMessage(e.getMessage());
        }

    }

    /**
     * yeoooo
     * 회원 가입 View를 제공
     * 공백 입력 예외처리
     * @param id
     * @param pw
     * @param name
     *
     * @return view
     */
    public void join(String id, String pw, String name) {
        try {
        if (id.length() <= 0 || pw.length() <= 0 || name.length() <= 0) {
            // 2023/04/05_yeoooo : InvalidInputException 으로 대체
            throw new InValidInputException("공백은 입력으로 허용되지 않습니다!");
        }
        UserDTO user = new UserDTO(id, pw, name);
            userService.addUser(user);

            System.out.println("\n-----------------");
            UserSuccessView.messagePrint(" * 회원가입 완료 *");
            System.out.println("-----------------");

        } catch (UserJoinFailureException e) {
            UserFailView.errorMessage(e.getMessage());
        } catch (InValidInputException e) {
            UserFailView.errorMessage(e.getMessage());

        }

    }

    /**
     * userDao.update() 를 통해 가입 승인이 되지 않은(Class column이 N인 유저)를
     * 인가 처리
     * 및
     * 관리자로 변경
     *
     *
     *
     * @return view
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

            System.out.println("\n-----------------");
            UserSuccessView.messagePrint(" * 반 배정 완료 *");
            System.out.println("-----------------");

        } catch (UserUpdateFailureException e) {
            UserFailView.errorMessage(e.getMessage());

        } catch (UserLoadFailureException e) {
            UserFailView.errorMessage(e.getMessage());
        }
    }

    public void update(String id, UserDTO target) {
        try {
            Optional<UserDTO> user = userService.userSelectOne(id);

            if (user.isEmpty()) {
                throw new UserNotFoundException();
            }
            UserDTO to = new UserDTO(id,
                    target.getUser_pw(),
                    target.getName(),
                    user.get().getRole(),
                    user.get().getClass_id()
            );
            userService.userUpdate(to);

            System.out.println("\n-----------------");
            UserSuccessView.messagePrint(" * 유저 수정 완료 *");
            System.out.println("-----------------");
        } catch (UserUpdateFailureException e) {
            UserFailView.errorMessage(e.getMessage());
        } catch (UserLoadFailureException e) {
            UserFailView.errorMessage(e.getMessage());
        } catch (UserNotFoundException e) {
            UserFailView.errorMessage(e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            if (id == null || id.length() <= 0) {
                throw new InValidInputException();
            }
            userService.userDelete(id);
            //Success View
        } catch (InValidInputException e) {
            FailView.errorMessage(e.getMessage());

        } catch (UserDeleteFailureException e) {
            UserFailView.errorMessage(e.getMessage());
        }

    }

    public boolean checkUser() {
        return false;
    }





}
