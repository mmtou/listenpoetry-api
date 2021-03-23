package art.xingzou.listenpoetry.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import art.xingzou.listenpoetry.model.Search;
import art.xingzou.listenpoetry.request.Request;
import art.xingzou.listenpoetry.response.Response;
import art.xingzou.listenpoetry.service.SearchService;

@RestController
@RequestMapping("search")
public class SearchController {

  @Autowired
  private SearchService searchService;

  @GetMapping("list")
  public Response<List<Search>> list(Request request) {
    return searchService.list(request);
  }

}
