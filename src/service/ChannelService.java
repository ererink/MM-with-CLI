package service;

import dto.BanDTO;
import dto.ChannelDTO;

import java.util.List;

public interface ChannelService {
    /**
     * 채널 목록 조회(유저)
     * @return 접근 가능한 채널 목록
     */
    List<ChannelDTO> visibleChannelSelect();

    /**
     * 반 선택 후 채널들 조회(관리자)
     * @return 선택된 방 안의 채널(접근 가능한 것만)
     */
    List<ChannelDTO> channelSelectByBan();

    /**
     * 채널 추가
     * @param channelDTO
     * @return 성공시 1, 실패시 0
     */
    int addChannel(ChannelDTO channelDTO);

    /**
     * 채널 삭제(관리자)
     * @param channel_id
     * @return 성공시 1, 실패시 0
     */
    int deleteChannel(long channel_id);

    /**
     * 채널 업데이트(관리자)
     * @param channelDTO
     * @return
     */
    int updateChannel(ChannelDTO channelDTO);

}
