package art.xingzou.listenpoetry.mapper;

import art.xingzou.listenpoetry.model.Recommend;
import art.xingzou.listenpoetry.model.RecommendExample;
import org.springframework.stereotype.Repository;

/**
 * RecommendDAO继承基类
 */
@Repository
public interface RecommendDAO extends MyBatisBaseDao<Recommend, Integer, RecommendExample> {
}