package art.xingzou.listenpoetry.web;

import art.xingzou.listenpoetry.request.*;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import art.xingzou.listenpoetry.model.Poetry;
import art.xingzou.listenpoetry.response.Response;
import art.xingzou.listenpoetry.service.FavoriteService;
import art.xingzou.listenpoetry.service.PoetryService;
import art.xingzou.listenpoetry.service.SearchService;
import art.xingzou.listenpoetry.vo.PoetryDetailVo;

@RestController
@RequestMapping("poetry")
public class PoetryController {

    @Autowired
    private PoetryService poetryService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private SearchService searchService;

    @GetMapping("list")
    public Response<PageInfo<Poetry>> list(@ModelAttribute ListPoetryRequest request) {
        final String keyword = request.getKeyword();
        searchService.add(new AddSearchRequest(keyword));
        return poetryService.list(request);
    }

    @GetMapping("/detail")
    public Response<PoetryDetailVo> get(IdRequest request) {
        return poetryService.get(request);
    }

    @PostMapping("/favorite/{id}")
    public Response<Boolean> favorite(@PathVariable long id) {
        favoriteService.favorite(new FavoriteRequest(id, (byte) 1));
        return poetryService.updateFavoriteCount(id);
    }

    @GetMapping("/favorite/list")
    public Response<PageInfo<Poetry>> favoriteList(PageRequest request) {
        return poetryService.favoriteList(request);
    }

}
