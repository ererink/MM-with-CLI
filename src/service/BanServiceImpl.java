package service;

import dao.BanDAO;
import dao.BanDAOImpl;
import dto.BanDTO;
import exception.ban.BanLoadFailedException;
import exception.ban.NotFoundBanException;
import exception.common.InValidInputException;

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
    public int insertBan(BanDTO banDTO) throws NotFoundBanException, InValidInputException {
        if (banDTO.getClass_name() == null || banDTO.getClass_name().length() <= 0 || banDTO.getClass_name().isBlank()) {
            throw new InValidInputException();
        }
        int result = banDAO.insertBan(banDTO);

        if(result == 0){
            throw new NotFoundBanException("해당 반을 생성할 수 없습니다!");

        }

        return result;
    }

    @Override
    public List<BanDTO> selectAllBan() throws NotFoundBanException {
        List<BanDTO> banList  = banDAO.selectAllBan();

        if(banList.size() == 0){
            throw new NotFoundBanException("반의 정보가 존재하지 않습니다!");
        }
        return banList;
    }

    @Override
    public BanDTO selectOneBan(long classNum) throws NotFoundBanException {
        BanDTO banDTO = banDAO.selectOneBan(classNum);

        if(banDTO == null){
            throw new NotFoundBanException("해당 반이 존재하지 않습니다!");
        }
        return banDTO;
    }
    @Override
    public int updateBan(BanDTO banDTO) throws BanLoadFailedException, NotFoundBanException {
        BanDTO dto = selectOneBan(banDTO.getClass_id());
        if (dto == null) {
            throw new NotFoundBanException();
        }
        int result = banDAO.updateBan(banDTO);
        if(result == 0){
            throw new BanLoadFailedException("반의 정보를 변경할 수 없습니다!");
        }
        return result;
    }

    @Override
    public int deleteBan(int classNum) throws BanLoadFailedException, NotFoundBanException {
        int result = banDAO.deleteBan(classNum);
        BanDTO dto = selectOneBan(classNum);

        if(result == 0){
            throw new BanLoadFailedException("반이 삭제되지 않았습니다!");
        }
        return 0;
    }

}
