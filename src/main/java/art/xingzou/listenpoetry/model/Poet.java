package art.xingzou.listenpoetry.model;

import java.io.Serializable;
import java.util.Date;

/**
 * poet
 * @author 
 */
public class Poet implements Serializable {
    private Long id;

    /**
     * 作者名
     */
    private String name;

    /**
     * 阅读数
     */
    private Integer readCount;

    /**
     * 喜欢数
     */
    private Integer likeCount;

    /**
     * 热度分值
     */
    private Integer hotScore;

    private Date createTime;

    private Byte deleteFlag;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getHotScore() {
        return hotScore;
    }

    public void setHotScore(Integer hotScore) {
        this.hotScore = hotScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}