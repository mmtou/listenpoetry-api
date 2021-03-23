package art.xingzou.listenpoetry.request;

public class PageRequest extends Request {

  private int pageNum = 1;

  private int pageSize = 20;

  public int getPageNum() {
    if (pageNum < 1) {
      pageNum = 1;
    }
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    if (pageSize > 20) {
      pageSize = 20;
    }
    if (pageSize < 1) {
      pageSize = 20;
    }
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
