package service;

import dto.ChannelDTO;
import dto.ChatDTO;

import java.util.List;

public interface ChatService {
    /**
     * 선택한 방의 채팅 조회
     * @param channelDTO
     * @return 해당 방의 채팅 목록
     */
    List<ChatDTO> selectChatByChannel(ChannelDTO channelDTO);

    /**
     * 현재 로그인한 유저가 작성한 채팅 목록 조회
     * @return 본인 채팅 목록
     */
    List<ChatDTO> selectChatByCurrentUser();

    /**
     * 채팅 수정(현재 로그인 되어 있는 유저가 입력한 채팅만 가능)
     * @param chatDTO
     * @return 성공시 true, 실패시 false
     */
    boolean updateChat(ChatDTO chatDTO);

    /**
     * 채팅 삭제(현재 로그인 되어 있는 유저가 입력한 채팅만 가능)
     * @param chatDTO
     * @return 성공시 true, 실패시 false
     */
    boolean deleteChat(ChatDTO chatDTO);
}
