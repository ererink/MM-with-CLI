package view;

import dto.BanDTO;

import java.util.List;

/**
 * 성공 시 출력될 메세지
 */
public class BanSuccessView {
    /**
     * 반 등록, 수정, 삭제 성공시
     * @param message
     */
    public static void messagePrint(String message) {
        System.out.println(message);
    }

    /**
     * 반 전체 검색 성공시
     * @param list
     */
    public static void selectPrint(List<BanDTO> list) {
        System.out.println("===========MM with CLI CLASS LIST============");
        for (BanDTO banDTO : list) {
            System.out.println(banDTO);
        }
    }

    /**
     * 단일 반 성공시
     * @param banDTO
     */
//    public static void selectOne(BanDTO banDTO) {
//        System.out.println(banDTO);
//    }


}
