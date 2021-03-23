package art.xingzou.listenpoetry.web;

import art.xingzou.listenpoetry.model.Poet;
import art.xingzou.listenpoetry.request.*;
import art.xingzou.listenpoetry.response.Response;
import art.xingzou.listenpoetry.service.FavoriteService;
import art.xingzou.listenpoetry.service.PoetService;
import art.xingzou.listenpoetry.service.SearchService;
import art.xingzou.listenpoetry.vo.PoetDetailVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("poet")
public class PoetController {

    @Autowired
    private PoetService poetService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private SearchService searchService;

    @GetMapping("list")
    public Response<PageInfo<Poet>> list(@ModelAttribute ListPoetRequest request) {
        final String keyword = request.getKeyword();
        searchService.add(new AddSearchRequest(keyword));
        return poetService.list(request);
    }

    @GetMapping("/detail")
    public Response<PoetDetailVo> get(IdRequest request) {
        return poetService.get(request);
    }

    @PostMapping("/favorite/{id}")
    public Response<Boolean> favorite(@PathVariable long id) {
        favoriteService.favorite(new FavoriteRequest(id, (byte) 1));
        return poetService.updateFavoriteCount(id);
    }

    @GetMapping("/favorite/list")
    public Response<PageInfo<Poet>> favoriteList(PageRequest request) {
        return poetService.favoriteList(request);
    }

}
