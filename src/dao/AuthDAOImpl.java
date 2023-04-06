package dao;

import common.DBManager;
import exception.auth.LoginFailedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAOImpl implements AuthDAO {
    private static AuthDAO instance = new AuthDAOImpl();

    public static AuthDAO getInstance() {
        return instance;
    }

    /**
     * yeoooo:
     * DB를 통해 로그인 확인
     * @param id
     * @param pw
     */
    @Override
    public boolean login(String id, String pw) throws LoginFailedException {
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

            throw new LoginFailedException(e);
        }
    }
}
