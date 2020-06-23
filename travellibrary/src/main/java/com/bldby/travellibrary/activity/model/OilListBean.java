package com.bldby.travellibrary.activity.model;

public class OilListBean {


    /**
     * gasId : FT000116344
     * gasName : 丰台区测试油站不对团油专业版个人开放
     * gasLogoSmall : https://static.czb365.com/1582512918327.jpg?x-oss-process=image/resize,m_lfit,h_200,w_200/format,png
     * gasLogoBig : https://static.czb365.com/1582512917601.jpg?x-oss-process=image/resize,m_lfit,h_420,w_630/format,png
     * gasAddress : 北京市丰台区北大地物美超市
     * distanceStr : 1km
     * distance : 1467
     * priceYfq : 7.09
     * priceOfficial : 6.35
     * priceGun : 7.30
     * gasAddressLongitude : 116.2881
     * gasAddressLatitude : 39.861649
     */

    private String gasId;
    private String gasName;
    private String gasLogoSmall;
    private String gasLogoBig;
    private String gasAddress;
    private String distanceStr;
    private int distance;
    private String priceYfq;
    private String priceOfficial;
    private String priceGun;
    private double gasAddressLongitude;
    private double gasAddressLatitude;

    public String getGasId() {
        return gasId;
    }

    public void setGasId(String gasId) {
        this.gasId = gasId;
    }

    public String getGasName() {
        return gasName;
    }

    public void setGasName(String gasName) {
        this.gasName = gasName;
    }

    public String getGasLogoSmall() {
        return gasLogoSmall;
    }

    public void setGasLogoSmall(String gasLogoSmall) {
        this.gasLogoSmall = gasLogoSmall;
    }

    public String getGasLogoBig() {
        return gasLogoBig;
    }

    public void setGasLogoBig(String gasLogoBig) {
        this.gasLogoBig = gasLogoBig;
    }

    public String getGasAddress() {
        return gasAddress;
    }

    public void setGasAddress(String gasAddress) {
        this.gasAddress = gasAddress;
    }

    public String getDistanceStr() {
        return distanceStr;
    }

    public void setDistanceStr(String distanceStr) {
        this.distanceStr = distanceStr;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getPriceYfq() {
        return priceYfq;
    }

    public void setPriceYfq(String priceYfq) {
        this.priceYfq = priceYfq;
    }

    public String getPriceOfficial() {
        return priceOfficial;
    }

    public void setPriceOfficial(String priceOfficial) {
        this.priceOfficial = priceOfficial;
    }

    public String getPriceGun() {
        return priceGun;
    }

    public void setPriceGun(String priceGun) {
        this.priceGun = priceGun;
    }

    public double getGasAddressLongitude() {
        return gasAddressLongitude;
    }

    public void setGasAddressLongitude(double gasAddressLongitude) {
        this.gasAddressLongitude = gasAddressLongitude;
    }

    public double getGasAddressLatitude() {
        return gasAddressLatitude;
    }

    public void setGasAddressLatitude(double gasAddressLatitude) {
        this.gasAddressLatitude = gasAddressLatitude;
    }
}
