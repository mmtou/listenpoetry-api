package art.xingzou.listenpoetry.request;

public class IdRequest extends Request {

  public IdRequest(long id) {
    this.id = id;
  }

  public IdRequest() {
  }

  private long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
