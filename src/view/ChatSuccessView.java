package view;

import dto.ChatDTO;

import java.util.List;
import java.util.Optional;

public class ChatSuccessView {

	public static void selectPrint(List<ChatDTO> list) {
		System.out.println("----Board LIST ("+list.size()+") 개 ------------------");
		for(ChatDTO chat : list) {
			System.out.println(chat);
		}
		
	}

	public static void messagePrint(String message) {
		System.out.println(message);
		
	}

	public static void selectByNoPrint(Optional<ChatDTO> chatDTO) {
		System.out.println(chatDTO);
		
	}


}












