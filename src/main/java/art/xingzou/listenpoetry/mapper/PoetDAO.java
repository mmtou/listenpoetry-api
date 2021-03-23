package art.xingzou.listenpoetry.mapper;

import art.xingzou.listenpoetry.model.Poet;
import art.xingzou.listenpoetry.model.PoetExample;
import org.springframework.stereotype.Repository;

/**
 * PoetDAO继承基类
 */
@Repository
public interface PoetDAO extends MyBatisBaseDao<Poet, Long, PoetExample> {
}