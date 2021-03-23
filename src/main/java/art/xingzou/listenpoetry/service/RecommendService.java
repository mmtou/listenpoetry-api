package art.xingzou.listenpoetry.service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import art.xingzou.listenpoetry.mapper.PoetDAO;
import art.xingzou.listenpoetry.mapper.PoetryDAO;
import art.xingzou.listenpoetry.mapper.RecommendDAO;
import art.xingzou.listenpoetry.model.Poet;
import art.xingzou.listenpoetry.model.PoetExample;
import art.xingzou.listenpoetry.model.Poetry;
import art.xingzou.listenpoetry.model.PoetryExample;
import art.xingzou.listenpoetry.model.Recommend;
import art.xingzou.listenpoetry.model.RecommendExample;
import art.xingzou.listenpoetry.request.Request;
import art.xingzou.listenpoetry.response.Response;

import static art.xingzou.listenpoetry.response.Response.success;

@Service
public class RecommendService {

    @Autowired
    private RecommendDAO recommendDAO;
    @Autowired
    private PoetryDAO poetryDAO;
    @Autowired
    private PoetDAO poetDAO;

    /**
     * 推荐列表
     */
    public Response<List<Object>> list(Request request) {
        final RecommendExample recommendExample = new RecommendExample();
        recommendExample.createCriteria().andDeleteFlagEqualTo((byte) 0);
        recommendExample.setOrderByClause("sort");
        final List<Recommend> recommends = recommendDAO.selectByExample(recommendExample);

        Map<String, Integer> itemIndex = Maps.newHashMap();
        // 诗词id
        List<Long> poetryIds = Lists.newArrayList();
        // 作者id
        List<Long> poetIds = Lists.newArrayList();
        for (int i = 0; i < recommends.size(); i++) {
            final Recommend recommend = recommends.get(i);
            final Byte sourceType = recommend.getSourceType();
            final Long sourceId = recommend.getSourceId();
            itemIndex.put(sourceType + ":" + sourceId, i);

            if (sourceType == 1) {
                poetryIds.add(sourceId);
            } else if (sourceType == 2) {
                poetIds.add(sourceId);
            }
        }

        // 最终推荐列表
        List<Object> result = Arrays.asList(new Object[10]);

        // 查询诗词
        if (poetryIds.size() > 0) {
            final PoetryExample poetryExample = new PoetryExample();
            poetryExample.createCriteria().andIdIn(poetryIds);
            final List<Poetry> poetryList = poetryDAO.selectByExampleWithBLOBs(poetryExample);
            poetryList.forEach(item -> {
                final Long id = item.getId();
                final Integer index = itemIndex.get("1:" + id);
                final JSONObject json = (JSONObject) JSONObject.toJSON(item);
                json.put("recommendType", 1);
                result.set(index, json);
            });
        }

        // 查询作者
        if (poetIds.size() > 0) {
            final PoetExample poetExample = new PoetExample();
            poetExample.createCriteria().andIdIn(poetIds);
            final List<Poet> poetList = poetDAO.selectByExample(poetExample);
            poetList.forEach(item -> {
                final Long id = item.getId();
                final Integer index = itemIndex.get("2:" + id);
                final JSONObject json = (JSONObject) JSONObject.toJSON(item);
                json.put("recommendType", 2);
                result.set(index, json);
            });
        }

        return success(result);
    }

    /**
     * 自动生成推荐
     */
    public void generate() {
        PageHelper.startPage(1, 20, false);
        PoetryExample poetryExample = new PoetryExample();
        poetryExample.setOrderByClause("hot_score desc");
        List<Poetry> poetries = poetryDAO.selectByExample(poetryExample);

        PageHelper.startPage(1, 20, false);
        PoetExample poetExample = new PoetExample();
        poetExample.setOrderByClause("hot_score desc");
        List<Poet> poets = poetDAO.selectByExample(poetExample);

        Date date = new Date();

        // 先保存
        for (int i = 0; i < poetries.size(); i++) {
            Poetry poetry = poetries.get(i);
            Recommend recommend = new Recommend();
            recommend.setSourceType((byte) 1);
            // 预留10个空位，供手动指定
            recommend.setSort(i * 2 + 11);
            recommend.setSourceId(poetry.getId());
            recommend.setCreateTime(date);
            recommend.setDeleteFlag((byte) 0);
            recommendDAO.insert(recommend);
        }

        for (int i = 0; i < poets.size(); i++) {
            Poet poet = poets.get(i);
            Recommend recommend = new Recommend();
            recommend.setSourceType((byte) 2);
            recommend.setSort(i * 2 + 12);
            recommend.setSourceId(poet.getId());
            recommend.setCreateTime(date);
            recommend.setDeleteFlag((byte) 0);
            recommendDAO.insert(recommend);
        }

        // 再删除旧数据
        RecommendExample recommendExample = new RecommendExample();
        recommendExample.createCriteria().andCreateTimeLessThan(date);
        recommendDAO.deleteByExample(recommendExample);
    }

}
