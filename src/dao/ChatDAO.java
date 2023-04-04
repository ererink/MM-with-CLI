package dao;

import dto.ChatDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ChatDAO {
    /**
     * 하나의 채널에서 모든 채팅 목록 출력
     */
    List<ChatDTO> selectAll(String user_id, long channel_id);

    /**
     * 조회한 하나의 채팅 출력
     */
    Optional<ChatDTO> selectOne(long channel_id, String keyWord);

    /**
     * 채팅 내용 검색 조회 목록 출력
     */
    List<ChatDTO> selectByContent(long channel_id, String keyWord);

    /**
     * 채팅 생성
     */
    int create(ChatDTO dto);

    /**
     * 채팅 수정
     */
    int update(ChatDTO dto);
    /**
     * 채팅 삭제
     */
    int delete(long channel_id, long chat_id);



}
