package dao;

import common.DBManager;
import dto.ROLE;
import dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private static UserDAO instance = new UserDAOImpl();

    public static UserDAO getInstance() {
        return instance;
    }


    /**
     * 회원 전체 조회 함수
     */
    @Override

    public List<UserDTO> selectAll() {
        String sql = "SELECT * FROM users";
        List<UserDTO> users = new ArrayList<>();
        /**
         * @ yeoooo
         * 2023-04-04
         * try-with-resource
         */
        try (Connection con = DBManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(
                        new UserDTO(
                                rs.getString("USER_ID"),
                                rs.getString("USER_PW"),
                                rs.getString("USER_NAME"),
                                ROLE.valueOf(rs.getString("ROLE")),
                                rs.getInt("CLASS_ID"))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    @Override
    /**
     * 회원 단건 조회 함수
     */
    public Optional<UserDTO> selectOne(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        Optional<UserDTO> user = null;
        try (Connection con = DBManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = Optional.of(new UserDTO(rs.getString("USER_ID"),
                        rs.getString("USER_PW"),
                        rs.getString("USER_NAME"),
                        ROLE.valueOf(rs.getString("ROLE")),
                        rs.getInt("CLASS_ID")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    /**
     * 회원가입 함수
     */
    public String join(String id, String pw, String name) {
        String sql = "INSERT INTO users(user_id, user_pw, user_name) VALUES(?, ?, ?)";
        try (Connection con = DBManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.executeUpdate();
            int result = pstmt.executeUpdate();

            if (result == 0) {
                throw new RuntimeException("가입할 수 없는 상태입니다.");
            } else {
                return id;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    /**
     * 패스워드, 이름, 반, Role 수정 함수
     * 관리자 등록과 회원 가입 승인, 회원 정보 수정에 이용
     */
    public String update(UserDTO dto) {
        String sql = "UPDATE users SET user_pw = ?, user_name = ?, ROLE = ?, class_id = ? WHERE user_id = ?";
        try (Connection con = DBManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dto.getUser_pw());
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getRole().toString());
            pstmt.setInt(4, dto.getClass_id());
            pstmt.setString(5, dto.getUser_id());

            int result = pstmt.executeUpdate();
            if (result == 0) {
                throw new RuntimeException("수정이 반영되지 않았습니다");
            } else {
                return dto.getUser_id();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
