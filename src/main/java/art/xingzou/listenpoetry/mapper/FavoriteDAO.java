package art.xingzou.listenpoetry.mapper;

import art.xingzou.listenpoetry.model.Favorite;
import art.xingzou.listenpoetry.model.FavoriteExample;
import org.springframework.stereotype.Repository;

/**
 * FavoriteDAO继承基类
 */
@Repository
public interface FavoriteDAO extends MyBatisBaseDao<Favorite, Long, FavoriteExample> {
}