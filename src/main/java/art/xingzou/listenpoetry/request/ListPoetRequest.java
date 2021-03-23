package art.xingzou.listenpoetry.request;

public class ListPoetRequest extends PageRequest {

  private String keyword;

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
