package art.xingzou.listenpoetry.vo;

import art.xingzou.listenpoetry.model.Favorite;
import art.xingzou.listenpoetry.model.Poetry;
import art.xingzou.listenpoetry.model.PoetryExtend;
import com.github.pagehelper.PageInfo;

public class PoetryDetailVo {

  private Poetry poetry;

  private PoetryExtend poetryExtend;

  private PageInfo<Favorite> favoritePageInfo;

  public Poetry getPoetry() {
    return poetry;
  }

  public void setPoetry(Poetry poetry) {
    this.poetry = poetry;
  }

  public PoetryExtend getPoetryExtend() {
    return poetryExtend;
  }

  public void setPoetryExtend(PoetryExtend poetryExtend) {
    this.poetryExtend = poetryExtend;
  }

  public PageInfo<Favorite> getFavoritePageInfo() {
    return favoritePageInfo;
  }

  public void setFavoritePageInfo(
      PageInfo<Favorite> favoritePageInfo) {
    this.favoritePageInfo = favoritePageInfo;
  }
}
