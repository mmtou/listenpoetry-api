package art.xingzou.listenpoetry.request;

import javax.validation.constraints.NotNull;

public class FavoriteRequest extends PageRequest {

  public FavoriteRequest() {
  }

  public FavoriteRequest(Long subjectId, Byte subjectType) {
    this.subjectId = subjectId;
    this.subjectType = subjectType;
  }

  @NotNull
  private Long subjectId;

  /**
   * 主体类型: 1poetry; 2author
   */
  @NotNull
  private Byte subjectType;

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

}
