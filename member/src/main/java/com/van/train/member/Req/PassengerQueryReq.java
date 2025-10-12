package com.van.train.member.Req;

//import com.van.train.common.Req.PageReq;
//extends PageReq

import java.time.LocalDateTime;

public class PassengerQueryReq  {

    private Long id;
    private Long memberId;
    private String name;
    private String idCard;
    private Integer type;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PassengerQueryReq{");
        sb.append("id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", idCard='").append(idCard).append('\'');
        sb.append(", type=").append(type);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append('}');
        return sb.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

}
