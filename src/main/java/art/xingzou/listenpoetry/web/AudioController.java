package art.xingzou.listenpoetry.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import art.xingzou.listenpoetry.model.Poetry;
import art.xingzou.listenpoetry.request.ListPoetryRequest;
import art.xingzou.listenpoetry.response.Response;
import art.xingzou.listenpoetry.service.AiService;
import art.xingzou.listenpoetry.service.PoetryService;

import static art.xingzou.listenpoetry.response.Response.success;

@RestController
@RequestMapping("audio")
public class AudioController {

  @Autowired
  private PoetryService poetryService;
  @Autowired
  private AiService aiService;

  private Logger logger = LoggerFactory.getLogger(AudioController.class);

  @GetMapping("/get")
  public Response<Boolean> get(@RequestParam int fromPageNum) {
    final ListPoetryRequest request = new ListPoetryRequest();
    int pageNum = fromPageNum;
    request.setPageSize(500);
    request.setOrderBy("id asc,");
    List<Poetry> list = null;
    do {
      request.setPageNum(pageNum);
      list = poetryService.list(request).getResult().getList();
      if (list != null) {
        list.forEach(item -> {
          final Response<Boolean> response = aiService.generate((byte) 1, item.getId(),
              item.getTitle() + "。" + item.getAuthorName() + "。" + item.getContent());
          if (!response.isSuccess()) {
            logger.error("异常了: id: {}", item.getId());
          }
        });
      }
      pageNum++;
      logger.info("第{}页", pageNum);
    } while (list != null && !list.isEmpty());

    return success(true);
  }

}
