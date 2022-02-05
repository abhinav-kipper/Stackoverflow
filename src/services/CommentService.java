package services;


import repositories.IRepository;


public class CommentService {

    IRepository repo;

    public CommentService(IRepository repo) {
        this.repo = repo;
    }

    /**
     * Comment on a question
     */
    public void comment(String userName, String questionId, String commentString) {
        repo.comment(userName, questionId, commentString);
    }
}
