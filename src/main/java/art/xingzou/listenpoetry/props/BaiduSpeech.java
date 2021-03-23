package art.xingzou.listenpoetry.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "baidu-speech")
public class BaiduSpeech {

  private String appId;
  private String apiKey;
  private String secretKey;
  private String aiDir;
  private String spd;
  private String pit;
  private String vol;
  private String per;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getAiDir() {
    return aiDir;
  }

  public void setAiDir(String aiDir) {
    this.aiDir = aiDir;
  }

  public String getSpd() {
    return spd;
  }

  public void setSpd(String spd) {
    this.spd = spd;
  }

  public String getPit() {
    return pit;
  }

  public void setPit(String pit) {
    this.pit = pit;
  }

  public String getVol() {
    return vol;
  }

  public void setVol(String vol) {
    this.vol = vol;
  }

  public String getPer() {
    return per;
  }

  public void setPer(String per) {
    this.per = per;
  }
}
