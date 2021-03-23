package art.xingzou.listenpoetry.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import art.xingzou.listenpoetry.request.FeedbackRequest;
import art.xingzou.listenpoetry.response.Response;
import art.xingzou.listenpoetry.service.FeedbackService;

@RestController
@RequestMapping("feedback")
public class FeedbackController {

  @Autowired
  private FeedbackService feedbackService;

  @PostMapping
  public Response<Boolean> feedback(@RequestBody FeedbackRequest request) {
    return feedbackService.add(request);
  }

}
