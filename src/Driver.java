import java.util.List;

import entities.Question;
import enums.FilterOn;
import enums.SortBy;
import enums.VoteType;
import repositories.IRepository;
import repositories.InMemoryRepository;
import services.CommentService;
import services.PostService;
import services.QuestionsFeedService;
import services.VotingService;

public class Driver {

    private IRepository repo;
    private CommentService commentService;
    private PostService postService;
    private VotingService votingService;
    private QuestionsFeedService questionsFeedService;

    public Driver() {
        repo = new InMemoryRepository();
        commentService = new CommentService(repo);
        postService = new PostService(repo);
        votingService = new VotingService(repo);
        questionsFeedService = new QuestionsFeedService(repo);
    }

    void test() {
        String qid = postService.post("abhinav", "question description1");
        System.out.println(qid);
        String qid2 = postService.post("tom", "question description2");
        System.out.println(qid2);
        votingService.vote("tom", "1", VoteType.UPVOTE);
        List<Question> sam = questionsFeedService.view("sam", SortBy.NONE, FilterOn.NONE);
        print(sam);
        votingService.vote("tom", "1", VoteType.UPVOTE);
        votingService.vote("tom", "1", VoteType.DOWNVOTE);

        votingService.vote("user1", "1", VoteType.UPVOTE);
        votingService.vote("user2", "1", VoteType.UPVOTE);
        votingService.vote("user3", "1", VoteType.UPVOTE);

        print(questionsFeedService.view("sam", SortBy.NONE, FilterOn.NONE));
        print(questionsFeedService.view("tom", SortBy.NONE, FilterOn.VOTED));

        commentService.comment("milind", "5", "any comment");
        commentService.comment("milind", "1", "any comment2");
        print(questionsFeedService.view("milind", SortBy.UPVOTE, FilterOn.COMMENTED));

    }

    void print(List<Question> questions) {
        for (Question question : questions) {
            System.out.println(question.getQuestionId() + " Desc: " + question.getDescription() + "Creator: " + question.getCreatorName() + " Upvote count : " + question.getUpvoteCount() + " Downvote count : "+ question.getDownvoteCount());
        }
    }

}
