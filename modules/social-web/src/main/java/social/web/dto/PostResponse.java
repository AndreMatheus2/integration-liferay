package social.web.dto;


public class PostResponse {

    private String dateTime;
    private String text;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PostResponse(String dateTime, String text) {
        this.dateTime = dateTime;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "dateTime=" + dateTime +
                ", text='" + text + '\'' +
                '}';
    }
}
