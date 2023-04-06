package service;

import dto.BanDTO;
import exception.ban.BanLoadFailedException;
import exception.ban.NotFoundBanException;
import exception.common.InValidInputException;

import java.util.List;

/**
 * 관리자만 접근 가능한 반 관리 서비스
 */
public interface BanService {
    /**
     * 반 등록
     * @param banDTO
     */
    int insertBan(BanDTO banDTO) throws NotFoundBanException, InValidInputException;

    /**
     * 반 전체 검색
     * @return 전체 반 목록
     */
    List<BanDTO> selectAllBan() throws NotFoundBanException;

    /**
     * classNum애 해당하는 반 검색
     * @param classNum
     * @return 해당 반 정보
     */
    BanDTO selectOneBan(long classNum) throws NotFoundBanException;

    /**
     * 반 수정
     * @param banDTO
     */
    int updateBan(BanDTO banDTO) throws BanLoadFailedException, NotFoundBanException;

    /**
     * classNum애 해당하는 반 삭제
     * @param classNum
     */
    int deleteBan(int classNum) throws BanLoadFailedException, NotFoundBanException;
}
