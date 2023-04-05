package service;

import dto.ROLE;
import dto.UserDTO;

import org.junit.jupiter.api.*;
import service.UserService;
import service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

class UserServiceTest {
    static UserService userService = UserServiceImpl.getInstance();

    static String id = "123";
    static String update_id = "ooda24";
    static String pw = "1234";
    static String name = "test";
//    @BeforeAll
//    static void inputUser() {
//        UserDTO user = new UserDTO(id, pw, name);
//        userService.addUser(user);
//        }
//    @AfterAll
//    static void roleBack() {
//        userService.userDelete(id);
//    }

    @Test
    @DisplayName("유저 전체 목록 조회 성공")
    void 유저_전체_목록_조회_성공() {
        //given
        List<UserDTO> users = null;
        //when
        users = userService.userSelectAll();
        //then
        System.out.println(users.toString());
        Assertions.assertNotNull(users);
    }
    @Test
    @DisplayName("유저 전체 목록 조회 성공")
    void 반_조회_성공() {
        //given
        List<UserDTO> users = null;
        //when
        users = userService.selectByClass(1);
        //then
        System.out.println(users.toString());
        Assertions.assertTrue(users.size() > 0);
    }
    @Test
    @DisplayName("유저 단건 조회")
    void 유저_단건_조회() {
        //given
        UserDTO user = new UserDTO(id, pw, name);
        userService.addUser(user);
        //when
        Optional<UserDTO> target = userService.userSelectOne(id);
        //then
        System.out.println(target.get().toString());
        Assertions.assertEquals(user.toString(), target.get().toString());
    }

    @Test
    @DisplayName("회원 가입 성공")
    void 회원가입_성공() {
        //given
        String id = "Leeeeeeee";
        String pw = "1234";
        String name = "test";
        UserDTO user = new UserDTO(id, pw, name);
        //when
        userService.addUser(user);
        //then
        System.out.println(user.toString());
        Assertions.assertNotNull(userService.userSelectOne(id));
    }


//    @Test
//    @DisplayName("회원 가입 실패 - 공백 ID")
//    void 회원가입_실패_공백_ID() {
//        //given
//        String id = "";
//        String pw = "1234";
//        String name = "test";
//        UserDTO user = new UserDTO(id, pw, name);
//        //when
//        userService.addUser(user);
//        //then
//        System.out.println(user.toString());
////        Assertions.assertThrows()
//    }
//    @Test
//    @DisplayName("회원 가입 실패 - 공백 PW")
//    void 회원가입_실패_공백_PW() {
//        //given
//        String id = "Kim";
//        String pw = "1234";
//        String name = "test";
//        UserDTO user = new UserDTO(id, pw, name);
//        //when
//        userService.addUser(user);
//        //then
//        System.out.println(user.toString());
////        Assertions.assertThrows()
//    }
//    @Test
//    @DisplayName("회원 가입 실패 - 공백 name")
//    void 회원가입_실패_공백_이름() {
//        //given
//        String id = "Lee";
//        String pw = "";
//        String name = "test";
//        UserDTO user = new UserDTO(id, pw, name);
//        //when
//        userService.addUser(user);
//        //then
//        System.out.println(user.toString());
//        Assertions.assertThrows();
//    }
    @Test
    @DisplayName("유저 삭제 성공")
    void 유저_삭제_성공() {
        //given
        Optional<UserDTO> user = userService.userSelectOne(id);
        //when
        userService.userDelete(id);
        //then
        Assertions.assertNotEquals(userService.userSelectOne(id).toString(), user.toString());
    }

    //    @Test
//    @DisplayName("유저 삭제 실패 - 찾을 수 없는 사용자")
//    void 유저_삭제_실패_No_User() {
//        //given
//
//        //when
//        userService.userDelete(id);
//        //then
//        Assertions.assertThrows()
//    }
    @Test
    @DisplayName("유저 수정 성공")
    void 유저_수정_성공() {
        //given
        name = "김길동";
        UserDTO user = userService.userSelectOne(update_id).get();
        //when
        userService.userUpdate(new UserDTO(update_id, pw, name, ROLE.U, 1));
        //then
        System.out.println(user.toString());
        System.out.println(userService.userSelectOne(update_id).get().toString());

        Assertions.assertNotEquals(user.toString(),userService.userSelectOne(update_id).get().toString());
    }






}
