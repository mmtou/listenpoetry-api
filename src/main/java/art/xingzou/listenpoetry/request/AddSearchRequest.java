package art.xingzou.listenpoetry.request;

public class AddSearchRequest extends Request {

  private String keyword;

  public AddSearchRequest(String keyword) {
    this.keyword = keyword;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
