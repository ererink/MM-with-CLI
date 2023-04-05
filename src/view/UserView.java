package view;

import controller.UserController;
import dto.ROLE;
import dto.UserDTO;
import session.UserSession;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class UserView {

//    UserController userController = UserController.getInstance();
//    private UserView(UserController userController) {
//        this.userController = userController;
//    }
    static UserSession userSession = UserSession.getInstance();
    private static final UserController userController = UserController.getInstance();
    private static Scanner sc = new Scanner(System.in);

    public static void main() {
        System.out.println("---------------------------------");
        System.out.println("** 유저 관리 화면 입니다 **");
        System.out.println("1. 유저 추가");
        System.out.println("2. 유저 인가");
        System.out.println("3. 유저 수정");
        System.out.println("4. 유저 삭제");
        System.out.println("---------------------------------");

        try {
            switch (Integer.parseInt(sc.nextLine())) {
                case 0:
                    userSession.setChannel_id(-1);
                case 1:
                    join();
                    break;
                case 2:
                    getAllUser();
                    authorizeUser();
                    break;
                case 3:
                    getAllUser();
                    update();
                    break;
                case 4:
                    getAllUser();
                    delete();
                    break;
                default:
                    System.out.println("잘못된 번호 입니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요.");
        }
    }

    private static void join() {
        String id = "";
        String pw = "";
        String name = "";
        try {
            System.out.println("ID를 입력하세요..");
            id = sc.nextLine();
            System.out.println("패스워드를 입력하세요..");
            pw = sc.nextLine();
            System.out.println("이름를 입력하세요..");
            name = sc.nextLine();
            userController.join(id, pw, name);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("회원 가입에 실패했습니다.");
//            System.out.println("회원 가입에 실패 했습니다.\n초기 화면으로 돌아갑니다.");
//            UserView.main();
        }

    }

//    private static void authorizeAdmin() {
//        String id = "";
//        String role = "";
//        try {
//            System.out.println("유저 ID를 입력하세요.. ");
//            id = sc.nextLine();
//            System.out.println("배정할 반을 입력하세요..");
//            role = sc.nextLine();
//            userController.authorizeUser(id, ROLE.valueOf(role));
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            throw new RuntimeException("인가 처리에 실패했습니다.");
//        }
//    }
    private static void authorizeUser() {
        String id = "";
        int class_id = 0;
        try {
            System.out.println("유저 ID를 입력하세요.. ");
            id = sc.nextLine();
            System.out.println("배정할 반을 입력하세요..");
            class_id = Integer.parseInt(sc.nextLine());
            userController.authorizeUser(id, class_id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("인가 처리에 실패했습니다.");
        }
    }

    private static void update() {
        String id = "";
        String name = "";
        String pw = "";
        try {
            System.out.println("유저 ID를 입력하세요..");
            id = sc.nextLine();
            System.out.println("변경할 이름을 입력하세요..");
            name = sc.nextLine();
            System.out.println("변경할 패스워드를 입력하세요..");
            pw = sc.nextLine();

            userController.update(id, new UserDTO(id, pw, name));
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("수정을 완료할 수 없습니다.");
        }
    }

    private static void delete() {
        String id = "";
        try {
            System.out.println("유저 ID를 입력하세요..");
            id = sc.nextLine();
            System.out.println("정말로 삭제합니까?");
            System.out.println("1. 확인");
            System.out.println("2. 취소");
            switch (Integer.parseInt(sc.nextLine())) {
                case 1:
                    System.out.println(id + "를 삭제합니다.");
                    userController.delete(id);
                    break;
                case 2:
                    System.out.println("삭제 작업을 취소합니다.");
                    break;
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("삭제할 수 없습니다.");
        }

    }

    public static void getAllUser() {
        List<UserDTO> users = userController.getAllUsers();
        System.out.println("|   아이디   |    이름   |    ROLE   |   반 번호  ");
        for (UserDTO user : users) {
            System.out.println(print(user));
        }
    }

    public static String print(UserDTO userDTO) {
        return userDTO.getUser_id()
                + " "
                + userDTO.getName()
                + " "
                + userDTO.getRole()
                + " "
                + userDTO.getClass_id();
    }
}
