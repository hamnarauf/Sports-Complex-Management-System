package Classes;

import java.sql.Date;

/**
 *
 * @author Hamna Rauf
 */
public class Notice {
    private String title;
    private String text;
    private Date date;

    public Notice(String title, String text, Date date) {
        this.setTitle(title);
        this.setText(text);
        this.setDate(date);
    }
    public Notice(String title, String text) {
        this.setTitle(title);
        this.setText(text);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
