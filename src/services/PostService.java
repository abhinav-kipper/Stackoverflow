package services;

import entities.Question;
import repositories.IRepository;

public class PostService {

    IRepository repo;

    public PostService(IRepository repo) {
        this.repo = repo;
    }

    /**
     * Posts question created by a user
     * @return question id
     */
    public String post(String userName, String query) {
        Question question = new Question(query, userName);
        return repo.addQuestion(question);
    }
}
