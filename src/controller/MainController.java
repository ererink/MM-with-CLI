package controller;

import auth.Authentication;
import dto.ROLE;
import session.UserSession;
import view.BanView;
import view.ChannelView;
import view.MainView;
import view.UserView;

import java.util.Scanner;

import static view.MainView.adminView;
import static view.MainView.start;


public class MainController {
    static Scanner sc = new Scanner(System.in);
    ChannelController channelController = ChannelController.getInstance();
    public static UserController userController = UserController.getInstance();
    private static UserSession userSession = UserSession.getInstance();


    /**
     * 메인 화면이 되는 Controller의 서비스를 시작
     */
    public static void controllerService() {
        System.out.println("***********프로그램 시작합니다.***************");
        System.out.println("종료하려면 0을 입력하세요..");
        System.out.println();
        while (true) {
            MainView.start();

                if (userSession.getRole() == ROLE.A) {
                    MainView.adminView();
                } else if (userSession.getRole() == ROLE.U) {
                    MainView.userView();
                }
            }

        }






}
