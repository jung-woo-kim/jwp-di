package jwp.model;

import java.sql.Date;
import java.util.Objects;

public class Question {
    private int questionId;
    private String writer;
    private String title;
    private String contents;
    private Date createdDate;
    private int countOfAnswer;

    public Question(int questionId, String writer, String title, String contents, Date createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return getQuestionId() == question.getQuestionId() && getCountOfAnswer() == question.getCountOfAnswer() && Objects.equals(getWriter(), question.getWriter()) && Objects.equals(getTitle(), question.getTitle()) && Objects.equals(getContents(), question.getContents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestionId(), getWriter(), getTitle(), getContents(), getCountOfAnswer());
    }
}
