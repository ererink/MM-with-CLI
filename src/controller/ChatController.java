package controller;

import dto.ChatDTO;
import service.ChatService;
import service.ChatServiceImpl;
import view.FailView;
import view.ChatSuccessView;

import java.util.List;
import java.util.Optional;

public class ChatController {
    private static ChatService chatService = ChatServiceImpl.getInstance();

    /**
     * 입장한 채널의 모든 채팅목록 출력
     */
    public static void selectAllChat(){
        try {
            List<ChatDTO> chatList = chatService.selectAllChat();
            /**
             * SuccessView 작성 후 주석제거 예정
             */
            ChatSuccessView.selectPrint(chatList);

        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    }

    /**
     * chat_id에 해당하는 채팅 검색
     */
    public static void selectOne(long chat_id) {
        try {
            ChatDTO dto = chatService.selectOne(chat_id).get();

            ChatSuccessView.selectByNoPrint(dto);

        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    }

    /**
     * 키워드 형식으로 채팅의 제목 검색
     */
    public static void selectByTitle(long channel_id, String keyWord){
        try {
            List<ChatDTO> chatList = chatService.selectByTitle(channel_id, keyWord);

            ChatSuccessView.selectPrint(chatList);

        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    }

    /**
     * 채팅 작성 및 등록
     */
    public static void createChat(ChatDTO chat){
        try {
            chatService.createChat(chat);

            System.out.println("\n-----------------");
            ChatSuccessView.messagePrint(" * 채팅 등록 완료 *");
            System.out.println("-----------------");

        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    }

    /**
     * 채팅 수정
     */
    public static void updateChat(ChatDTO chat){
        try {
            chatService.updateChat(chat);

            System.out.println("\n-----------------");
            ChatSuccessView.messagePrint(" * 채팅 수정 완료 *");
            System.out.println("-----------------");
        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    }

    /**
     * 채팅 삭제
     */
    public static void deleteChat(ChatDTO chat){
        try {
            chatService.deleteChat(chat);

            System.out.println("\n-----------------");
            ChatSuccessView.messagePrint(" * 채팅 삭제 완료 *");
            System.out.println("-----------------");

        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    }
}
