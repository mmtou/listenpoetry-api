package art.xingzou.listenpoetry.service;

import art.xingzou.listenpoetry.mapper.PoetryCustomDAO;
import art.xingzou.listenpoetry.mapper.PoetryDAO;
import art.xingzou.listenpoetry.mapper.PoetryExtendDAO;
import art.xingzou.listenpoetry.model.*;
import art.xingzou.listenpoetry.request.FavoriteRequest;
import art.xingzou.listenpoetry.request.IdRequest;
import art.xingzou.listenpoetry.request.ListPoetryRequest;
import art.xingzou.listenpoetry.request.PageRequest;
import art.xingzou.listenpoetry.response.Response;
import art.xingzou.listenpoetry.vo.PoetryDetailVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static art.xingzou.listenpoetry.response.Response.fail;
import static art.xingzou.listenpoetry.response.Response.success;

@Service
public class PoetryService {

    @Autowired
    private PoetryDAO poetryDAO;
    @Autowired
    private PoetryCustomDAO poetryCustomDAO;
    @Autowired
    private PoetryExtendDAO poetryExtendDAO;
    @Autowired
    private FavoriteService favoriteService;

    /**
     * 分页查询诗词列表
     */
    public Response<PageInfo<Poetry>> list(ListPoetryRequest request) {
        final Long authorId = request.getAuthorId();
        final String keyword = request.getKeyword();

        PageHelper.startPage(request.getPageNum(), request.getPageSize(), false);
        PoetryExample poetryExample = new PoetryExample();
        final PoetryExample.Criteria criteria = poetryExample.createCriteria();
        if (authorId != null) {
            criteria.andAuthorIdEqualTo(authorId);
        }
        if (StringUtils.isNoneBlank(keyword)) {
            criteria.andTitleLike("%" + keyword);
        }
        criteria.andDeleteFlagEqualTo((byte) 0);
        final List<Poetry> list = poetryDAO.selectByExampleWithBLOBs(poetryExample);

        return success(new PageInfo<>(list));
    }

    /**
     * 根据id查询详情
     */
    public Response<PoetryDetailVo> get(IdRequest request) {
        PoetryDetailVo vo = new PoetryDetailVo();
        final long id = request.getId();
        final Poetry poetry = poetryDAO.selectByPrimaryKey(id);
        if (poetry == null) {
            return fail("内容不存在");
        }
        vo.setPoetry(poetry);

        PoetryExtendExample example = new PoetryExtendExample();
        example.createCriteria().andPoetryIdEqualTo(id);
        final List<PoetryExtend> poetryExtends = poetryExtendDAO.selectByExampleWithBLOBs(example);
        if (poetryExtends != null && !poetryExtends.isEmpty()) {
            final PoetryExtend poetryExtend = poetryExtends.get(0);
            vo.setPoetryExtend(poetryExtend);
        }

        // 查询用户的点赞列表
        final FavoriteRequest favoriteRequest = new FavoriteRequest();
        favoriteRequest.setSubjectId(id);
        favoriteRequest.setSubjectType((byte) 1);
        favoriteRequest.setPageSize(20);
        final PageInfo<Favorite> favoritePageInfo = favoriteService.list(favoriteRequest).getResult();
        vo.setFavoritePageInfo(favoritePageInfo);

        // 更新阅读数
        updateReadCount(poetry);

        return success(vo);
    }

    //// 1阅读数 == 1热度分；1点赞数 == 2热度分；

    /**
     * 更新阅读数量 TODO 优化
     */
    private void updateReadCount(Poetry poetry) {
        Poetry p = new Poetry();
        p.setId(poetry.getId());
        p.setReadCount(poetry.getReadCount() + 1);
        p.setHotScore(poetry.getHotScore() + 1);
        poetryDAO.updateByPrimaryKeySelective(p);
    }

    /**
     * 更新点赞数量 TODO 优化
     */
    public Response<Boolean> updateFavoriteCount(long id) {
        final Poetry poetry = poetryDAO.selectByPrimaryKey(id);
        if (poetry != null) {
            Poetry p = new Poetry();
            p.setId(poetry.getId());
            p.setLikeCount(poetry.getLikeCount() + 1);
            p.setHotScore(poetry.getHotScore() + 2);
            poetryDAO.updateByPrimaryKeySelective(p);
        }
        return success(true);
    }

    /**
     * 分页查询收藏的诗词列表
     */
    public Response<PageInfo<Poetry>> favoriteList(PageRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize(), false);
        List<Poetry> poetries = poetryCustomDAO.favoriteList(request.getCurrentUserId());
        return success(PageInfo.of(poetries));
    }

}
