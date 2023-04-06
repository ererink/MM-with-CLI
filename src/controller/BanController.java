package controller;

import dto.BanDTO;
import exception.ban.BanLoadFailedException;
import exception.ban.NotFoundBanException;
import exception.common.InValidInputException;
import service.BanService;
import service.BanServiceImpl;
import view.BanSuccessView;
import view.FailView;
import java.util.List;

public class BanController {
    private static BanService  banService = BanServiceImpl.getInstance();

    /**
     * 반 등록하기
     */
    public static void insertBan(String name) {
        try {
            banService.insertBan(new BanDTO(0,name));
            BanSuccessView.messagePrint("반이 생성되었습니다.");
        } catch (NumberFormatException | NotFoundBanException  | InValidInputException e) {
            FailView.errorMessage(e.getMessage());
        }
    };

    /**
     * 반 전체 검색하기
     */
    public static void selectAllBan(){

        try {
            List<BanDTO> banList = banService.selectAllBan();
            BanSuccessView.selectPrint(banList);
        }catch (NotFoundBanException e){
            FailView.errorMessage(e.getMessage());
        }
    };

    /**
     * 단일 반 검색하기
     */
//    public static void selectOneBan(int classNum){
//
//        try{
//            BanDTO banDTO = banService.selectOneBan(classNum);
//            BanSuccessView.selectOne(banDTO);
//        }catch (RuntimeException e){
//            FailView.errorMessage(e.getMessage());
//        }
//    };

    /**
     * 반 수정하기
     */
    public static void updateBan(long class_id, String class_Name) {
        try {
            banService.updateBan(new BanDTO(class_id, class_Name));
            BanSuccessView.messagePrint("반 정보가 변경되었습니다.");
        } catch (BanLoadFailedException | NotFoundBanException e) {
            FailView.errorMessage(e.getMessage());
        }
    };

    /**
     * 반 삭제하기
     */
    public static void deleteBan(int classNum){
        try{
            banService.deleteBan(classNum);
            BanSuccessView.messagePrint("반이 삭제되었습니다.");
        }catch (BanLoadFailedException | NotFoundBanException e){
            FailView.errorMessage(e.getMessage());
        }
    };


}
