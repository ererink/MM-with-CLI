package controller;

import auth.Authentication;
import dto.ROLE;
import session.UserSession;
import view.BanView;
import view.ChannelView;
import view.MainView;

import java.util.Scanner;


public class MainController {
    static Scanner sc = new Scanner(System.in);
    ChannelController channelController = ChannelController.getInstance();
    public static UserController userController = UserController.getInstance();
    private static UserSession userSession = UserSession.getInstance();


    /**
     * 메인 화면이 되는 Controller의 서비스를 시작
     */
    public static void controllerService() {

        MainView.login();
        if (userSession.getRole() == ROLE.A) {
            System.out.println("***** 관리자 화면입니다 *****");
            System.out.println("[ 1. 유저관리         2. 반 관리]");
            int option = Integer.parseInt(sc.nextLine());
            if (option == 1) {
                UserController.main();
            } else if (option == 2) {
                BanView.banChoice();
            }



            } else if (userSession.getRole() == ROLE.U) {
                System.out.println("***** 유저 화면입니다 *****");
                ChannelView.channelChoice();
            }




    }
}
