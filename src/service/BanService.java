package service;

import dao.BanDAO;
import dto.BanDTO;

import java.util.List;

public interface BanService {
    /**
     * 모든 반을 조회(관리자)
     * @return 모든 반 리스트
     */
    List<BanDAO> banSelectAll();

    /**
     * 반 추가(관리자)
     * @param banDTO
     * @return 성공시 true, 실패시 false
     */
    boolean banInsert(BanDTO banDTO);
}
