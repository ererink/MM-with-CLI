package view;

import dto.ChatDTO;
import print.PrintClass;

import java.util.List;

public class ChatSuccessView {
	private static PrintClass printClass = PrintClass.getInstance();
	public static void selectPrint(List<ChatDTO> list) {
		System.out.println();
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 총 "+list.size()+" 개의 게시물");
		printClass.addRow();
		printClass.addElement("채팅아이디");
		printClass.addElement("유저아이디");
		printClass.addElement("제목");
		printClass.addElement("내용");
		printClass.addElement("날짜");
		for(ChatDTO chat : list) {
			printClass.addRow();
			printClass.addElement(Long.toString(chat.getChat_id()));
			printClass.addElement(chat.getUser_id());
			printClass.addElement(chat.getTitle());
			printClass.addElement(chat.getContent());
			printClass.addElement(chat.getDateTime().toString().substring(2,10));
		}
		printClass.printCurrent();
		
	}

	public static void messagePrint(String message) {
		System.out.println(message);
		
	}

	public static void selectByNoPrint(ChatDTO chatDTO) {
		printClass.addRow();
		printClass.addElement("채팅아이디");
		printClass.addElement("유저아이디");
		printClass.addElement("제목");
		printClass.addElement("내용");
		printClass.addElement("날짜");
		printClass.addRow();
		printClass.addElement(Long.toString(chatDTO.getChat_id()));
		printClass.addElement(chatDTO.getUser_id());
		printClass.addElement(chatDTO.getTitle());
		printClass.addElement(chatDTO.getContent());
		printClass.addElement(chatDTO.getDateTime().toString().substring(2,10));
		printClass.printCurrent();
	}

}












