package art.xingzou.listenpoetry.mapper;

import art.xingzou.listenpoetry.model.PoetExtend;
import art.xingzou.listenpoetry.model.PoetExtendExample;
import org.springframework.stereotype.Repository;

/**
 * PoetExtendDAO继承基类
 */
@Repository
public interface PoetExtendDAO extends MyBatisBaseDao<PoetExtend, Long, PoetExtendExample> {
}