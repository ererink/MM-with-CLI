package view;

import auth.Authentication;
import dto.ROLE;
import session.UserSession;

import java.util.Scanner;

public class MainView {
    private static UserSession userSession = UserSession.getInstance();
    public static Scanner sc = new Scanner(System.in);
    public static void login() {
        System.out.println("***********프로그램 시작합니다.***************");
        System.out.println("종료하려면 0을 입력하세요..");
        System.out.println();
        boolean loginFlag = false;

        System.out.println("아이디를 입력하세요.");
        String id = sc.nextLine();
        System.out.println("비밀번호를 입력하세요.");
        String pw = sc.nextLine();

        if (id == "0" || pw == "0") {
            throw new RuntimeException();
            //로그인 실패 예외
        } else {
            try {
                Authentication.login(id, pw);
            } catch (RuntimeException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }

        }

    }
}
