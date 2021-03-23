package art.xingzou.listenpoetry.mapper;

import art.xingzou.listenpoetry.model.Poetry;
import art.xingzou.listenpoetry.model.PoetryExample;
import org.springframework.stereotype.Repository;

/**
 * PoetryDAO继承基类
 */
@Repository
public interface PoetryDAO extends MyBatisBaseDao<Poetry, Long, PoetryExample> {
}