package art.xingzou.listenpoetry.mapper;

import art.xingzou.listenpoetry.model.Feedback;
import art.xingzou.listenpoetry.model.FeedbackExample;
import org.springframework.stereotype.Repository;

/**
 * FeedbackDAO继承基类
 */
@Repository
public interface FeedbackDAO extends MyBatisBaseDao<Feedback, Integer, FeedbackExample> {
}