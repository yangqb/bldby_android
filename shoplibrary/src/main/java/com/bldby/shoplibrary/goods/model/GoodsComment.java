package com.bldby.shoplibrary.goods.model;

import java.util.List;

public class GoodsComment {

    /**
     * image : WWWW
     * headImg : HHHHH
     * createTime : 2020-06-14 20:40:55
     * nickName : HHHHH
     * ownSpec : {"机身颜色":"白色","内存":"3GB","机身存储":"16GB"}
     * id : 3
     * text : ddd
     */

    private String image;
    private String headImg;
    private String createTime;
    private String nickName;
    private String ownSpec;
    private int id;
    private String text;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOwnSpec() {
        return ownSpec;
    }

    public void setOwnSpec(String ownSpec) {
        this.ownSpec = ownSpec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
