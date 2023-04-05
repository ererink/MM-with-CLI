package view;

import controller.BanController;
import dto.BanDTO;
import dto.ROLE;
import session.UserSession;

import java.util.Scanner;

public class BanView {

    static Scanner sc = new Scanner(System.in);
    static UserSession userSession = UserSession.getInstance();

    public static void banChoice(){
//            1. 반 목록 확인하기
//            1-1. 반 추가 하기
//            1-2. 반 수정 하기
//            1-3. 반 삭제 하기

            while(true){
                if(userSession.getRole() == ROLE.A){
                    System.out.println("\n----------------------------------------");

                    System.out.print("[ 뒤로 가기    ");
                    System.out.print("1. 반 입장하기   ");
                    System.out.print("2. 반 관리하기  ");

                    System.out.println("\n--------------------------------------------");
                    System.out.print("기능을 선택하세요: ");
            }
            try{
                int menu = Integer.parseInt(sc.nextLine());
                switch (menu){
                    case 0:
                        userSession.setClass_id(-1);
                        return;
                    case 1:
                        selectAllBan();
                        break;
                    case 2:
                        manageBan();
                        break;
                    default:
                        System.out.println("잘못된 번호 입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요. ");
            }
        }
    }

    /**
     * 1. 반 입장하기
     */
    public static void selectAllBan(){
        BanController.selectAllBan();
        System.out.print("입장할 반 번호를 입력해주세요.");
        long class_id = Long.parseLong(sc.nextLine());
        userSession.setClass_id(class_id);
        ChannelView.channelChoice();
    }

    /**
     * 2. 반 관리하기
     * 반 추가/수정/삭제
     */
    public static void manageBan(){
        BanController.selectAllBan();
        while(true){
            System.out.print("1. 반 추가하기   ");
            System.out.print("2. 반 수정하기   ");
            System.out.print("3. 반 삭제하기   ");
            System.out.println("\n--------------------------------------------");
            System.out.print("기능을 선택하세요: ");

            try{
                int menu = Integer.parseInt(sc.nextLine());
                switch (menu){
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
                        System.out.println("잘못된 번호 입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요. ");
            }
        }

    }

    /**
     * 2-1. 반 추가
     */
    public static void insertClass(){
        System.out.print("추가하고 싶은 반 이름을 입력하세요: ");
        String class_Name = sc.nextLine();

        BanController.insertBan(new BanDTO(0,class_Name));
    }

    /**
     * 2-2. 반 수정
     */
    public static void updateClass(){
        BanController.selectAllBan();
        System.out.print("수정하고 싶은 반 번호를 입력해주세요.");
        long class_id = Long.parseLong(sc.nextLine());
        System.out.print("수정하고 싶은 반 이름을 정확하게 입력해주세요.");
        String class_Name = sc.nextLine();

        BanController.updateBan(new BanDTO(class_id, class_Name));
    }

    /**
     * 2-3. 반 삭제
     */
    public static void deleteClass(){
        BanController.selectAllBan();
        System.out.print("삭제하고 싶은 반 번호를 입력해주세요.");
        int class_id = Integer.parseInt(sc.nextLine());
        BanController.deleteBan(class_id);
    }

}
