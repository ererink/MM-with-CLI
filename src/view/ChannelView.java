package view;

import controller.ChannelController;
import dto.ChannelDTO;
import dto.ROLE;
import session.UserSession;

import java.util.Scanner;

public class ChannelView {
    static Scanner sc = new Scanner(System.in);
    static UserSession userSession = UserSession.getInstance();
    public static void channelChoice() {

        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.print("[ 뒤로 가기    ");
            System.out.print("1. 채널 입장   ");
            if (userSession.getRole() == ROLE.ADMIN) {
                System.out.print("2. 채널 추가   ");
                System.out.print("3. 채널 업데이트   ");
                System.out.print("4. 채널 삭제   ");
            }
            System.out.println("\n--------------------------------------------");
            System.out.print("기능을 선택하세요: ");
            try {
                int menu = Integer.parseInt(sc.nextLine());
                switch (menu) {
                    case 0:
                        userSession.setChannel_id(-1);
                        return;
                    case 1:
                        enterChannel();
                        break;
                    case 2:
                        if (userSession.getRole() != ROLE.ADMIN) {
                            System.out.println("잘못된 번호 입니다.");
                            break;
                        }
                        inputInsertChannel();
                        break;
                    case 3:
                        if (userSession.getRole() != ROLE.ADMIN) {
                            System.out.println("잘못된 번호 입니다.");
                            break;
                        }
                        inputUpdateChannel();
                        break;
                    case 4:
                        if (userSession.getRole() != ROLE.ADMIN) {
                            System.out.println("잘못된 번호 입니다.");
                            break;
                        }
                        deleteChannel();
                        break;
                    default:
                        System.out.println("잘못된 번호 입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요. ");
            }
        }
    }

    public static void enterChannel() {
        ChannelController.channelSelect();
        System.out.print("입장할 채널 번호를 입력해주세요: ");
        int channel_id = Integer.parseInt(sc.nextLine());
        userSession.setChannel_id(channel_id);
        //채팅 뷰 호출
    }
    public static ChannelDTO inputChannelInfo() {
        System.out.print("채널 제목을 입력하세요: ");
        String channelName = sc.nextLine();
        return new ChannelDTO(0, channelName, 0);
    }


    public static void inputInsertChannel() {
        ChannelDTO channelDTO = inputChannelInfo();
        ChannelController.channelInsert(channelDTO);
    }

    public static void inputUpdateChannel() {
        ChannelController.channelSelect();
        ChannelDTO channelDTO = inputChannelInfo();
        System.out.print("업데이트할 채널 번호를 입력해주세요: ");
        long channel_id = Long.parseLong(sc.nextLine());
        channelDTO.setChannel_id(channel_id);
        ChannelController.channelUpdate(channelDTO);
    }

    public static void deleteChannel() {
        ChannelController.channelSelect();
        System.out.print("삭제할 채널 번호를 입력해주세요: ");
        int channel_id = Integer.parseInt(sc.nextLine());
        ChannelController.channelDelete(channel_id);
    }
}
