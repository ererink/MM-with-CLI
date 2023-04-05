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
            ps.setString(1, user_id);
            ps.setLong(2, channel_id);
            rs = ps.executeQuery();

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
    public Optional<ChatDTO> selectOne(long chat_id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ChatDTO dto = null;
        String sql = "select * from chat where chat_id = ?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, chat_id);
            rs = ps.executeQuery();

            if (rs.next()){
                dto = new ChatDTO(
                        rs.getLong("chat_id"),
                        rs.getString("user_id"),
                        rs.getLong("channel_id"),
                        rs.getString("title"),
                        rs.getString("content")
                );
            }
            return Optional.of(dto);
        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            DBManager.releaseConnection(con, ps, rs);
        }
        return Optional.empty();
    }

    @Override
    public List<ChatDTO> selectByContent(long channel_id, String keyWord) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ChatDTO> list = new ArrayList<>();

        /**
         * 해당 채팅 제목에 포함되는 키워드를 찾는 시나리오로 구성
         */
        String sql = "select * from chat where channel_id  = ? and title like ?";

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            String title = '%' + keyWord + '%';
            ps.setLong(1, channel_id);
            ps.setString(2, title);
            rs = ps.executeQuery();

            while (rs.next()) {
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
