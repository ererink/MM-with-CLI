package view;

import dto.BanDTO;
import print.PrintClass;

import java.util.List;

/**
 * 성공 시 출력될 메세지
 */
public class BanSuccessView {
    private static PrintClass printClass = PrintClass.getInstance();
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
        System.out.println("================================================MM with CLI CLASS LIST================================================");
        printClass.addRow();
        printClass.addElement("반아이디");
        printClass.addElement("반이름");
        printClass.addElement(" ");
        printClass.addElement(" ");

        for (BanDTO banDTO : list) {
            printClass.addRow();
            printClass.addElement(Long.toString(banDTO.getClass_id()));
            printClass.addElement(banDTO.getClass_name());
            printClass.addElement(" ");
            printClass.addElement(" ");
        }
        printClass.printCurrent();
    }

    /**
     * 단일 반 성공시
     * @param banDTO
     */
//    public static void selectOne(BanDTO banDTO) {
//        System.out.println(banDTO);
//    }


}
