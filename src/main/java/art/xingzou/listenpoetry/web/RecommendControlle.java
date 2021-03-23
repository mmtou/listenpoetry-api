package art.xingzou.listenpoetry.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import art.xingzou.listenpoetry.request.Request;
import art.xingzou.listenpoetry.response.Response;
import art.xingzou.listenpoetry.service.RecommendService;

@RestController
@RequestMapping("recommend")
public class RecommendControlle {

  @Autowired
  private RecommendService recommendService;

  @GetMapping("list")
  public Response<List<Object>> list(Request request) {
    return recommendService.list(request);
  }

}
