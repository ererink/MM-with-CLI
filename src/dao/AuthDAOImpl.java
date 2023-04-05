package dao;

import auth.Authentication;
import common.DBManager;
import session.UserSession;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAOImpl implements AuthDAO {
    private static AuthDAO instance = new AuthDAOImpl();

    public static AuthDAO getInstance() {
        return instance;
    }
    @Override
    public boolean login(String id, String pw) {
        String sql = "SELECT * FROM users WHERE user_id = ? AND user_pw = ?";
        try (Connection con = DBManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1,id);
            pstmt.setString(2, pw);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}
