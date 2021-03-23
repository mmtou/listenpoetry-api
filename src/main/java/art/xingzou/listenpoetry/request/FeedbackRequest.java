package art.xingzou.listenpoetry.request;

public class FeedbackRequest extends PageRequest {

  private Long subjectId;

  /**
   * 主体类型: 1poetry; 2author
   */
  private Byte subjectType;

  private int star;

  private String content;

  public Long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(Long subjectId) {
    this.subjectId = subjectId;
  }

  public Byte getSubjectType() {
    return subjectType;
  }

  public void setSubjectType(Byte subjectType) {
    this.subjectType = subjectType;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getStar() {
    return star;
  }

  public void setStar(int star) {
    this.star = star;
  }
}
