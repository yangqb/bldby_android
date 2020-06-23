package com.bldby.shoplibrary.classify.bean;

import java.util.List;

public class ClassRightGoodsBean {

    private List<CategoryListBean> categoryList;
    private List<CategoryGoodsListBean> categoryGoodsList;

    public List<CategoryListBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryListBean> categoryList) {
        this.categoryList = categoryList;
    }

    public List<CategoryGoodsListBean> getCategoryGoodsList() {
        return categoryGoodsList;
    }

    public void setCategoryGoodsList(List<CategoryGoodsListBean> categoryGoodsList) {
        this.categoryGoodsList = categoryGoodsList;
    }

    public static class CategoryListBean {
        /**
         * image : null
         * name : 手机3C
         * id : 2
         */

        private Object image;
        private String name;
        private int id;

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class CategoryGoodsListBean {
        /**
         * image : http://bldby-dev.oss-cn-beijing.aliyuncs.com/good/imgs/%E4%BA%A7%E5%93%81%E8%AF%A6%E6%83%85%E9%A1%B5-%E8%AF%84%E4%BB%B7_%E5%9B%BE%E7%89%87%E5%B1%95%E7%A4%BA1.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=36010141955087&Signature=yNPz2PSWkfdhZWOVmIDmK09j5iI%3D
         * minMarketPrice : 0.08
         * counts : 15
         * minPrice : 0.08
         * minReturnPrice : null
         * goodsname : 苹果12
         * spuId : 2
         * sales : null
         */

        private String image;
        private double minMarketPrice;
        private int counts;
        private double minPrice;
        private Object minReturnPrice;
        private String goodsname;
        private int spuId;
        private Object sales;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public double getMinMarketPrice() {
            return minMarketPrice;
        }

        public void setMinMarketPrice(double minMarketPrice) {
            this.minMarketPrice = minMarketPrice;
        }

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public double getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(double minPrice) {
            this.minPrice = minPrice;
        }

        public Object getMinReturnPrice() {
            return minReturnPrice;
        }

        public void setMinReturnPrice(Object minReturnPrice) {
            this.minReturnPrice = minReturnPrice;
        }

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public int getSpuId() {
            return spuId;
        }

        public void setSpuId(int spuId) {
            this.spuId = spuId;
        }

        public Object getSales() {
            return sales;
        }

        public void setSales(Object sales) {
            this.sales = sales;
        }
    }
}
