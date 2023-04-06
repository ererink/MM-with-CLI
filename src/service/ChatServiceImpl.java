package service;

import dao.ChatDAO;
import dao.ChatDAOImpl;
import dto.ChannelDTO;
import dto.ChatDTO;
import session.UserSession;

import java.util.List;
import java.util.Optional;

public class ChatServiceImpl implements ChatService{
    private static ChatService instance = new ChatServiceImpl();
    private ChatDAO chatDAO = ChatDAOImpl.getInstance();
    private UserSession userSession = UserSession.getInstance();
    public static ChatService getInstance() {
        return instance;
    }
    @Override
    public List<ChatDTO> selectAllChat() throws RuntimeException{
        List<ChatDTO> chatList = chatDAO.selectAll( userSession.getChannel_id());
        if (chatList.size() == 0){
          throw new RuntimeException("채팅이 없습니다!");
        }
        return chatList;
    }

    @Override
    public Optional<ChatDTO> selectOne(long chat_id) throws RuntimeException {
        Optional<ChatDTO> chatDTO = chatDAO.selectOne(chat_id);
        if (chatDTO.isEmpty()){
            throw new RuntimeException("검색한 채팅 정보가 존재하지 않습니다!");
        }
        return chatDTO;
    }

    @Override
    public List<ChatDTO> selectByTitle(long channel_id, String keyWord) throws RuntimeException{
        List<ChatDTO> chatDTO = chatDAO.selectByTitle(userSession.getChannel_id(), keyWord);
        if (chatDTO.size() == 0){
            throw new RuntimeException("해당 키워드에 대한 채팅이 없습니다!");
        }
        return chatDTO;
    }

    @Override
    public boolean createChat(ChatDTO chatDTO) throws RuntimeException{
        chatDTO.setChannel_id(userSession.getChannel_id());
        chatDTO.setUser_id(userSession.getUser_id());

        int result = chatDAO.create(chatDTO);
        if (result == 0){
            throw new RuntimeException("채팅이 등록되지 않았습니다!");
        }
        return false;
    }

    @Override
    public boolean updateChat(ChatDTO chatDTO) throws RuntimeException{
        chatDTO.setChannel_id(userSession.getChannel_id());
        chatDTO.setUser_id(userSession.getUser_id());

        int result = chatDAO.update(chatDTO);
        if (result == 0){
            throw new RuntimeException("채팅이 수정되지 않았습니다!");
        }
        if (userSession.getUser_id() != chatDTO.getUser_id()){
            throw new RuntimeException("본인의 채팅만 수정할 수 있습니다!");
        }
        return false;
    }

    @Override
    public boolean deleteChat(ChatDTO chatDTO) {
        chatDTO.setChannel_id(userSession.getChannel_id());
        chatDTO.setUser_id(userSession.getUser_id());

        int result = chatDAO.delete(chatDTO.getChannel_id(), chatDTO.getChat_id());
        if (result == 0){
            throw new RuntimeException("채팅이 삭제되지 않았습니다!");
        }
        if (userSession.getUser_id() != chatDTO.getUser_id()){
            throw new RuntimeException("본인의 채팅만 삭제할 수 있습니다!");
        }
        return false;
    }
}
