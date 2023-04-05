package view;

import controller.ChannelController;
import dao.UserChannelDAO;
import dto.ChannelDTO;
import dto.ROLE;
import session.UserSession;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ChannelView {
    static Scanner sc = new Scanner(System.in);
    static UserSession userSession = UserSession.getInstance();
    public static void channelChoice() {

        while (true) {
            System.out.println("\n-------------------------------------------------------------------------------");
            System.out.print("[ 0. 뒤로 가기    ");
            System.out.print("1. 채널 입장   ");
            System.out.print("2. 채널 추가   ");
            System.out.print("3. 채널 업데이트   ");
            System.out.print("4. 채널 삭제   ]");
            System.out.println("\n------------------------------------------------------------------------------");
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
                        inputInsertChannel();
                        break;
                    case 3:
                        inputUpdateChannel();
                        break;
                    case 4:
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
        ChatView.menuChoice();
    }



    public static void inputInsertChannel() {
        System.out.print("공개채널로 만드시려면 1, 아니라면 0을 입력해주세요: ");
        int isOpen = Integer.parseInt(sc.nextLine());
        System.out.print("채널 제목을 입력하세요: ");
        String channelName = sc.nextLine();
        if (isOpen == 0) {
            //반에 있는 유저들 리스트 출력
            Set<String> set = new HashSet<>();
            set.add(userSession.getUser_id());
            while (true) {
                System.out.print("추가할 유저를 입력하세요(그만하려면 q를 입력): ");
                String temp = sc.nextLine();
                if (temp.equals("q")) {
                    break;
                }
                set.add(temp);
            }
            for (String user_id : set) {
                UserChannelDAO.insertRelation(user_id);
            }
        }
        ChannelController.channelInsert(new ChannelDTO(0,channelName,0,isOpen));

    }

    public static void inputUpdateChannel() {
        ChannelController.channelSelect();
        System.out.print("업데이트할 채널 번호를 입력해주세요: ");
        long channel_id = Long.parseLong(sc.nextLine());
        System.out.print("채널 제목을 입력하세요: ");
        String channelName = sc.nextLine();
        ChannelController.channelUpdate(new ChannelDTO(channel_id,channelName,0,0));
    }

    public static void deleteChannel() {
        ChannelController.channelSelect();
        System.out.print("삭제할 채널 번호를 입력해주세요: ");
        int channel_id = Integer.parseInt(sc.nextLine());
        ChannelController.channelDelete(channel_id);
    }
}
