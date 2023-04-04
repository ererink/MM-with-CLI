package service;

import dto.UserDTO;

import java.util.List;

public interface UserService {
    /**
     * 모든 유저 조회
     * @return 모든 유저 리스트
     */
    List<UserDTO> userSelectAll();

    /**
     * 유저의 반 정보 수정 및 Role 변경(관리자)
     * @param userDTO
     */
    void userUpdate(UserDTO userDTO);
    /**
     * 유저 삭제(관리자)
     * @param userDTO
     */
    void userDelete(UserDTO userDTO);

    /**
     * 유저 회원가입
     * @param userDTO
     */
    void addUser(UserDTO userDTO);

    /**
     * 유저 로그인 인증에 사용
     * @param userDTO
     * @return 해당 유저 존재 시 true, 아니면 false
     */
    boolean checkUser(UserDTO userDTO);
}
