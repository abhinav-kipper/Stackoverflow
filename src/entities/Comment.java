package entities;

public class Comment {
  private static Integer counter = 0;
  private String commentId;
  private String commentString;
  private String commentedBy; // username who commented

  public Comment(String commentString, String commentedBy) {
    this.commentString = commentString;
    this.commentedBy = commentedBy;
    this.commentId = Integer.toString(++counter);
  }
}
