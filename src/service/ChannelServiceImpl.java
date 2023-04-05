package service;

import dao.ChannelDAO;
import dao.ChannelDAOImpl;
import dto.BanDTO;
import dto.ChannelDTO;
import session.UserSession;

import java.util.ArrayList;
import java.util.List;

public class ChannelServiceImpl implements ChannelService{
    private ChannelServiceImpl() {

    }
    private static ChannelService instance = new ChannelServiceImpl();

    public static ChannelService getInstance() {
        return instance;
    }
    private ChannelDAO channelDAO = ChannelDAOImpl.getInstance();
    UserSession userSession = UserSession.getInstance();

    @Override
    public List<ChannelDTO> visibleChannelSelect() throws RuntimeException{
        return channelDAO.selectVisibleChannel(userSession.getUser_id(), userSession.getClass_id());
    }

    @Override
    public List<ChannelDTO> channelSelectByBan() throws RuntimeException {
        return channelDAO.selectAllChannel(userSession.getChannel_id());
    }

    @Override
    public int addChannel(ChannelDTO channelDTO) throws RuntimeException{
        System.out.println("addChannel called");
        channelDTO.setClass_id(userSession.getClass_id());
        return channelDAO.insertChannel(channelDTO);
    }

    @Override
    public int deleteChannel(long channel_id) {
        return channelDAO.deleteChannel(channel_id);
    }

    @Override
    public int updateChannel(ChannelDTO channelDTO) {
        return channelDAO.updateChannel(channelDTO);
    }


}
