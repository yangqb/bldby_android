package com.bldby.shoplibrary.seach.model;

import java.util.List;

public class GoodsSeachModel {

    /**
     * total : 4
     * totalPage : 4
     * list : [{"image":"http://bldby-dev.oss-cn-beijing.aliyuncs.com/good/imgs/%E4%BA%A7%E5%93%81%E8%AF%A6%E6%83%85%E9%A1%B5-%E8%AF%84%E4%BB%B7_%E5%9B%BE%E7%89%87%E5%B1%95%E7%A4%BA1.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=36010141955087&Signature=yNPz2PSWkfdhZWOVmIDmK09j5iI%3D","minMarketPrice":0,"counts":3,"minPrice":0,"minReturnPrice":null,"goodsname":"苹果12","spuId":2,"sales":null}]
     */

    private int total;
    private int totalPage;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * image : http://bldby-dev.oss-cn-beijing.aliyuncs.com/good/imgs/%E4%BA%A7%E5%93%81%E8%AF%A6%E6%83%85%E9%A1%B5-%E8%AF%84%E4%BB%B7_%E5%9B%BE%E7%89%87%E5%B1%95%E7%A4%BA1.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=36010141955087&Signature=yNPz2PSWkfdhZWOVmIDmK09j5iI%3D
         * minMarketPrice : 0
         * counts : 3
         * minPrice : 0
         * minReturnPrice : null
         * goodsname : 苹果12
         * spuId : 2
         * sales : null
         */

//        封面图
        private String image;
        //        市场最低价
        private double minMarketPrice;
        //        spu对应sku条数
        private int counts;
        //        minPrice
        private double minPrice;
        //        返利
        private double minReturnPrice;
        //        商品名称
        private String goodsname;
        //        商品id
        private int spuId;
        //        销量
        private int sales;

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

        public double getMinReturnPrice() {
            return minReturnPrice;
        }

        public void setMinReturnPrice(double minReturnPrice) {
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

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }
    }
}
