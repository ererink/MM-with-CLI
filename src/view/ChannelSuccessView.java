package view;

import dto.ChannelDTO;

import java.util.List;

public class ChannelSuccessView {
    public static void messagePrint(String message) {
        System.out.println(message);
    }

    public static void selectPrint(List<ChannelDTO> list) {
        for (ChannelDTO channelDTO : list) {
            System.out.println(channelDTO);
        }
    }

    public static void selectOnePrint(ChannelDTO channelDTO) {
        System.out.println(channelDTO);
    }
}
