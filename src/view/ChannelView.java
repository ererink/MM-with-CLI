package view;

import controller.ChannelController;
import dao.UserChannelDAO;
import dto.ChannelDTO;
import dto.ROLE;
import exception.channel.NoAuthException;
import service.BanService;
import service.BanServiceImpl;
import service.ChannelService;
import service.ChannelServiceImpl;
import session.UserSession;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ChannelView {
    static Scanner sc = new Scanner(System.in);
    private static ChannelService channelService = ChannelServiceImpl.getInstance();
    private static BanService banService = BanServiceImpl.getInstance();
    static UserSession userSession = UserSession.getInstance();

    public static void channelChoice() {
        userSession.setClass_name(banService.selectOneBan(userSession.getClass_id()).getClass_name());
        System.out.println("  ______   __                                                __ \n" +
                " /      \\ /  |                                              /  |\n" +
                "/$$$$$$  |$$ |____    ______   _______   _______    ______  $$ |\n" +
                "$$ |  $$/ $$      \\  /      \\ /       \\ /       \\  /      \\ $$ |\n" +
                "$$ |      $$$$$$$  | $$$$$$  |$$$$$$$  |$$$$$$$  |/$$$$$$  |$$ |\n" +
                "$$ |   __ $$ |  $$ | /    $$ |$$ |  $$ |$$ |  $$ |$$    $$ |$$ |\n" +
                "$$ \\__/  |$$ |  $$ |/$$$$$$$ |$$ |  $$ |$$ |  $$ |$$$$$$$$/ $$ |\n" +
                "$$    $$/ $$ |  $$ |$$    $$ |$$ |  $$ |$$ |  $$ |$$       |$$ |\n" +
                " $$$$$$/  $$/   $$/  $$$$$$$/ $$/   $$/ $$/   $$/  $$$$$$$/ $$/ ");
        while (true) {
            System.out.println();
            System.out.print("===================================");
            System.out.print(" CHANNEL [ "+userSession.getClass_name()+" ] ");
            System.out.print("===================================\n");
            System.out.print("∥  0. 뒤로 가기   ");
            System.out.print("1. 채널 입장하기   ");
            System.out.print("2. 채널 추가하기   ");
            System.out.print("3. 채널 업데이트   ");
            System.out.print("4. 채널 삭제하기  ∥");
            System.out.println("\n========================================================================================");
            System.out.print("선택 ▶ ");

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
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요!");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자 입력만 가능해요!");
            }
        }
    }

    public static void enterChannel() {
        List<ChannelDTO> list =ChannelController.channelSelect();
        Set<Long> channel_id_set = new HashSet<>();
        for (ChannelDTO dto : list) {
            channel_id_set.add(dto.getChannel_id());
        }
        System.out.println();
        System.out.println("====================== 입장할 채널 번호를 입력해주세요 ======================");
        
        // 입력값
        System.out.print("채널 번호 ▶ ");
        int channel_id = Integer.parseInt(sc.nextLine());
        try {
            if (!channel_id_set.contains(channel_id)) {
                throw new NoAuthException("접근할 수 없는 채널입니다!");
            }
            userSession.setChannel_id(channel_id);
            userSession.setChannel_name(channelService.selectOneChannel(channel_id).getChannel_name());
            ChatView.menuChoice();
        } catch (NoAuthException e) {
            System.out.println(e.getMessage());
        }
    }



    public static void inputInsertChannel() {
        System.out.println();
        System.out.println("==================== √ 공개채널로 만드시려면 1, 아니라면 0을 입력해주세요 √ ====================");
        System.out.println("※※※※※※※※※※※※※※ 추가하고 싶은 채널이 없다면 X를 입력해 주세요 ※※※※※※※※※※※※※※");

        System.out.print("입력 ▶ ");
        int isOpen = Integer.parseInt(sc.nextLine());
        System.out.print("채널 제목 ▶ ");
        String channelName = sc.nextLine();
        if (isOpen == 0) {
            //반에 있는 유저들 리스트 출력
            Set<String> set = new HashSet<>();
            set.add(userSession.getUser_id());
            while (true) {
                System.out.print("아이디 ▶ ");

                String temp = sc.nextLine();
                if (temp.equals("X")) {
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
        List<ChannelDTO> list =ChannelController.channelSelect();
        Set<Long> channel_id_set = new HashSet<>();
        for (ChannelDTO dto : list) {
            channel_id_set.add(dto.getChannel_id());
        }
        System.out.println();
        System.out.println("========================== 수정할 채널번호를 입력해주세요 ==========================");
        System.out.println("※※※※※※※※※※※※※※ 수정하고 싶은 채팅이 없다면 0을 입력해 주세요 ※※※※※※※※※※※※※※");
        System.out.print("채널 번호 ▶ ");
        long channel_id = Long.parseLong(sc.nextLine());
        try {
            if (!channel_id_set.contains(channel_id)) {
                throw new NoAuthException("접근할 수 없는 채널입니다!");
            }
            switch ((int) channel_id){
                case 0:
                    return;
                default:
                    System.out.print("채널 제목 ▶ ");
                    String channelName = sc.nextLine();
                    ChannelController.channelUpdate(new ChannelDTO(channel_id,channelName,0,0));
            }
        } catch (NoAuthException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteChannel() {
        List<ChannelDTO> list =ChannelController.channelSelect();
        Set<Long> channel_id_set = new HashSet<>();
        for (ChannelDTO dto : list) {
            channel_id_set.add(dto.getChannel_id());
        }
        System.out.println();
        System.out.println("========================== 삭제할 채널 번호를 입력해주세요 ==========================");
        System.out.println("※※※※※※※※※※※※※※ 삭제하고 싶은 채팅이 없다면 0을 입력해 주세요 ※※※※※※※※※※※※※※");

        System.out.print("채널 번호 ▶ ");

        int channel_id = Integer.parseInt(sc.nextLine());
        try {
            if (!channel_id_set.contains(channel_id)) {
                throw new NoAuthException("접근할 수 없는 채널입니다!");
            }
            switch (channel_id){
                case 0:
                    return;
                default:
                    ChannelController.channelDelete(channel_id);
            }
        } catch (NoAuthException e) {
            System.out.println(e.getMessage());
        }
    }
}
