package dto;

import java.time.LocalDateTime;

public class ChatDTO {

    private long chat_id;
    private String user_id;
    private long channel_id;
    public LocalDateTime dateTime;
    public String title;
    public String content;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setChannel_id(long channel_id) {
        this.channel_id = channel_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(chat_id).append("  ");
        sb.append(", user_id='").append(user_id).append('\'');
        sb.append(", channel_id=").append(channel_id);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public ChatDTO(){}
    public ChatDTO(long chat_id, String user_id, long channel_id, String title, String content) {
        this.chat_id = chat_id;
        this.user_id = user_id;
        this.channel_id = channel_id;
        this.title = title;
        this.content = content;
        this.dateTime = LocalDateTime.now();
    }
    public long getChat_id() {
        return chat_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public long getChannel_id() {
        return channel_id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
