package service;

import dao.BanDAO;
import dao.BanDAOImpl;
import dto.BanDTO;

import java.util.List;

public class BanServiceImpl implements BanService{
    private static BanService instance = new BanServiceImpl();

    private BanDAO banDAO = BanDAOImpl.getInstance();

    public BanServiceImpl() {
    }

    public static BanService getInstance() {
        return instance;
    }

    @Override
    public int insertBan(BanDTO banDTO) {
        int result = banDAO.insertBan(banDTO);
        if(result == 0 ){
            throw new RuntimeException("해당 반을 생성할 수 없습니다.");
        }
        return result;
    }

    @Override
    public List<BanDTO> selectAllBan() {
        List<BanDTO> banList  = banDAO.selectAllBan();

        if(banList.size() == 0){
            throw new RuntimeException("반의 정보를 불러올 수 없습니다.");
        }
        return banList;
    }

    @Override
    public BanDTO selectOneBan(long classNum) {
        BanDTO banDTO = banDAO.selectOneBan(classNum);

        if(banDTO == null){
            throw new RuntimeException("해당 반이 존재하지 않습니다.");
        }
        return banDTO;
    }
    @Override
    public int updateBan(BanDTO banDTO) {
        int result = banDAO.updateBan(banDTO);
        if(result == 0){
            throw new RuntimeException("반의 정보를 변경할 수 없습니다. 다시 변경해 주세요.");
        }
        return result;
    }

    @Override
    public int deleteBan(int classNum) {
        int result = banDAO.deleteBan(classNum);

        if(result == 0){
            throw new RuntimeException("반의 정보를 삭제할 수 없습니다. 다시 삭제해 주세요.");
        }
        return 0;
    }

}
