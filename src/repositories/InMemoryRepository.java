package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import entities.Comment;
import entities.Question;
import exceptions.DBException;
import exceptions.ErrorMessage;

public class InMemoryRepository implements IRepository {

    private static Map<String, Question> questions = new HashMap<>();
    private static  Map<String, Set<String>> userUpvotedQuestions = new HashMap<>();
    private static  Map<String, Set<String>> userDownvotedQuestions = new HashMap<>();


    @Override
    public void upvote(String userName, String questionId) {
        Set<String> upvotedQuestions = userUpvotedQuestions.getOrDefault(userName, new HashSet<>());
        Set<String> downvotedQuestions = userDownvotedQuestions.getOrDefault(userName, new HashSet<>());

        if (upvotedQuestions.contains(questionId))
            questions.get(questionId).decrementUpVoteCount();

        if (downvotedQuestions.contains(questionId))
            questions.get(questionId).decrementDownVoteCount();

        upvotedQuestions.add(questionId);
        downvotedQuestions.remove(questionId);
        questions.get(questionId).addVotedUser(userName);
        questions.get(questionId).incrementUpVoteCount();
        userUpvotedQuestions.put(userName, upvotedQuestions);
    }

    @Override
    public void downvote(String userName, String questionId) {
        Set<String> upvotedQuestions = userUpvotedQuestions.getOrDefault(userName, new HashSet<>());
        Set<String> downvotedQuestions = userDownvotedQuestions.getOrDefault(userName, new HashSet<>());

        if (upvotedQuestions.contains(questionId))
            questions.get(questionId).decrementUpVoteCount();

        if (downvotedQuestions.contains(questionId))
            questions.get(questionId).decrementDownVoteCount();


        upvotedQuestions.remove(questionId);
        downvotedQuestions.add(questionId);
        questions.get(questionId).addVotedUser(userName);
        questions.get(questionId).incrementDownVoteCount();
        userDownvotedQuestions.put(userName, downvotedQuestions);
    }

    @Override
    public void comment(String userName, String questionId, String description) {
        Question question = questions.get(questionId);
        if (Objects.isNull(question))
            throw new DBException(ErrorMessage.INVALID_QUES);
        List<Comment> comments = question.getComments();
        comments.add(new Comment(description, userName));
        question.setComments(comments);
        question.addCommentedUser(userName);
        questions.put(questionId, question);
    }

    @Override
    public String addQuestion(Question question) {
        questions.put(question.getQuestionId(), question);
        return question.getQuestionId();
    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questionslist = new ArrayList<>();
        for (Question question : questions.values()) {
            questionslist.add(question);
        }
        return questionslist;
    }
}
