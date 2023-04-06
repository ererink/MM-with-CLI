package service;

import dto.BanDTO;

import java.util.List;

public interface BanService {
    /**
     * 반 등록하기
     */
    int insertBan(BanDTO banDTO);

    /**
     * 반 전체 검색하기
     */
    List<BanDTO> selectAllBan();

    /**
     * 단일 반 검색하기
     */
    BanDTO selectOneBan(long classNum);

    /**
     * 반 수정하기
     */
    int updateBan(BanDTO banDTO);

    /**
     * 반 삭제하기
     */
    int deleteBan(int classNum);
}
