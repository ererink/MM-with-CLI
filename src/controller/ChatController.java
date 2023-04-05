package controller;

import dto.ChatDTO;
import service.ChatService;
import service.ChatServiceImpl;
import view.FailView;

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
            // SuccessView.selectPrint(chatList)

        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    }

    /**
     * chat_id에 해당하는 채팅 검색
     */
    public static void selectOne(long chat_id) {
        try {
            Optional<ChatDTO> dto = chatService.selectOne(chat_id);
            /**
             * SuccessView 작성 후 주석제거 예정
             */
            // SuccessView.selectPrint(dto);

        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    }

    /**
     * 키워드 형식으로 채팅의 제목 검색
     */
    public static void selectByContent(long channel_id, String keyWord){
        try {
            List<ChatDTO> chatList = chatService.selectByContent(channel_id, keyWord);
            /**
             * SuccessView 작성 후 주석제거 예정
             */
            // SuccessView.selectPrint(chatList);

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
            /**
             * SuccessView 작성 후 주석제거 예정
             */
            // SuccessView.selectPrint("채팅이 등록되었습니다.");

        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    }

    /**
     * 채팅 수정
     */
    public static void updateChat(ChatDTO chat){
        try {
            chatService.createChat(chat);
            /**
             * SuccessView 작성 후 주석제거 예정
             */
            // SuccessView.selectPrint("채팅이 수정되었습니다.");
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
            /**
             * SuccessView 작성 후 주석제거 예정
             */
            // SuccessView.selectPrint("채팅이 삭제되었습니다.");

        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    }
}
