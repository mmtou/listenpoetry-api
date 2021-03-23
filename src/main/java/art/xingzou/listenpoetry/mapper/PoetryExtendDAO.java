package art.xingzou.listenpoetry.mapper;

import art.xingzou.listenpoetry.model.PoetryExtend;
import art.xingzou.listenpoetry.model.PoetryExtendExample;
import org.springframework.stereotype.Repository;

/**
 * PoetryExtendDAO继承基类
 */
@Repository
public interface PoetryExtendDAO extends MyBatisBaseDao<PoetryExtend, Long, PoetryExtendExample> {
}