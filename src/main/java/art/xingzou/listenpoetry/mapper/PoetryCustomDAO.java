package art.xingzou.listenpoetry.mapper;

import art.xingzou.listenpoetry.model.Poetry;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PoetryCustomDAO
 */
@Repository
public interface PoetryCustomDAO {

    /**
     * 收藏的诗词
     */
    List<Poetry> favoriteList(long userId);

}