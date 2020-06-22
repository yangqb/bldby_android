package com.bldby.shoplibrary.goods.model;

import com.bldby.baselibrary.core.util.StringUtil;

import java.util.List;

public class GoodsDetailModel {

    /**
     * imgList : ["http://bldby-dev.oss-cn-beijing.aliyuncs.com/good/imgs/%E4%BA%A7%E5%93%81%E8%AF%A6%E6%83%85%E9%A1%B5-%E8%AF%84%E4%BB%B7_%E5%9B%BE%E7%89%87%E5%B1%95%E7%A4%BA1.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=36010141955087&Signature=yNPz2PSWkfdhZWOVmIDmK09j5iI%3D","http://bldby-dev.oss-cn-beijing.aliyuncs.com/good/imgs/%E5%88%86%E7%B1%BB_%E6%9E%9C%E8%94%AC%E7%94%9F%E9%B2%9C_%E4%BB%B7%E6%94%B92.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=36006246440810&Signature=nJmtBkcS2Q5KZuB1CKc3bCoSOgg%3D"]
     * imgs : http://bldby-dev.oss-cn-beijing.aliyuncs.com/good/imgs/%E4%BA%A7%E5%93%81%E8%AF%A6%E6%83%85%E9%A1%B5-%E8%AF%84%E4%BB%B7_%E5%9B%BE%E7%89%87%E5%B1%95%E7%A4%BA1.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=36010141955087&Signature=yNPz2PSWkfdhZWOVmIDmK09j5iI%3D,http://bldby-dev.oss-cn-beijing.aliyuncs.com/good/imgs/%E5%88%86%E7%B1%BB_%E6%9E%9C%E8%94%AC%E7%94%9F%E9%B2%9C_%E4%BB%B7%E6%94%B92.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=36006246440810&Signature=nJmtBkcS2Q5KZuB1CKc3bCoSOgg%3D
     * minPrice : 0.1
     * maxPrice : 13
     * marketPrice : 0.21
     * returnPrice : 0.12
     * postPrice : 0
     * sales : null
     * logistics : 苹果11
     * guarantee : 所有商品资质齐全，正品保障
     * title : 苹果12
     * introduce : 苹果12,苹果11,苹果11,
     * spec : null
     * evals : [{"id":6,"text":"ssssssssssssssssssssssssss","image":"ssssss","nickName":"aaaaa","headImg":"sssss","createTimeStr":null,"createTime":"2020-06-17 20:28:13","ownSpec":"[{\"specParamName\":\"颜色\",\"value\":\"白色\"},{\"specParamName\":\"重量\",\"value\":\"100g\"},{\"specParamName\":\"内存\",\"value\":\"10G\"}]"},{"id":4,"text":"dddd","image":"WWWW","nickName":"HHHH","headImg":"JJJJJ","createTimeStr":null,"createTime":"2020-06-15 20:40:55","ownSpec":"[{\"specParamName\":\"颜色\",\"value\":\"白色\"},{\"specParamName\":\"重量\",\"value\":\"100g\"},{\"specParamName\":\"内存\",\"value\":\"10G\"}]"},{"id":3,"text":"ddd","image":"WWWW","nickName":"HHHHH","headImg":"HHHHH","createTimeStr":null,"createTime":"2020-06-14 20:40:55","ownSpec":"[{\"specParamName\":\"颜色\",\"value\":\"白色\"},{\"specParamName\":\"重量\",\"value\":\"100g\"},{\"specParamName\":\"内存\",\"value\":\"10G\"}]"},{"id":2,"text":"hedd","image":"SSSS,ssss","nickName":"HHHH","headImg":"GGGGG","createTimeStr":null,"createTime":"2020-06-13 20:40:55","ownSpec":"[{\"specParamName\":\"颜色\",\"value\":\"白色\"},{\"specParamName\":\"重量\",\"value\":\"100g\"},{\"specParamName\":\"内存\",\"value\":\"10G\"}]"},{"id":1,"text":"好好好好","image":"sssss,ssss,ssss","nickName":"wwwww","headImg":"wwwww","createTimeStr":null,"createTime":"2020-06-12 20:40:55","ownSpec":"[{\"specParamName\":\"颜色\",\"value\":\"白色\"},{\"specParamName\":\"重量\",\"value\":\"100g\"},{\"specParamName\":\"内存\",\"value\":\"10G\"}]"}]
     * specList : [{"specParamName":"城市","attributeValueList":[{"value":"北京","skuIdList":[1,4,7,10,13,16,19,22,25]},{"value":"上海","skuIdList":[2,5,8,11,14,17,20,23,16]},{"value":"广州","skuIdList":[3,6,9,12,15,18,21,24,17]}]},{"specParamName":"重量","attributeValueList":[{"value":"100g","skuIdList":[1,2,3,10,11,12,19,20,21]},{"value":"200g","skuIdList":[4,5,6,13,14,15,22,23,24]},{"value":"300g","skuIdList":[7,8,9,16,17,18,25,26,27]}]},{"specParamName":"颜色","attributeValueList":[{"value":"白色","skuIdList":[1,2,3,4,5,6,7,8,9]},{"value":"红色","skuIdList":[10,12,13,14,15,16,17,18,11]},{"value":"黄色","skuIdList":[21,20,23,24,25,26,27,22,19]}]}]
     * skuList : [{"skuId":1,"image":"http://bldby-dev.oss-cn-beijing.aliyuncs.com/good/imgs/%E4%BD%8D%E5%9B%BE%E5%A4%87%E4%BB%BD%403x.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=361646998257&Signature=DkUr0crOhk8UEPob4pQD7kbXmJ0%3D","price":0.1,"returnPrice":0.12,"isEnable":1},{"skuId":2,"image":"http://bldby-dev.oss-cn-beijing.aliyuncs.com/good/imgs/%E4%BD%8D%E5%9B%BE%E5%A4%87%E4%BB%BD%403x.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=361646998257&Signature=DkUr0crOhk8UEPob4pQD7kbXmJ0%3D","price":0.11,"returnPrice":0.11,"isEnable":1},{"skuId":5,"image":"http://bldby-dev.oss-cn-beijing.aliyuncs.com/good/imgs/%E4%BD%8D%E5%9B%BE%E5%A4%87%E4%BB%BD%403x.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=361646998257&Signature=DkUr0crOhk8UEPob4pQD7kbXmJ0%3D","price":13,"returnPrice":0.11,"isEnable":1}]
     * packingList : null
     * afterService : null
     */

