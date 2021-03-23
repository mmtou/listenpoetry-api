package art.xingzou.listenpoetry.model;

import java.io.Serializable;
import java.util.Date;

/**
 * feedback
 * @author 
 */
public class Feedback implements Serializable {
    private Integer id;

    private Long userId;

    /**
     * 评论的主体id，比如Poetry、author
     */
    private Long subjectId;

    /**
     * 主体类型: 1poetry; 2author
     */
    private Byte subjectType;

    private Integer star;

    private String content;

    /**
     * 0未回复; 1已回复
     */
    private Byte reply;

    private Date createTime;

    /**
     * 0未删除，1已删除
     */
    private Byte deleteFlag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Byte getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(Byte subjectType) {
        this.subjectType = subjectType;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getReply() {
        return reply;
    }

    public void setReply(Byte reply) {
        this.reply = reply;
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