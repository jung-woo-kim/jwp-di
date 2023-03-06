package jwp.service;

import jwp.dao.AnswerDAO;
import jwp.dao.QuestionDAO;
import jwp.model.Answer;
import jwp.model.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QnaService {

    private AnswerDAO answerDAO = AnswerDAO.getInstance();
    private QuestionDAO questionDAO = QuestionDAO.getInstance();
    private static QnaService qnaService;

    private QnaService() {
    }

    public static QnaService getInstance() {
        if (qnaService == null) {
            qnaService = new QnaService();
        }
        return qnaService;
    }

    public void deleteQuestion(String questionId) {
        List<Answer> answers = answerDAO.findAllByQuestionId(Integer.parseInt(questionId));
        Question question = questionDAO.findByQuestionId(Integer.parseInt(questionId));

        List<Answer> notWriterAnswers = answers.stream().filter(answer -> !answer.getWriter().equals(question.getWriter())).collect(Collectors.toList());
        if (notWriterAnswers.size() > 0) {
            throw new IllegalArgumentException();
        }

        answerDAO.delete(question.getQuestionId());
        questionDAO.delete(question.getQuestionId());
    }
}
