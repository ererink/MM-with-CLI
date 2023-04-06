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
        System.out.println();
        System.out.println("==================== 아이디와 비밀번호를 입력해주세요 ====================");
        System.out.print("아이디 ▶ ");
        String id = sc.nextLine();
        System.out.print("비밀번호 ▶ ");
        String pw = sc.nextLine();

        if (Authentication.login(id, pw)) {
            System.out.println("\n-----------------");
            UserSuccessView.messagePrint(" * 로그인 성공 *");
            System.out.println("-----------------");
            return true;
        } else {
            System.out.println("\n------------------------------------------------");
            UserSuccessView.messagePrint(" * 일치하는 사용자 정보가 없습니다. 초기화면으로 돌아갑니다! *");
            System.out.println("------------------------------------------------");
            return false;
        }




    }

    public static void start() {
        while (true) {
            System.out.println("\n-----------------------------------");
            System.out.println("∥ 1. 로그인            2. 회원가입 ∥");
            System.out.println("-----------------------------------");
            System.out.print("선택 ▶ ");
            String opt = sc.nextLine();

            if (opt.length() <= 0) {
                System.out.println("유효하지 않은 입력입니다. 다시 입력해주세요!");
                System.out.println("\n-----------------");
                System.out.println("∥ 1. 로그인       2. 회원가입 ∥");
                System.out.println("-----------------");
                System.out.print("선택 ▶ ");
                continue;
            }
            switch (Integer.parseInt(opt)) {
                case 0:
                    System.out.println("  종료 --<-<-<@,,, ADIOS,,,");
                    return;
                case 1:
                    MainView.login();
                    return;
                case 2:
                    UserView.join();
                    break;
                default:
                    System.out.println("유효하지 않은 입력입니다!");
                    break;
            }
        }
    }
    public static void adminView() {
        System.out.println("++++++++ ADMIN ++++++++");
        System.out.println();
        System.out.println("\n--------------------");
        System.out.println("∥ 1. 유저 관리       2. 반 관리 ∥");
        System.out.println("---------------------");
        int option = Integer.parseInt(sc.nextLine());
        if (option == 1) {
            UserController.main();
        } else if (option == 2) {
            BanView.banChoice();
        }
    }

    public static void userView() {
        System.out.println();
        System.out.println("+++++++++++++ 환영합니다, "+userSession.getUser_id()+" 님! +++++++++++++");
        System.out.println();
        ChannelView.channelChoice();
    }
}
