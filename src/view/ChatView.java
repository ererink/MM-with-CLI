package view;
import java.util.Scanner;
import controller.ChatController;
import dto.ChatDTO;
import session.UserSession;

public class ChatView {
    static Scanner sc = new Scanner(System.in);
    private UserSession userSession;

    public static void main(String[] args) {
        menuChoice();
        
    }
    

    /**
     * 메뉴 출력
     */
    public static void menuChoice(){
        UserSession userSession = UserSession.getInstance();
        userSession.setChannel_id(1);
        userSession.setClass_id(1);
        userSession.setUser_id("adoo24");
        
        while (true){
            System.out.println("\n===============("+userSession.getChannel_id()+")===============");
            System.out.print("[ 1. 모든 채팅 조회하기   ");
            System.out.print("2. 채팅번호로 조회하기   ");
            System.out.print("3. 제목으로 채팅 조회하기   ");
            System.out.print("4. 채팅 등록하기   ");
            System.out.print("5. 채팅 수정하기   ");
            System.out.print("6. 채팅 삭제하기   ");
            System.out.print("7. 종료 ]");
            System.out.println("\n=============================================");
            System.out.println("선택 : ");

            try {
                int menu = Integer.parseInt(sc.nextLine());

                switch (menu){
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

                }
            }catch (NumberFormatException e){
                System.out.println("숫자 입력만 가능해요!");
            }

        }
    }

    /**
     * 메뉴의 기능 호출
     */
    public static void inputSelectOne(){
        try {
            System.out.println("찾으실 채팅 번호를 입력해주세요");
            String num = sc.nextLine();
            ChatController.selectOne(Integer.parseInt(num));
        }catch (NumberFormatException e){
            System.out.println("숫자만 입력이 가능해요!");
            inputSelectOne();
        }
    }

    public static void inputSelectByTitle(){
        UserSession userSession = UserSession.getInstance();
        userSession.setChannel_id(1);
        userSession.setClass_id(1);
        userSession.setUser_id("adoo24");

        System.out.println("찾으실 키워드를 입력해주세요");
        String word = sc.nextLine();
        ChatController.selectByTitle(userSession.getChannel_id(), word);
    }

    public static void inputCreateChat(){
        UserSession userSession = UserSession.getInstance();
        userSession.setChannel_id(1);
        userSession.setClass_id(1);
        userSession.setUser_id("adoo24");

        System.out.println("======== 제목과 내용을 입력해주세요 ========");
        System.out.println("제목 : ");
        String title = sc.nextLine();

        System.out.println("내용 : ");
        String content = sc.nextLine();

        ChatDTO chat = new ChatDTO(0, userSession.getUser_id(), userSession.getChannel_id(), title, content);
        ChatController.createChat(chat);
    }

    public static void inputUpdateChat(){
        UserSession userSession = UserSession.getInstance();
        userSession.setChannel_id(1);
        userSession.setClass_id(1);
        userSession.setUser_id("adoo24");

        System.out.println("======== 수정할 채팅 번호와 내용을 입력해주세요 ========");
        System.out.println("채팅 번호 : ");
        int num = Integer.parseInt(sc.nextLine());

        System.out.println("내용 : ");
        String content = sc.nextLine();

        ChatDTO chat = new ChatDTO(num, userSession.getUser_id(), userSession.getChannel_id(), "title", content);
        ChatController.updateChat(chat);
    }

    public static void inputDeleteChat(){
        UserSession userSession = UserSession.getInstance();
        userSession.setChannel_id(1);
        userSession.setClass_id(1);
        userSession.setUser_id("adoo24");

        System.out.println("===== 삭제할 내용을 채팅 번호를 입력해주세요 =====");
        System.out.println("채팅 번호 : ");
        int num = Integer.parseInt(sc.nextLine());

        ChatDTO chat = new ChatDTO(num, userSession.getUser_id(), userSession.getChannel_id(), "title", "content");
        ChatController.deleteChat(chat);

    }
}
