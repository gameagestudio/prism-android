
package com.shopex.android.prism.domain;


public class DeviceInfo extends AbstractCommonReq {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    // 厂商
    public String product;
    // 机型
    public String modle;
    // 手机系统版本
    public short sdk;

    public String sdks;
    // IMSI
    public String imsi;
    // IMEI
    public String imei;

    public String version;

    public String versionName;

    public String osVersion;

    public DeviceInfo() {

    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getModle() {
        return modle;
    }

    public void setModle(String modle) {
        this.modle = modle;
    }

    public short getSdk() {
        return sdk;
    }

    public void setSdk(short sdk) {
        this.sdk = sdk;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSdks() {
        return sdks;
    }

    public void setSdks(String sdks) {
        this.sdks = sdks;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

}
