package controller;

import dto.BanDTO;
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
    public static void insertBan(BanDTO banDTO){
        try{
            banService.insertBan(banDTO);
            BanSuccessView.messagePrint("반이 생성되었습니다.");
        }catch (RuntimeException e){
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
        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    };

    /**
     * 단일 반 검색하기
     */
    public static void selectOneBan(int classNum){

        try{
            BanDTO banDTO = banService.selectOneBan(classNum);
            BanSuccessView.selectOne(banDTO);
        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    };

    /**
     * 반 수정하기
     */
    public static void updateBan(BanDTO banDTO){
        try{
            banService.updateBan(banDTO);
            BanSuccessView.messagePrint("반 정보가 변경되었습니다.");
        }catch (RuntimeException e){
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
        }catch (RuntimeException e){
            FailView.errorMessage(e.getMessage());
        }
    };


}
