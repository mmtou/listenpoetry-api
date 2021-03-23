package art.xingzou.listenpoetry.service;

import art.xingzou.listenpoetry.mapper.PoetCustomDAO;
import art.xingzou.listenpoetry.mapper.PoetDAO;
import art.xingzou.listenpoetry.mapper.PoetExtendDAO;
import art.xingzou.listenpoetry.model.*;
import art.xingzou.listenpoetry.request.FavoriteRequest;
import art.xingzou.listenpoetry.request.IdRequest;
import art.xingzou.listenpoetry.request.ListPoetRequest;
import art.xingzou.listenpoetry.request.PageRequest;
import art.xingzou.listenpoetry.response.Response;
import art.xingzou.listenpoetry.vo.PoetDetailVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static art.xingzou.listenpoetry.response.Response.fail;
import static art.xingzou.listenpoetry.response.Response.success;

@Service
public class PoetService {

    @Autowired
    private PoetDAO poetDAO;
    @Autowired
    private PoetCustomDAO poetCustomDAO;
    @Autowired
    private PoetExtendDAO poetExtendDAO;
    @Autowired
    private FavoriteService favoriteService;

    /**
     * 分页查询诗词列表
     */
    public Response<PageInfo<Poet>> list(ListPoetRequest request) {
        final String keyword = request.getKeyword();

        PageHelper.startPage(request.getPageNum(), request.getPageSize(), false);
        PoetExample poetExample = new PoetExample();
        final PoetExample.Criteria criteria = poetExample.createCriteria();
        criteria.andDeleteFlagEqualTo((byte) 0);

        String orderBy = "";
        if (StringUtils.isNoneBlank(keyword)) {
//      criteria.andNameLike("%" + keyword + "%");
            criteria.andNameEqualTo(keyword);
            orderBy = "LENGTH(name) asc,";
        }
//    poetExample.setOrderByClause(orderBy + "hot_score desc");
        final List<Poet> list = poetDAO.selectByExample(poetExample);

        return success(new PageInfo<>(list));
    }

    /**
     * 根据id查询详情
     */
    public Response<PoetDetailVo> get(IdRequest request) {
        PoetDetailVo vo = new PoetDetailVo();
        final long id = request.getId();
        final Poet poet = poetDAO.selectByPrimaryKey(id);
        if (poet == null) {
            return fail("内容不存在");
        }
        vo.setPoet(poet);

        PoetExtendExample example = new PoetExtendExample();
        example.createCriteria().andPoetIdEqualTo(id);
        final List<PoetExtend> poetExtends = poetExtendDAO.selectByExampleWithBLOBs(example);
        if (poetExtends != null && !poetExtends.isEmpty()) {
            final PoetExtend poetExtend = poetExtends.get(0);
            vo.setPoetExtend(poetExtend);
        }

        // 查询用户的点赞列表
        final FavoriteRequest favoriteRequest = new FavoriteRequest();
        favoriteRequest.setSubjectId(id);
        favoriteRequest.setSubjectType((byte) 1);
        favoriteRequest.setPageSize(20);
        final PageInfo<Favorite> favoritePageInfo = favoriteService.list(favoriteRequest).getResult();
        vo.setFavoritePageInfo(favoritePageInfo);

        updateReadCount(poet);

        return success(vo);
    }

    //// 1阅读数 == 1热度分；1点赞数 == 2热度分；

    /**
     * 更新阅读数量 TODO 优化
     */
    private void updateReadCount(Poet poet) {
        Poet p = new Poet();
        p.setId(poet.getId());
        p.setReadCount(poet.getReadCount() + 1);
        p.setHotScore(poet.getHotScore() + 1);
        poetDAO.updateByPrimaryKeySelective(p);
    }

    /**
     * 更新点赞数量 TODO 优化
     */
    public Response<Boolean> updateFavoriteCount(long id) {
        final Poet poet = poetDAO.selectByPrimaryKey(id);
        if (poet != null) {
            Poet p = new Poet();
            p.setId(poet.getId());
            p.setLikeCount(poet.getLikeCount() + 1);
            p.setHotScore(poet.getHotScore() + 2);
            poetDAO.updateByPrimaryKeySelective(p);
        }
        return success(true);
    }

    /**
     * 分页查询收藏的诗人列表
     */
    public Response<PageInfo<Poet>> favoriteList(PageRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize(), false);
        List<Poet> poetries = poetCustomDAO.favoriteList(request.getCurrentUserId());
        return success(PageInfo.of(poetries));
    }

}
