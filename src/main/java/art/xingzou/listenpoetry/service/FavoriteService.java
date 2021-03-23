package art.xingzou.listenpoetry.service;

import art.xingzou.listenpoetry.mapper.FavoriteDAO;
import art.xingzou.listenpoetry.model.Favorite;
import art.xingzou.listenpoetry.model.FavoriteExample;
import art.xingzou.listenpoetry.request.FavoriteRequest;
import art.xingzou.listenpoetry.response.Response;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static art.xingzou.listenpoetry.response.Response.success;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteDAO favoriteDAO;

    /**
     * 点赞
     */
    public Response<Boolean> favorite(FavoriteRequest request) {
        final long subjectId = request.getSubjectId();
        final byte subjectType = request.getSubjectType();

        // 判断是否点赞过
        final Boolean exist = this.exist(request).getResult();
        if (!exist) {
            Favorite favorite = new Favorite();
            favorite.setUserId(request.getCurrentUserId());
            favorite.setUserName(request.getCurrentNickname());
            favorite.setSubjectId(subjectId);
            favorite.setSubjectType(subjectType);
            favorite.setCreateTime(new Date());
            favoriteDAO.insertSelective(favorite);
        }

        return success(true);
    }

    /**
     * 查询点赞列表
     */
    public Response<PageInfo<Favorite>> list(FavoriteRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize(), false);
        final Byte subjectType = request.getSubjectType();
        final Long subjectId = request.getSubjectId();

        FavoriteExample example = new FavoriteExample();
        final FavoriteExample.Criteria criteria = example.createCriteria();
        criteria.andSubjectTypeEqualTo(subjectType).andSubjectIdEqualTo(subjectId)
                .andDeleteFlagEqualTo((byte) 0);
        final List<Favorite> list = favoriteDAO.selectByExample(example);
        return success(PageInfo.of(list));
    }

    /**
     * 判断是否赞过
     */
    public Response<Boolean> exist(FavoriteRequest request) {
        final long subjectId = request.getSubjectId();
        final byte subjectType = request.getSubjectType();

        final FavoriteExample favoriteExample = new FavoriteExample();
        favoriteExample.createCriteria().andUserIdEqualTo(request.getCurrentUserId())
                .andSubjectTypeEqualTo(subjectType).andSubjectIdEqualTo(subjectId)
                .andDeleteFlagEqualTo((byte) 0);
        final List<Favorite> favorites = favoriteDAO.selectByExample(favoriteExample);
        if (favorites == null || favorites.isEmpty()) {
            return success(false);
        } else {
            return success(true);
        }
    }

}
