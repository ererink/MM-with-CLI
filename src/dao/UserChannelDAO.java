package dao;

import common.DBManager;
import dto.BanDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserChannelDAO {
    public static int insertRelation(String user_id) {

        Connection con = null;
        PreparedStatement ps = null;

        int result = 0;
        String sql = "insert into user_channel_relation  values (?,(select max(channel_id) from channel))";

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, user_id);

            result = ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("관계생성에 실패했습니다. 다시 생성해주세요.");
        } finally {
            DBManager.releaseConnection(con,ps);
        }
        return result;
    }
}
