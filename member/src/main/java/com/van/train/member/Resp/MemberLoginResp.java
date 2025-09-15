package com.van.train.member.Resp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MemberLoginResp {

    @NotBlank(message = "【手机号】不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "手机号码格式错误")
    private String mobile;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MemberLoginResp{");
        sb.append("mobile='").append(mobile).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }

    private String token;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NotBlank(message = "【短信验证码】不能为空")
    private String code;


    public String getMobile() {
        return mobile;
    }

    public void setMobile() {
        this.mobile = mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;  // ✅ 正确
    }


}
