package view;

import controller.UserController;
import dto.ROLE;
import dto.UserDTO;
import exception.common.InValidInputException;
import exception.user.UserDeleteFailureException;
import exception.user.UserLoadFailureException;
import exception.user.UserUpdateFailureException;
import print.PrintClass;
import session.UserSession;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class UserView {

    //    UserController userController = UserController.getInstance();
//    private UserView(UserController userController) {
//        this.userController = userController;
//    }
    private static PrintClass printClass = PrintClass.getInstance();
    static UserSession userSession = UserSession.getInstance();
    private static final UserController userController = UserController.getInstance();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        userSession.setUser_id("adoo24");
        main();
    }
    public static void main() {
        System.out.println(" __    __   ______   ________  _______  \n" +
                "/  |  /  | /      \\ /        |/       \\ \n" +
                "$$ |  $$ |/$$$$$$  |$$$$$$$$/ $$$$$$$  |\n" +
                "$$ |  $$ |$$ \\__$$/ $$ |__    $$ |__$$ |\n" +
                "$$ |  $$ |$$      \\ $$    |   $$    $$< \n" +
                "$$ |  $$ | $$$$$$  |$$$$$/    $$$$$$$  |\n" +
                "$$ \\__$$ |/  \\__$$ |$$ |_____ $$ |  $$ |\n" +
                "$$    $$/ $$    $$/ $$       |$$ |  $$ |\n" +
                " $$$$$$/   $$$$$$/  $$$$$$$$/ $$/   $$/ s");
        System.out.println();

        while (true){
            System.out.println();
            System.out.print("=============================================");
            System.out.print(" ?? ");
            System.out.print("=============================================\n");
            System.out.print("∥  0. 뒤로 가기   ");
            System.out.print("1. 유저 추가하기   ");
            System.out.print("2. 유저 인가하기   ");
            System.out.print("3. 유저 수정하기   ");
            System.out.print("4. 유저 삭제하기  ");
            System.out.print("5. 유저 목록 확인하기  ∥");
            System.out.println("\n==============================================================================================");
            System.out.print("선택 ▶ ");

            try {

                switch (Integer.parseInt(sc.nextLine())) {
                    case 0:
                        userSession.setChannel_id(-1);
                        MainView.adminView();
                        return;
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
                    case 5:
                        getAllUser();
                        break;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요!");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자 입력만 가능해요!");
            } catch (InValidInputException e) {
                System.out.println("공백을 입력으로 허용하지 않습니다!");
            }
        }

    }

    public static void join() {
        String id = "";
        String pw = "";
        String name = "";

        System.out.println();
        System.out.println("====================== 추가할 유저 정보를 입력해주세요 ======================");
        System.out.print("ID ▶ ");
        id = sc.nextLine();
        System.out.print("PASSWORD ▶ ");
        pw = sc.nextLine();
        System.out.print("NAME ▶ ");
        name = sc.nextLine();
        userController.join(id, pw, name);


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
    private static void authorizeUser(){
        String id = "";
        int class_id = 0;
        System.out.println();
        System.out.println("====================== 반을 배정할 유저 정보를 입력해주세요 ======================");
        try {
            System.out.print("ID ▶ ");
            id = sc.nextLine();
            System.out.print("CLASS ▶ ");
            class_id = Integer.parseInt(sc.nextLine());
            if (id.length() <= 0 || class_id == 0) {
                throw new InValidInputException("공백을 입력으로 허용하지 않습니다!");
            }
            userController.authorizeUser(id, class_id);
        } catch (InValidInputException e) {
            UserFailView.errorMessage(e.getMessage());
        }
    }

    private static void update() throws InValidInputException {
        String id = "";
        String name = "";
        String pw = "";
        
        System.out.println();
        System.out.println("========================== 수정할 유저 정보를 입력해주세요 ==========================");
        System.out.print("ID ▶ ");
        id = sc.nextLine();

        System.out.print("NAME ▶ ");
        name = sc.nextLine();

        System.out.print("PASSWORD ▶ ");
        pw = sc.nextLine();

        userController.update(id, new UserDTO(id, pw, name));

    }

    private static void delete() {
        String id = "";
        
        System.out.println();
        System.out.println("========================== 삭제할 유저 정보를 입력해주세요 ==========================");

        System.out.print("ID ▶ ");
        id = sc.nextLine();
        if (id.length() <= 0) {
            System.out.println("유효한 입력이 아닙니다!");
            return;
        }
        System.out.println(id + "님을 삭제할까요?");

        System.out.println("\n------------------");
        System.out.print(" 1. 확인  ");
        System.out.println(" 2. 취소  ");
        System.out.println("------------------");
        System.out.print("선택 ▶ ");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                System.out.println(id + "를 삭제합니다");
                userController.delete(id);
                break;
            case 2:
                System.out.println("삭제 작업을 취소합니다");
                break;
            default:
                System.out.println("유효하지 않은 입력입니다. 이전 메뉴로 돌아갑니다!");
        }



    }

    public static void getAllUser() {
        List<UserDTO> users = userController.getAllUsers();
        printClass.addRow();
        printClass.addElement("ID");
        printClass.addElement("NAME");
        printClass.addElement("ROLE");
        printClass.addElement("CLASS_ID");
        printClass.addRow();
        printClass.addElement("-------------------------------------------------------------------------------------------------------------------------                                ");
        for (UserDTO user : users) {
            printClass.addRow();
            printClass.addElement(user.getUser_id());
            printClass.addElement(user.getName());
            printClass.addElement(user.getRole().toString());
            printClass.addElement(Long.toString(user.getClass_id()));
        }
        printClass.printCurrent();
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
