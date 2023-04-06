package view;

import auth.Authentication;
import controller.UserController;
import dto.ROLE;
import session.UserSession;

import java.util.Scanner;

public class MainView {
    private static UserSession userSession = UserSession.getInstance();
    public static Scanner sc = new Scanner(System.in);
    public static boolean login() {

        System.out.println("아이디를 입력하세요.");
        String id = sc.nextLine();
        System.out.println("비밀번호를 입력하세요.");
        String pw = sc.nextLine();

        if (Authentication.login(id, pw)) {
            UserSuccessView.messagePrint("로그인에 성공했습니다.");
            return true;
        } else {
            UserSuccessView.messagePrint("일치하는 사용자 정보가 없습니다. 초기화면으로 돌아갑니다.");
            return false;
        }




    }

    public static void start() {
        while (true) {
            System.out.println("[1. 로그인         2. 회원가입]");
            String opt = sc.nextLine();

            if (opt.length() <= 0) {
                System.out.println("유효하지 않은 입력입니다. 다시 입력해주세요.");
                System.out.println("[1. 로그인         2. 회원가입]");
                continue;
            }
            switch (Integer.parseInt(opt)) {
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case 1:
                    MainView.login();
                    return;
                case 2:
                    UserView.join();
                    break;
                default:
                    System.out.println("유효하지 않은 입력입니다.");
                    break;
            }
        }
    }
    public static void adminView() {
        System.out.println("***** 관리자 화면입니다 *****");
        System.out.println("[ 1. 유저관리         2. 반 관리]");
        int option = Integer.parseInt(sc.nextLine());
        if (option == 1) {
            UserController.main();
        } else if (option == 2) {
            BanView.banChoice();
        }
    }

    public static void userView() {
        System.out.println("***** 유저 화면입니다 *****");
        ChannelView.channelChoice();
    }
}
