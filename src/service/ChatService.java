package service;

import dto.ChannelDTO;
import dto.ChatDTO;

import java.util.List;
import java.util.Optional;

public interface ChatService {
    /**
     * 선택한 방의 채팅 조회
     * @return 해당 방의 채팅 목록
     */
    List<ChatDTO> selectChatByChannel();

    /**
     * chat_id에 해당하는 채팅 검색
     * @param chat_id
     * @return 해당 채팅 정보
     */
    Optional<ChatDTO> selectOne(long chat_id);

    /**
     * 채팅의 제목 검색
     * @param channel_id, keyWord
     * @return 키워드에 해당하는 채팅 정보
     */
    List<ChatDTO> selectByContent(long channel_id, String keyWord);

    /**
     * 채팅 생성
     * @param chatDTO
     * @return 성공시 true, 실패시 false
     */
    boolean createChat(ChatDTO chatDTO);

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
