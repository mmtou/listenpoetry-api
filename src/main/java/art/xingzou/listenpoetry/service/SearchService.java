package art.xingzou.listenpoetry.service;

import com.github.pagehelper.PageHelper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import art.xingzou.listenpoetry.mapper.SearchDAO;
import art.xingzou.listenpoetry.model.Search;
import art.xingzou.listenpoetry.model.SearchExample;
import art.xingzou.listenpoetry.request.AddSearchRequest;
import art.xingzou.listenpoetry.request.Request;
import art.xingzou.listenpoetry.response.Response;

import static art.xingzou.listenpoetry.response.Response.fail;
import static art.xingzou.listenpoetry.response.Response.success;

@Service
public class SearchService {

  @Autowired
  private SearchDAO searchDAO;

  /**
   * 查询搜索列表
   */
  public Response<List<Search>> list(Request request) {
    PageHelper.startPage(1, 20, false);
    final SearchExample searchExample = new SearchExample();
    searchExample.createCriteria().andDeleteFlagEqualTo((byte) 0);
    searchExample.setOrderByClause("score desc");
    final List<Search> searches = searchDAO.selectByExample(searchExample);
    return success(searches);
  }

  public Response<Boolean> add(AddSearchRequest request) {
    String keyword = request.getKeyword();
    if (StringUtils.isBlank(keyword)) {
      return fail("关键字为空");
    }
    keyword = keyword.trim();

    final SearchExample searchExample = new SearchExample();
    searchExample.createCriteria().andKeywordEqualTo(keyword).andDeleteFlagEqualTo((byte) 0);
    final List<Search> searches = searchDAO.selectByExample(searchExample);
    if (searches == null || searches.isEmpty()) {
      final Search search = new Search();
      search.setKeyword(keyword);
      search.setScore(1);
      searchDAO.insert(search);
    } else {
      final Search search = searches.get(0);
      search.setScore(search.getScore() + 1);
      searchDAO.updateByPrimaryKey(search);
    }

    return success(true);
  }

}
