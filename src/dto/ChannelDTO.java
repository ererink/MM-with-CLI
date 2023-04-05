package dto;

public class ChannelDTO {
    private long channel_id;
    private String channel_name;
    private long class_id;
    private int isOpen;

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public ChannelDTO(long channel_id, String channel_name, long class_id,int isOpen) {
        this.channel_id = channel_id;
        this.channel_name = channel_name;
        this.class_id = class_id;
        this.isOpen = isOpen;
    }

    public void setChannel_id(long channel_id) {
        this.channel_id = channel_id;
    }

    public long getChannel_id() {
        return channel_id;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public long getClass_id() {
        return class_id;
    }

    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "|"+ channel_id +
                "|"+ channel_name + "|";
    }
}
