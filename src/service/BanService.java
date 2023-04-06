package service;

import dto.BanDTO;

import java.util.List;

/**
 * 관리자만 접근 가능한 반 관리 서비스
 */
public interface BanService {
    /**
     * 반 등록
     * @param banDTO
     */
    int insertBan(BanDTO banDTO);

    /**
     * 반 전체 검색
     * @return 전체 반 목록
     */
    List<BanDTO> selectAllBan();

    /**
     * classNum애 해당하는 반 검색
     * @param classNum
     * @return 해당 반 정보
     */
    BanDTO selectOneBan(long classNum);

    /**
     * 반 수정
     * @param banDTO
     */
    int updateBan(BanDTO banDTO);

    /**
     * classNum애 해당하는 반 삭제
     * @param classNum
     */
    int deleteBan(int classNum);
}
