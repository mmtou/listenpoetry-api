package art.xingzou.listenpoetry.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import art.xingzou.listenpoetry.mapper.FeedbackDAO;
import art.xingzou.listenpoetry.model.Feedback;
import art.xingzou.listenpoetry.request.FeedbackRequest;
import art.xingzou.listenpoetry.response.Response;

import static art.xingzou.listenpoetry.response.Response.fail;
import static art.xingzou.listenpoetry.response.Response.success;

@Service
public class FeedbackService {

  @Autowired
  private FeedbackDAO feedbackDAO;

  public Response<Boolean> add(FeedbackRequest request) {
    final String content = request.getContent();
    if (StringUtils.isBlank(content)) {
      return fail("反馈内容为空");
    }
    final Long subjectId = request.getSubjectId();
    final Byte subjectType = request.getSubjectType();

    final Feedback feedback = new Feedback();
    feedback.setUserId(request.getCurrentUserId());
    feedback.setContent(content);
    feedback.setSubjectId(subjectId);
    feedback.setSubjectType(subjectType);
    feedback.setStar(request.getStar());
    feedback.setCreateTime(new Date());
    feedback.setDeleteFlag((byte) 0);
    feedbackDAO.insert(feedback);

    return success(true);
  }

}
