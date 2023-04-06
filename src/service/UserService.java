package service;

import dto.UserDTO;
import exception.user.UserDeleteFailureException;
import exception.user.UserJoinFailureException;
import exception.user.UserLoadFailureException;
import exception.user.UserUpdateFailureException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * 모든 유저 조회
     * @return 모든 유저 리스트
     */
    List<UserDTO> userSelectAll() throws UserLoadFailureException;

    List<UserDTO> selectByClass(long id) throws UserLoadFailureException;

    Optional<UserDTO> userSelectOne(String id) throws UserLoadFailureException;

    /**
     * 유저의 반 정보 수정 및 Role 변경(관리자)
     * @param userDTO
     */
    int userUpdate(UserDTO userDTO) throws UserUpdateFailureException;
    /**
     * 유저 삭제(관리자)
     * @param userDTO
     */
    int userDelete(String id) throws UserDeleteFailureException;

    /**
     * 유저 회원가입
     */
    int addUser(UserDTO dto) throws UserJoinFailureException;

    /**
     * 유저 로그인 인증에 사용
     * @param userDTO
     * @return 해당 유저 존재 시 true, 아니면 false
     */
    boolean checkUser(UserDTO userDTO);

}
