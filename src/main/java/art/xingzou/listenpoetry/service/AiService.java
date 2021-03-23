package art.xingzou.listenpoetry.service;

import com.google.common.collect.Maps;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

import art.xingzou.listenpoetry.props.BaiduSpeech;
import art.xingzou.listenpoetry.response.Response;

import static art.xingzou.listenpoetry.response.Response.fail;
import static art.xingzou.listenpoetry.response.Response.success;

@Service
public class AiService {

  private Logger logger = LoggerFactory.getLogger(AiService.class);

  @Autowired
  private BaiduSpeech baiduSpeech;

  public Response<Boolean> generate(byte type, long id, String content) {
//    https://ai.baidu.com/ai-doc/SPEECH/fk38y8nbm
    // 初始化一个AipSpeech
    AipSpeech client = new AipSpeech(baiduSpeech.getAppId(), baiduSpeech.getApiKey(),
        baiduSpeech.getSecretKey());

    // 可选：设置网络连接参数
    client.setConnectionTimeoutInMillis(2000);
    client.setSocketTimeoutInMillis(60000);

    // 调用接口
    // 设置可选参数
    HashMap<String, Object> options = Maps.newHashMap();
    options.put("spd", baiduSpeech.getSpd());
    options.put("pit", baiduSpeech.getPit());
    options.put("vol", baiduSpeech.getVol());
    options.put("per", baiduSpeech.getPer());
    TtsResponse res = client.synthesis(content, "zh", 1, options);
    byte[] data = res.getData();
    JSONObject res1 = res.getResult();
    if (data != null) {
      try {
        Util.writeBytesToFileSystem(data, baiduSpeech.getAiDir() + type + "-" + id + ".mp3");
      } catch (IOException e) {
        logger.error("合成失败", e);
      }
    } else {
      return fail();
    }
    if (res1 != null) {
      logger.info(res1.toString(2));
    }

    return success(true);
  }

}
