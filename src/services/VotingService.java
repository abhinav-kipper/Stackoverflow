package services;

import enums.VoteType;
import repositories.IRepository;

public class VotingService {

    IRepository repo;

    public VotingService(IRepository repo) {
        this.repo = repo;
    }

    /**
     * Upvote or DownVote a question
     */
    public void vote(String userName, String questionId, VoteType voteType) {
            switch (voteType) {
                case UPVOTE:
                    repo.upvote(userName, questionId);
                    break;
                default:
                    repo.downvote(userName, questionId);

            }
    }

}
