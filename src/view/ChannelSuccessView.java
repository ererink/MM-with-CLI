package view;

import dto.ChannelDTO;
import print.PrintClass;

import java.util.List;

public class ChannelSuccessView {
    static PrintClass printClass = PrintClass.getInstance();
    public static void messagePrint(String message) {
        System.out.println(message);
    }

    public static void selectPrint(List<ChannelDTO> list) {
        printClass.addRow();
        printClass.addElement("공개대상");
        printClass.addElement("채널아이디");
        printClass.addElement("채널이름");
        printClass.addRow();
        printClass.addElement("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (ChannelDTO channelDTO : list) {
            printClass.addRow();
            printClass.addElement((channelDTO.getIsOpen()==1)? "공개":"개인");
            printClass.addElement(Long.toString(channelDTO.getChannel_id()));
            printClass.addElement(channelDTO.getChannel_name());
        }
        printClass.printCurrent();
    }

    public static void selectOnePrint(ChannelDTO channelDTO) {
        System.out.println(channelDTO);
    }
}
