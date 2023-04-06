package view;
import java.util.Scanner;
import controller.ChatController;
import dto.ChatDTO;
import session.UserSession;

public class ChatView {
    private static UserSession userSession = UserSession.getInstance();

    static Scanner sc = new Scanner(System.in);

//    public static void main(String[] args) {
//        /**
//         * 테스트용 use_id 입력
//         */
//
//        userSession.setUser_id("adoo24");
//        menuChoice();
//
//    }


    /**
     * 메뉴 출력
     */
    public static void menuChoice(){
        System.out.println("  ______   __    __   ______   ________ \n" +
                " /      \\ /  |  /  | /      \\ /        |\n" +
                "/$$$$$$  |$$ |  $$ |/$$$$$$  |$$$$$$$$/ \n" +
                "$$ |  $$/ $$ |__$$ |$$ |__$$ |   $$ |   \n" +
                "$$ |      $$    $$ |$$    $$ |   $$ |   \n" +
                "$$ |   __ $$$$$$$$ |$$$$$$$$ |   $$ |   \n" +
                "$$ \\__/  |$$ |  $$ |$$ |  $$ |   $$ |   \n" +
                "$$    $$/ $$ |  $$ |$$ |  $$ |   $$ |   \n" +
                " $$$$$$/  $$/   $$/ $$/   $$/    $$/   ");
        System.out.println();
//        UserSession userSession = UserSession.getInstance();
//        userSession.setChannel_id(1);
//        userSession.setClass_id(1);
//        userSession.setUser_id("adoo24");
        
        while (true){
            System.out.println();
            System.out.print("========================================================");
            System.out.print(" CHANNEL [ "+userSession.getChannel_name()+" ] ");
            System.out.print("========================================================\n");
            System.out.print("∥  0. 뒤로 가기   ");
            System.out.print("1. 모든 채팅 조회하기   ");
            System.out.print("2. 채팅번호로 조회하기   ");
            System.out.print("3. 제목으로 채팅 조회하기   ");
            System.out.print("4. 채팅 등록하기   ");
            System.out.print("5. 채팅 수정하기   ");
            System.out.print("6. 채팅 삭제하기  ∥");
            //System.out.print("7. 종료   ∥");
            System.out.println("\n==================================================================================================================================");
            System.out.print("선택 ▶ ");

            try {
                int menu = Integer.parseInt(sc.nextLine());

                switch (menu){
                    case 0:
                        System.out.println();
                        return;
                    case 1:
                        ChatController.selectAllChat();
                        break;
                    case 2:
                        inputSelectOne();
                        break;
                    case 3:
                        inputSelectByTitle();
                        break;
                    case 4:
                        inputCreateChat();
                        break;
                    case 5:
                        inputUpdateChat();
                        break;
                    case 6:
                        inputDeleteChat();
                        break;
                        /**
                    case 7:
                        System.out.println();
                        System.out.println("  종료 --<-<-<@,,, ADIOS,,,");
                        return; */
                    default:
                        System.out.println("잘못된 입력입니다. 다시 입력해주세요!");
                }
            }catch (NumberFormatException e){
                System.out.println("숫자 입력만 가능해요!");
            }

        }
    }

    /**
     * 입력값을 받는 메뉴의 각 기능 호출
     */

    /**
     * 선택한 하나의 채팅 조회 및 출력
     * @input int chat_id
     */
    public static void inputSelectOne(){
        try {
            // 채팅 목록 출력
            ChatController.selectAllChat();
            System.out.println();
            System.out.println("==================== 찾으실 채팅 번호를 입력해주세요 ====================");
            System.out.println("※※※※※※※※※※※※※※ 찾으실 채팅이 없다면 0을 입력해 주세요 ※※※※※※※※※※※※※※");

            // 입력값
            System.out.print("채팅 번호 ▶ ");
            int num = Integer.parseInt(sc.nextLine());
            switch (num){
                case 0:
                    return;
                default:
                    ChatController.selectOne(num);
            }
        }catch (NumberFormatException e){
            System.out.println("숫자만 입력이 가능해요!");
            inputSelectOne();
        }
    }

    /**
     * 키워드 검색 조회 및 결과 출력
     * @input String keyword
     */
    public static void inputSelectByTitle(){
        // 채팅 목록 출력
        ChatController.selectAllChat();
        System.out.println();
        System.out.println("======================= 찾으실 키워드를 입력해주세요 =======================");
        System.out.println("※※※※※※※※※※※※※※ 찾으실 키워드가 없다면 X를 입력해 주세요 ※※※※※※※※※※※※※※");

        // 입력값
        System.out.print("키워드 ▶ ");
        String word = sc.nextLine();
        switch (word){
            case "X":
                return;
            default:
                ChatController.selectByTitle(userSession.getChannel_id(), word);
        }
    }

    /**
     * 채팅 등록
     * @input String title, String content
     */
    public static void inputCreateChat(){
        System.out.println();
        System.out.println("====================== 채팅을 입력해주세요 ======================");
        System.out.println("※※※※※※※※※※ 추가하고 싶은 채팅이 없다면 X를 입력해 주세요 ※※※※※※※※※※");

        System.out.print("제목 ▶ ");
        String title = sc.nextLine();
        switch (title){
            case "X":
                return;
            default:
                System.out.print("내용 ▶ ");
                String content = sc.nextLine();

                ChatDTO chat = new ChatDTO(0, userSession.getUser_id(), userSession.getChannel_id(), title, content);
                ChatController.createChat(chat);
        }

    }

    /**
     * 채팅 수정
     * @input int chat_id, String content
     */
    public static void inputUpdateChat(){
        // 채팅 목록 출력
        ChatController.selectAllChat();
        System.out.println();
        System.out.println("====================== 수정하고 싶은 채팅번호를 입력해주세요 ======================");
        System.out.println("※※※※※※※※※※※※※※ 수정하고 싶은 채팅이 없다면 0을 입력해 주세요 ※※※※※※※※※※※※※※");

        System.out.print("채팅 번호 ▶ ");
        // 입력값
        int num = Integer.parseInt(sc.nextLine());
        switch (num){
            case 0:
                return;
            default:
                System.out.print("내용 ▶ ");
                String content = sc.nextLine();

                ChatDTO chat = new ChatDTO(num, userSession.getUser_id(), userSession.getChannel_id(), "title", content);
                ChatController.updateChat(chat);
        }

    }

    /**
     * 채팅 삭제
     * @input int chat_id
     */
    public static void inputDeleteChat(){
        // 채팅 목록 출력
        ChatController.selectAllChat();
        System.out.println();
        System.out.println("========================== 삭제하고 싶은 채팅 번호를 입력해주세요 ==========================");
        System.out.println("※※※※※※※※※※※※※※ 삭제하고 싶은 채팅이 없다면 0을 입력해 주세요 ※※※※※※※※※※※※※※");

        System.out.print("채팅 번호 ▶ ");
        // 입력값
        int num = Integer.parseInt(sc.nextLine());
        switch (num){
            case 0:
                return;
            default:
                ChatDTO chat = new ChatDTO(num, userSession.getUser_id(), userSession.getChannel_id(), "title", "content");
                ChatController.deleteChat(chat);
        }
    }
}
