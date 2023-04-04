package dto;

public class BanDTO {
    private long class_id;
    private String class_name;

    public long getClass_id() {
        return class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public BanDTO(long class_id, String class_name) {
        this.class_id = class_id;
        this.class_name = class_name;
    }
}