    private String imgs;
    private double minPrice;
    private int maxPrice;
    private double marketPrice;
    private double returnPrice;
    private int postPrice;
    private Object sales;
    private String logistics;
    private String guarantee;
    private String title;
    private String introduce;
    private Object spec;
    private Object packingList;
    private Object afterService;
    //    商品主图 ,banner
    private List<String> imgList;
    //    商品评价 ,
    private List<EvalsBean> evals;
    private List<SpecListBean> specList;
    private List<SkuListBean> skuList;

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(double returnPrice) {
        this.returnPrice = returnPrice;
    }

    public int getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(int postPrice) {
        this.postPrice = postPrice;
    }

    public Object getSales() {
        return sales;
    }

    public void setSales(Object sales) {
        this.sales = sales;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Object getSpec() {
        return spec;
    }

    public void setSpec(Object spec) {
        this.spec = spec;
    }

    public Object getPackingList() {
        return packingList;
    }

    public void setPackingList(Object packingList) {
        this.packingList = packingList;
    }

    public Object getAfterService() {
        return afterService;
    }

    public void setAfterService(Object afterService) {
        this.afterService = afterService;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public List<EvalsBean> getEvals() {
        return evals;
    }

    public void setEvals(List<EvalsBean> evals) {
        this.evals = evals;
    }

    public List<SpecListBean> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecListBean> specList) {
        this.specList = specList;
    }

    public List<SkuListBean> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SkuListBean> skuList) {
        this.skuList = skuList;
    }

    public static class EvalsBean {
        /**
         * id : 6
         * text : ssssssssssssssssssssssssss
         * image : ssssss
         * nickName : aaaaa
         * headImg : sssss
         * createTimeStr : null
         * createTime : 2020-06-17 20:28:13
         * ownSpec : [{"specParamName":"颜色","value":"白色"},{"specParamName":"重量","value":"100g"},{"specParamName":"内存","value":"10G"}]
         */

        private int id;
        private String text;
        private String image;
        private String nickName;
        private String headImg;
        private String createTimeStr;
        private String createTime;
        private String ownSpec;

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

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getCreateTimeStr() {
            return createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getOwnSpec() {
            return ownSpec;
        }

        public void setOwnSpec(String ownSpec) {
            this.ownSpec = ownSpec;
        }
    }

    public static class SpecListBean {
        /**
         * specParamName : 城市
         * attributeValueList : [{"value":"北京","skuIdList":[1,4,7,10,13,16,19,22,25]},{"value":"上海","skuIdList":[2,5,8,11,14,17,20,23,16]},{"value":"广州","skuIdList":[3,6,9,12,15,18,21,24,17]}]
         */

        private String specParamName;
        private List<AttributeValueListBean> attributeValueList;

        public String getSpecParamName() {
            return specParamName;
        }

        public void setSpecParamName(String specParamName) {
            this.specParamName = specParamName;
        }

        public List<AttributeValueListBean> getAttributeValueList() {
            return attributeValueList;
        }

        public void setAttributeValueList(List<AttributeValueListBean> attributeValueList) {
            this.attributeValueList = attributeValueList;
        }

        public static class AttributeValueListBean {
            /**
             * value : 北京
             * skuIdList : [1,4,7,10,13,16,19,22,25]
             */

            private String value;
            private List<Integer> skuIdList;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public List<Integer> getSkuIdList() {
                return skuIdList;
            }

            public void setSkuIdList(List<Integer> skuIdList) {
                this.skuIdList = skuIdList;
            }
        }
    }

    public static class SkuListBean {
        /**
         * skuId : 1
         * image : http://bldby-dev.oss-cn-beijing.aliyuncs.com/good/imgs/%E4%BD%8D%E5%9B%BE%E5%A4%87%E4%BB%BD%403x.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=361646998257&Signature=DkUr0crOhk8UEPob4pQD7kbXmJ0%3D
         * price : 0.1
         * returnPrice : 0.12
         * isEnable : 1
         */

        private int skuId;
        private String image;
        private double price;
        private double returnPrice;
        private int isEnable;

        public int getSkuId() {
            return skuId;
        }

        public void setSkuId(int skuId) {
            this.skuId = skuId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getReturnPrice() {
            return returnPrice;
        }

        public void setReturnPrice(double returnPrice) {
            this.returnPrice = returnPrice;
        }

        public int getIsEnable() {
            return isEnable;
        }

        public void setIsEnable(int isEnable) {
            this.isEnable = isEnable;
        }
    }
}
