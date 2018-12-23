package work.rizvan.com.kingdomtest.model;

public class ToDoModel {

    String keyId;
    String title;
    String description;
    String time;
    String status;

    public ToDoModel(String keyId, String title, String description, String time, String status) {
        this.keyId = keyId;
        this.title = title;
        this.description = description;
        this.time = time;
        this.status = status;
    }

    public ToDoModel(String title, String description, String time, String status) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.status = status;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
