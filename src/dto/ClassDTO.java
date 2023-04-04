package dto;

public class ClassDTO {
    private long class_id;
    private String class_name;

    public long getClass_id() {
        return class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public ClassDTO(long class_id, String class_name) {
        this.class_id = class_id;
        this.class_name = class_name;
    }
}
