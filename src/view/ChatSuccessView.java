package view;

import dto.ChatDTO;

import java.util.List;

public class ChatSuccessView {

	public static void selectPrint(List<ChatDTO> list) {
		System.out.println();
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 총 "+list.size()+" 개의 게시물");
		System.out.println("    ID       TITLE      CONTENT ");
		for(ChatDTO chat : list) {
			System.out.println(chat);
		}
		
	}

	public static void messagePrint(String message) {
		System.out.println(message);
		
	}

	public static void selectByNoPrint(ChatDTO chatDTO) {
		System.out.println("    ID       TITLE      CONTENT ");
		System.out.println(chatDTO);
		
	}

}












