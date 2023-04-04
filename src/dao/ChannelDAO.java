package dao;

import dto.ChannelDTO;

import java.util.List;

public interface ChannelDAO {
    int insertChannel(ChannelDTO channelDTO);
    List<ChannelDTO> selectAllChannel();
    int deleteChannel(ChannelDTO channelDTO);

    int updateChannel(ChannelDTO channelDto);
}
