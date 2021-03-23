package art.xingzou.listenpoetry.model;

import java.io.Serializable;
import java.util.Date;

/**
 * poet_extend
 * @author 
 */
public class PoetExtend implements Serializable {
    private Long id;

    /**
     * 作者编号
     */
    private Long poetId;

    private Date updateTime;

    private Long updateBy;

    private Byte deleteFlag;

    /**
     * 用户简介
     */
    private String description;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPoetId() {
        return poetId;
    }

    public void setPoetId(Long poetId) {
        this.poetId = poetId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}