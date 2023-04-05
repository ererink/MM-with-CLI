package dao;

import dto.ChannelDTO;

import java.util.List;

public interface ChannelDAO {
    int insertChannel(ChannelDTO channelDTO);
    List<ChannelDTO> selectAllChannel(long class_id);
    List<ChannelDTO> selectVisibleChannel(String user_id);
    int deleteChannel(long channel_id);
    int updateChannel(ChannelDTO channelDto);

}
