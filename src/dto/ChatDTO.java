package dto;

import java.time.LocalDateTime;

/**
 * <채팅 정보>
 * 채팅 id, 유저 id, 채널 id, 채팅 생성 시간, 채팅 제목, 채팅 내용
 */
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
        String c_id = String.format("%-3s", chat_id);
        sb.append(c_id);
        String u_id = String.format("%-10s", user_id);
        sb.append(u_id);
        String tit = String.format("%-20s", title);
        sb.append(tit);
        String cont = String.format("%-40s", content);
        sb.append(cont);
//        sb.append(user_id).append("  ");
//        sb.append(title).append("  ");
//        sb.append(content).append("  ");
        String date = String.format("%-10s", dateTime.toString().substring(2, 10));
        sb.append(date);
//        sb.append(dateTime.toString().substring(2, 10));
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
