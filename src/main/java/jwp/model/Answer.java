package jwp.model;

import java.sql.Date;

public class Answer {
    private int answerId;
    private int questionId;
    private String writer;
    private String contents;
    private Date createdDate;

    public Answer(int answerId, int questionId, String writer, String contents, Date createdDate) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
    }

    public int getAnswerId() {
        return answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
