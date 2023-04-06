package view;

import controller.BanController;
import controller.MainController;
import dto.BanDTO;
import dto.ROLE;
import session.UserSession;

import java.util.Scanner;

public class BanView {

    static Scanner sc = new Scanner(System.in);
    static UserSession userSession = UserSession.getInstance();

//    public static void main(String[] args) {
//        //Test
//        userSession.setRole(ROLE.A);
//        banChoice();
//
//    }

    /**
     * 반(CLASS) 출력
     */
    public static void banChoice(){
        System.out.println("  ______   __         ______    ______   ______  \n" +
                " /      \\ /  |       /      \\  /      \\ /      \\ \n" +
                "/$$$$$$  |$$ |      /$$$$$$  |/$$$$$$  /$$$$$$  |\n" +
                "$$ |  $$/ $$ |      $$ |__$$ |$$ \\__$$/$$ \\__$$/ \n" +
                "$$ |      $$ |      $$    $$ |$$      \\$$      \\ \n" +
                "$$ |   __ $$ |      $$$$$$$$ | $$$$$$  |$$$$$$  |\n" +
                "$$ \\__/  |$$ |_____ $$ |  $$ |/  \\__$$ /  \\__$$ |\n" +
                "$$    $$/ $$       |$$ |  $$ |$$    $$/$$    $$/ \n" +
                " $$$$$$/  $$$$$$$$/ $$/   $$/  $$$$$$/  $$$$$$/");

            while(true){
                if(userSession.getRole() == ROLE.A){
                    System.out.println();
                    System.out.print("====================================");
                    System.out.print("∥ 0 .뒤로 가기   ");
                    System.out.print("1. 반 입장하기   ");
                    System.out.print("2. 반 관리하기   ∥");
                    System.out.println("===================================\n");
                    System.out.print("선택 ▶ ");
            }
            try{
                int menu = Integer.parseInt(sc.nextLine());
                switch (menu){
                    case 0:
                        if (userSession.getRole() == ROLE.A){
                            MainView.adminView();
                            return;
                        }
                    case 1:
                        selectAllBan();
                        break;
                    case 2:
                        manageBan();
                        break;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요!");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자 입력만 가능해요!");
            }
        }
    }

    //메뉴의 각 기능 호출
    /**
     * 1. 반 입장하기
     */
    public static void selectAllBan(){
        BanController.selectAllBan();
        System.out.println();
        System.out.println("====================== 입장할 반 번호를 입력해주세요. ======================");
        long class_id = Long.parseLong(sc.nextLine());
        userSession.setClass_id(class_id);
        ChannelView.channelChoice();
    }

    /**
     * 2. 반 관리하기
     * 반 추가/수정/삭제
     */
    public static void manageBan(){
        while(true){
            BanController.selectAllBan();
            System.out.println();
            System.out.print("============================");
            System.out.print("∥ 0.뒤로 가기    ");
            System.out.print("1. 반 추가하기   ");
            System.out.print("2. 반 수정하기   ");
            System.out.print("3. 반 삭제하기   ∥");
            System.out.println("============================\n");
            System.out.print("선택 ▶ ");

            try{
                int menu = Integer.parseInt(sc.nextLine());
                switch (menu){
                    case 0:
                        return;
                    case 1:
                        insertClass();
                        break;
                    case 2:
                        updateClass();
                        break;
                    case 3:
                        deleteClass();
                        break;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요!");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자 입력만 가능해요!");
            }
        }

    }

    /**
     * 2-1. 반 추가
     */
    public static void insertClass(){
        System.out.println("====================== 추가하고 싶은 반 이름을 입력하세요 ======================");
        System.out.println("※※※※※※※※※※※※※※ 추가하고 싶은 반이 없다면 X를 입력해 주세요 ※※※※※※※※※※※※※※");
        System.out.print("반 이름 ▶ ");

        try{
            String class_Name = sc.nextLine();
            switch (class_Name){
                case "X":
                    return;
                default:
                    BanController.insertBan(new BanDTO(0,class_Name));
            }
        } catch (NumberFormatException e) {
            System.out.println("문자 입력만 가능해요!");
        }
    }

    /**
     * 2-2. 반 수정
     */
    public static void updateClass(){
        BanController.selectAllBan();
        System.out.println("====================== 수정하고 싶은 반 번호를 입력해주세요 ======================");
        System.out.println("※※※※※※※※※※※※※※ 수정하고 싶은 반이 없다면 0을 입력해 주세요 ※※※※※※※※※※※※※※");
        System.out.print("반 번호 ▶ ");

        try{
            long class_id = Long.parseLong(sc.nextLine());
            switch ((int) class_id){
                case 0:
                    return;
                default:
                    System.out.println("====================== 수정하고 싶은 반 이름을 입력해주세요 ======================");
                    System.out.print("반 이름 ▶ ");
                    String class_Name = sc.nextLine();
                    BanController.updateBan(new BanDTO(class_id, class_Name));
            }
        } catch (NumberFormatException e) {
            System.out.println("숫자 입력만 가능해요!");
        }

    }

    /**
     * 2-3. 반 삭제
     */
    public static void deleteClass(){
        BanController.selectAllBan();
        System.out.println("====================== 삭제하고 싶은 반 번호를 입력해주세요 ======================");
        System.out.println("※※※※※※※※※※※※※※ 삭제하고 싶은 반 번호가 없다면 0을 입력해 주세요 ※※※※※※※※※※※※※※");

        System.out.print("반 번호 ▶ ");

        try{
            int class_id = Integer.parseInt(sc.nextLine());
            switch (class_id){
                case 0:
                    return;
                default:
                    BanController.deleteBan(class_id);
            }
        } catch (NumberFormatException e) {
            System.out.println("숫자 입력만 가능해요!");
        }
    }

}
