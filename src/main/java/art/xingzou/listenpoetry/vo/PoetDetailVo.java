package art.xingzou.listenpoetry.vo;

import art.xingzou.listenpoetry.model.Favorite;
import art.xingzou.listenpoetry.model.Poet;
import art.xingzou.listenpoetry.model.PoetExtend;
import com.github.pagehelper.PageInfo;

public class PoetDetailVo {

    private Poet poet;

    private PoetExtend poetExtend;

    private PageInfo<Favorite> favoritePageInfo;

    public Poet getPoet() {
        return poet;
    }

    public void setPoet(Poet poet) {
        this.poet = poet;
    }

    public PoetExtend getPoetExtend() {
        return poetExtend;
    }

    public void setPoetExtend(PoetExtend poetExtend) {
        this.poetExtend = poetExtend;
    }

    public PageInfo<Favorite> getFavoritePageInfo() {
        return favoritePageInfo;
    }

    public void setFavoritePageInfo(
            PageInfo<Favorite> favoritePageInfo) {
        this.favoritePageInfo = favoritePageInfo;
    }

}
