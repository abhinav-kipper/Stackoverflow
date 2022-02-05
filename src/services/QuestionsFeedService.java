package services;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import entities.Question;
import enums.FilterOn;
import enums.SortBy;
import repositories.IRepository;

public class QuestionsFeedService {

    IRepository repo;

    public QuestionsFeedService(IRepository repo) {
        this.repo = repo;
    }

    /**
     * View questions feed
     * @return list of questions with details
     */
    public List<Question> view(String userName, SortBy sortBy, FilterOn filterOn) {

        List<Question> allQuestions = repo.getAllQuestions();
        List<Question> filteredQuestions = filter(allQuestions, filterOn, userName);
        sort(filteredQuestions, sortBy);
        return filteredQuestions;
    }

    private List<Question> filter(List<Question> questions, FilterOn filterOn, String userName) {
        List<Question> filteredQuestions = new ArrayList<>();
        switch (filterOn) {
            case NONE: filteredQuestions = questions; break;

            case POSTED:
                filteredQuestions = questions.stream()
                        .filter( question -> question.getCreatorName().equals(userName))
                        .collect(Collectors.toList());
                break;

            case VOTED:
                filteredQuestions = questions.stream()
                    .filter( question -> question.getUsersWhoVoted().contains(userName))
                    .collect(Collectors.toList());
                break;

            case COMMENTED:
                filteredQuestions = questions.stream()
                        .filter( question -> question.getUsersWhoCommented().contains(userName))
                        .collect(Collectors.toList());
                break;
        }
        return filteredQuestions;
    }

    private void sort(List<Question> questions, SortBy sortBy) {
       // List<Question> sortedQuestions = new ArrayList<>();

        switch (sortBy) {
            case COMMENT:
                Collections.sort(questions, new Comparator<Question>() {
                    @Override
                    public int compare(Question q1, Question q2) {
                        return Integer.compare(q1.getComments().size(), q2.getComments().size());
                    }
                });
            case UPVOTE:
                Collections.sort(questions, new Comparator<Question>() {
                    @Override
                    public int compare(Question q1, Question q2) {
                        return Long.compare(q1.getUpvoteCount(), q2.getUpvoteCount());
                    }
                });
            case DOWNVOTE:
        }
       // return sortedQuestions;
    }

}
