package service;

import dao.ChannelDAO;
import dao.ChannelDAOImpl;
import dto.BanDTO;
import dto.ChannelDTO;
import dto.ROLE;
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
        return channelDAO.selectVisibleChannel(userSession.getUser_id());
    }

    /**
     * 안씀
     * @return
     * @throws RuntimeException
     */
    @Override
    public List<ChannelDTO> channelSelectByBan() throws RuntimeException {
        return channelDAO.selectAllChannel(userSession.getChannel_id());
    }

    @Override
    public int addChannel(ChannelDTO channelDTO) throws RuntimeException{
        if (channelDTO.getIsOpen() == 1 && userSession.getRole() != ROLE.A) {
            throw new RuntimeException("관리자가 아니면 공개채널 삽입 불가능합니다.");
        }
        channelDTO.setClass_id(userSession.getClass_id());
        return channelDAO.insertChannel(channelDTO);
    }

    @Override
    public int deleteChannel(long channel_id) throws RuntimeException {
        return channelDAO.deleteChannel(channel_id);
    }

    @Override
    public int updateChannel (ChannelDTO channelDTO) throws RuntimeException {
        return channelDAO.updateChannel(channelDTO);
    }

    @Override
    public ChannelDTO selectOneChannel(long channel_id) throws RuntimeException{
        return channelDAO.selectOneChannel(channel_id);
    }


}
