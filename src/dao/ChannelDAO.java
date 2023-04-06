package dao;

import dto.ChannelDTO;

import java.util.List;

public interface ChannelDAO {
    /**
     * 채널 삽입
     * @param channelDTO
     * @return 성공시 1, 실패시 0
     */
    int insertChannel(ChannelDTO channelDTO);

    /**
     * 채널 아이디로 채널 하나 선택
     * @param channel_id
     * @return 채널DTO
     */
    ChannelDTO selectOneChannel(long channel_id);

    /**
     * 모든 채널 선택(개인 채널은 관리자도 열람하지 못하기 때문에 사용하지 않음)
     * @param class_id
     * @return 반에 있는 모든 채널
     */
    List<ChannelDTO> selectAllChannel(long class_id);

    /**
     * 현재 유저가 열람 가능한 모든 채널 목록
     * @param user_id
     * @return 열람 가능한 채널
     */
    List<ChannelDTO> selectVisibleChannel(String user_id);

    /**
     * 채널 삭제
     * @param channel_id
     * @return 성공시 1, 실패시 0
     */
    int deleteChannel(long channel_id);

    /**
     * 채널 업데이트(채널 이름)
     * @param channelDto
     * @return 성공시 1, 실패시 0
     */
    int updateChannel(ChannelDTO channelDto);

}
