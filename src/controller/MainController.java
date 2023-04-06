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
        System.out.println(" __       __  __       __                      __    __      __               ______   __       ______ \n" +
                "/  \\     /  |/  \\     /  |                    /  |  /  |    /  |             /      \\ /  |     /      |\n" +
                "$$  \\   /$$ |$$  \\   /$$ |       __   __   __ $$/  _$$ |_   $$ |____        /$$$$$$  |$$ |     $$$$$$/ \n" +
                "$$$  \\ /$$$ |$$$  \\ /$$$ |      /  | /  | /  |/  |/ $$   |  $$      \\       $$ |  $$/ $$ |       $$ |  \n" +
                "$$$$  /$$$$ |$$$$  /$$$$ |      $$ | $$ | $$ |$$ |$$$$$$/   $$$$$$$  |      $$ |      $$ |       $$ |  \n" +
                "$$ $$ $$/$$ |$$ $$ $$/$$ |      $$ | $$ | $$ |$$ |  $$ | __ $$ |  $$ |      $$ |   __ $$ |       $$ |  \n" +
                "$$ |$$$/ $$ |$$ |$$$/ $$ |      $$ \\_$$ \\_$$ |$$ |  $$ |/  |$$ |  $$ |      $$ \\__/  |$$ |_____ _$$ |_ \n" +
                "$$ | $/  $$ |$$ | $/  $$ |      $$   $$   $$/ $$ |  $$  $$/ $$ |  $$ |      $$    $$/ $$       / $$   |\n" +
                "$$/      $$/ $$/      $$/        $$$$$/$$$$/  $$/    $$$$/  $$/   $$/        $$$$$$/  $$$$$$$$/$$$$$$/ \n" +
                "                                                                                                     ");

        System.out.println(" √ 종료하려면 0을 입력해주세요 √");
        System.out.println();
        while (true) {
            MainView.start();

                if (userSession.getRole() == ROLE.A) {
                    MainView.adminView();
                } else if (userSession.getRole() == ROLE.U) {
                    MainView.userView();
                }else if (userSession.getRole() == ROLE.N){
                    System.out.println("승인되지 않은 계정입니다. 승인을 기다려주세요..");
                }
            }

        }






}
