package art.xingzou.listenpoetry.mapper;

import art.xingzou.listenpoetry.model.Search;
import art.xingzou.listenpoetry.model.SearchExample;
import org.springframework.stereotype.Repository;

/**
 * SearchDAO继承基类
 */
@Repository
public interface SearchDAO extends MyBatisBaseDao<Search, Integer, SearchExample> {
}