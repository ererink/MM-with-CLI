package controller;

import auth.Authentication;
import dto.ROLE;
import session.UserSession;
import view.ChannelView;
import view.MainView;

import java.util.Scanner;


public class MainController {
    ChannelController channelController = ChannelController.getInstance();
    public static UserController userController = UserController.getInstance();
    private static UserSession userSession = UserSession.getInstance();



    public static void controllerService() {

        MainView.login();
        if (userSession.getRole() == ROLE.A) {
            System.out.println("***** 관리자 화면입니다 *****");



            } else if (userSession.getRole() == ROLE.U) {
                System.out.println("***** 유저 화면입니다 *****");
                
            }




    }
}
