package entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question {
    private static Integer counter = 0;
    private String questionId;
    private String description;
    private String creatorName;
    private Long upvoteCount = 0L;
    private Long downvoteCount = 0L;
    private List<Comment> comments = new ArrayList<>();
    private Set<String> usersWhoVoted = new HashSet<>();
    private Set<String> usersWhoCommented = new HashSet<>();

    public Question(String description, String creatorName) {
        this.description = description;
        this.creatorName = creatorName;
        this.questionId = Integer.toString(++counter);
    }

    public String getQuestionId() {
        return questionId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void addVotedUser(String userName) {
        usersWhoVoted.add(userName);
    }

    public void addCommentedUser(String userName) {
        usersWhoCommented.add(userName);
    }

    public Set<String> getUsersWhoVoted() {
        return usersWhoVoted;
    }

    public Set<String> getUsersWhoCommented() {
        return usersWhoCommented;
    }

    public void incrementUpVoteCount() {
        upvoteCount++;
    }

    public void incrementDownVoteCount() {
        downvoteCount++;
    }

    public void decrementUpVoteCount() {
        upvoteCount--;
    }

    public void decrementDownVoteCount() {
        downvoteCount--;
    }

    public Long getUpvoteCount() {
        return upvoteCount;
    }

    public Long getDownvoteCount() {
        return downvoteCount;
    }

    public String getDescription() {
        return description;
    }
}
