package com.bldby.baselibrary.app.network;

import com.alibaba.fastjson.annotation.JSONField;
import com.bldby.baselibrary.core.network.BaseApiRequest;

import java.util.List;

/**
 * Created by bch on 2020/6/22
 * 这个base分页加载的基类请求
 */
class BasePageModel {
    /**
     * total : 5
     * totalPage : 1
     * list : [{"image":"WWWW","headImg":"HHHHH","createTime":"2020-06-14 20:40:55","nickName":"HHHHH","ownSpec":"{\"机身颜色\":\"白色\",\"内存\":\"3GB\",\"机身存储\":\"16GB\"}","id":3,"text":"ddd"},{"image":"sssss","headImg":"wwwww","createTime":"2020-06-12 20:40:55","nickName":"wwwww","ownSpec":"{\"机身颜色\":\"白色\",\"内存\":\"3GB\",\"机身存储\":\"16GB\"}","id":1,"text":"好好好好"},{"image":"WWWW","headImg":"JJJJJ","createTime":"2020-06-15 20:40:55","nickName":"HHHH","ownSpec":"{\"机身颜色\":\"白色\",\"内存\":\"3GB\",\"机身存储\":\"16GB\"}","id":4,"text":"dddd"},{"image":"SSSS","headImg":"GGGGG","createTime":"2020-06-13 20:40:55","nickName":"HHHH","ownSpec":"{\"机身颜色\":\"白色\",\"内存\":\"3GB\",\"机身存储\":\"16GB\"}","id":2,"text":"hedd"},{"image":"ssssss","headImg":"sssss","createTime":null,"nickName":"aaaaa","ownSpec":"{\"机身颜色\":\"白色\",\"内存\":\"3GB\",\"机身存储\":\"16GB\"}","id":6,"text":"ssssssssssssssssssssssssss"}]
     */
    @JSONField(name = "total")
    public int total;

    @JSONField(name = "totalPage")
    public int totalPage;

    @JSONField(name = "list")
    public Object list;
}

