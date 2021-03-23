package art.xingzou.listenpoetry.mapper;

import art.xingzou.listenpoetry.model.Poet;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PoetCustomDAO
 */
@Repository
public interface PoetCustomDAO {

    /**
     * 收藏的诗人
     */
    List<Poet> favoriteList(long userId);

}