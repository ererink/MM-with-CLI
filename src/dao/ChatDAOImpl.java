package dao;

import common.DBManager;
import dto.ChatDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatDAOImpl implements ChatDAO{
    private static ChatDAO instance = new ChatDAOImpl();
    private ChatDAOImpl(){}
    public static ChatDAO getInstance(){
        return instance;
    }
    @Override
    public List<ChatDTO> selectAll(String user_id, long channel_id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ChatDTO> list = new ArrayList<>();
        String sql = "select * from chat where user_id = ? and channel_id  = ? order by created_at";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setString(1, user_id);
            ps.setLong(2, channel_id);

            while (rs.next()){
                ChatDTO dto = new ChatDTO(
                        rs.getLong("chat_id"),
                        rs.getString("user_id"),
                        rs.getLong("channel_id"),
                        rs.getString("title"),
                        rs.getString("content"));
                list.add(dto);
            }

        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            DBManager.releaseConnection(con, ps, rs);
        }
        return list;
    }

    @Override
    public Optional<ChatDTO> selectOne(long channel_id, String keyWord) {

        return Optional.empty();
    }
    @Override
    public List<ChatDTO> selectByContent(long channel_id, String keyWord) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ChatDTO> list = new ArrayList<>();

        /**
         * 하나의 채널에서 하나의 채팅을 찾는다면 해당 channel_id를 조회하고 해당 키워드를 찾는 시나리오로 구성
         * ex) 하나의 카톡방에 검색어를 입력하여 찾고자하는 채팅을 찾는다.
         */
        String sql = "select * from chat where channel_id  = ? and content like ?";

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            String title = '%' + keyWord + '%';
            ps.setLong(1, channel_id);
            ps.setString(2, keyWord);
            rs = ps.executeQuery();

            ChatDTO dto = new ChatDTO(
                    rs.getLong("chat_id"),
                    rs.getString("user_id"),
                    rs.getLong("channel_id"),
                    rs.getString("title"),
                    rs.getString("content"));
            list.add(dto);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(con, ps, rs);
        }
        return list;
    }
    @Override
    public int create(ChatDTO chatDTO) {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "insert into chat (chat_id, user_id, channel_id, title, content) values (chat_id_seq.nextval, ?, ?, ?, ?)";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, chatDTO.getChat_id());
            ps.setString(2, chatDTO.getUser_id());
            ps.setLong(3, chatDTO.getChannel_id());
            ps.setString(4, chatDTO.getTitle());
            ps.setString(5, chatDTO.getContent());

            result = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(con, ps);
        }
        return result;
    }

    @Override
    public int update(ChatDTO chatDTO) {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "update chat set content = ? where channel_id = ? and chat_id = ?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, chatDTO.getContent());
            ps.setLong(2, chatDTO.getChannel_id());
            ps.setLong(3, chatDTO.getChat_id());

            result = ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(con, ps);
        }
        return result;
    }

    @Override
    public int delete(long channel_id, long chat_id) {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "delete from where channel_id = ? and chat_id = ?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, channel_id);
            ps.setLong(2, chat_id);
            result = ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(con, ps);
        }
        return result;
    }
}
