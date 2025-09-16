package com.van.train.member.Req;

public class MembersRegiserRec {
    private String mobile;

    @Override
    public String toString() {
        return "MembersRegiserRec{" +
                "mobile='" + mobile + '\'' +
                '}';
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
