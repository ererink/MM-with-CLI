package service;

import dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * 모든 유저 조회
     * @return 모든 유저 리스트
     */
    List<UserDTO> userSelectAll();

    List<UserDTO> selectByClass(long id);

    Optional<UserDTO> userSelectOne(String id);

    /**
     * 유저의 반 정보 수정 및 Role 변경(관리자)
     * @param userDTO
     */
    int userUpdate(UserDTO userDTO);
    /**
     * 유저 삭제(관리자)
     * @param userDTO
     */
    int userDelete(String id);

    /**
     * 유저 회원가입
     */
    int addUser(UserDTO dto);

    /**
     * 유저 로그인 인증에 사용
     * @param userDTO
     * @return 해당 유저 존재 시 true, 아니면 false
     */
    boolean checkUser(UserDTO userDTO);

}
