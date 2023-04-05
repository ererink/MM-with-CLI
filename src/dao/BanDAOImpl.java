package dao;

import common.DBManager;
import dto.BanDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BanDAOImpl implements BanDAO{

    private static BanDAO instance = new BanDAOImpl();

    private BanDAOImpl(){}

    public static BanDAO getInstance() {
        return instance;
    }

    @Override
    public int insertBan(BanDTO banDTO) {

        Connection con = null;
        PreparedStatement ps = null;

        int result = 0;
        String sql = "insert into class (class_id, class_name) values (?,?)";

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);

            ps.setLong(1, banDTO.getClass_id());
            ps.setString(2, banDTO.getClass_name());

            result = ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("해당 반을 생성할 수 없습니다. 다시 생성해주세요.");
        } finally {
            DBManager.releaseConnection(con,ps);
        }
        return result;
    }

    @Override
    public List<BanDTO> selectAllBan() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<BanDTO> list = new ArrayList<>();

        String sql = "select * from class order by class_id";

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                BanDTO banDTO = new BanDTO(rs.getLong("class_id"), rs.getString("class_name") );

                list.add(banDTO);
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("반의 정보를 불러올 수 없습니다. 다시 조회해 주세요.");
        } finally {
            DBManager.releaseConnection(con,ps, rs);
        }
        return list;
    }

    @Override
    public BanDTO selectOneBan(int classNum) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        BanDTO dto = null;

        String sql = " select * from class where class_id = ?";

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);

            ps.setLong(1, classNum);

            if (rs.next()){
                dto = new BanDTO(rs.getLong("class_id"), rs.getString("class_name"));
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("반의 정보를 불러올 수 없습니다. 다시 조회해 주세요.");
        } finally {
            DBManager.releaseConnection(con,ps, rs);
        }
        return dto;
    }

    @Override
    public int updateBan(BanDTO banDTO) {

        Connection con = null;
        PreparedStatement ps = null;

        int result = 0;
        String sql = "update class set class_name = ? where class_id=?";

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, banDTO.getClass_name());
            ps.setLong(2, banDTO.getClass_id());

            result =  ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("반의 정보를 변경할 수 없습니다. 다시 변경해 주세요.");
        } finally {
            DBManager.releaseConnection(con,ps);
        }

        return result;
    }

    @Override
    public int deleteBan(int classNum) {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;

        String sql = "delete from class where class_id = ?";

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);

            ps.setLong(1, classNum);

            result =  ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("반의 정보를 삭제할 수 없습니다. 다시 삭제해 주세요.");
        } finally {
            DBManager.releaseConnection(con,ps);
        }

        return result;
    }


}