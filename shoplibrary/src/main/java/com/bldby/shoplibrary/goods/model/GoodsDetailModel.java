package com.bldby.shoplibrary.goods.model;

import java.util.List;

public class GoodsDetailModel {

    /**
     * evals : [{"createTime":"2020-06-18T13:29:06.241Z","headImg":"string","id":1,"image":"string","nickName":"string","ownSpec":"string","text":"string"}]
     * guarantee : string
     * imgList : ["string"]
     * imgs : string
     * introduce : string
     * logistics : string
     * marketPrice : 1
     * maxPrice : 1
     * minPrice : 1
     * postPrice : 1
     * returnPrice : 1
     * sales : 1
     * spec : string
     * title : string
     */

    private String guarantee;
    private String imgs;
    private String introduce;
    private String logistics;
    private int marketPrice;
    private int maxPrice;
    private int minPrice;
    private int postPrice;
    private int returnPrice;
    private int sales;
    private String spec;
    private String title;
    private List<EvalsBean> evals;
    private List<String> imgList;

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public int getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(int marketPrice) {
        this.marketPrice = marketPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(int postPrice) {
        this.postPrice = postPrice;
    }

    public int getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(int returnPrice) {
        this.returnPrice = returnPrice;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<EvalsBean> getEvals() {
        return evals;
    }

    public void setEvals(List<EvalsBean> evals) {
        this.evals = evals;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public static class EvalsBean {
        /**
         * createTime : 2020-06-18T13:29:06.241Z
         * headImg : string
         * id : 1
         * image : string
         * nickName : string
         * ownSpec : string
         * text : string
         */

        private String createTime;
        private String headImg;
        private int id;
        private String image;
        private String nickName;
        private String ownSpec;
        private String text;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
