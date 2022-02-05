package repositories;

import java.util.List;

import entities.Question;

public interface IRepository {

    void upvote(String userName, String questionId);

    void downvote(String userName, String questionId);

    void comment(String userName, String questionId, String description);

    String addQuestion(Question question);

    List<Question> getAllQuestions();
}
