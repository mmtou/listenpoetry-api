package art.xingzou.listenpoetry.model;

import java.io.Serializable;

/**
 * poetry_extend
 * @author 
 */
public class PoetryExtendWithBLOBs extends PoetryExtend implements Serializable {
    /**
     * 翻译
     */
    private String translation;

    /**
     * 评析
     */
    private String intro;

    /**
     * 注释
     */
    private String annotation;

    /**
     * 赏析
     */
    private String appreciation;

    private static final long serialVersionUID = 1L;

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }
}