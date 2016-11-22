package kanfang.qf.com.kanfang.bean;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/22.
 */
public class CityBean {

    /**
     * cityid : 44
     * cityalias : anshan
     * cityname : 鞍山
     * comparename : 鞍山市
     * center_x : 41.11796524
     * center_y : 123.0302855
     * lng : 0.25229
     * lat : 0.27025
     * mobiletype : 0
     * citypinyin : an shan
     * esfalias : anshan
     * xiaomarent : 0
     * xiaomazhuangxiu : 0
     * xiaomaurl :
     */
    /*城市首字母*/
    private String letter;
    /*分组id*/
    private long typeId;

    private String cityid;
    private String cityalias;
    private String cityname;
    private String comparename;
    private String center_x;
    private String center_y;
    private String lng;
    private String lat;
    private String mobiletype;
    private String citypinyin;
    private String esfalias;
    private int xiaomarent;
    private int xiaomazhuangxiu;
    private String xiaomaurl;


    public CityBean() {
    }

    public CityBean(JSONObject json, long id, String str) {
        typeId = id;
        this.letter = str;
        //从json中取出值
        cityid = json.optString("cityid");
        cityalias = json.optString("cityalias");
        cityname = json.optString("cityname");
        comparename = json.optString("comparename");
        center_x = json.optString("center_x");
        center_y = json.optString("center_y");
        lng = json.optString("lng");
        lat = json.optString("lat");
        mobiletype = json.optString("mobiletype");
        citypinyin = json.optString("citypinyin");
        esfalias = json.optString("esfalias");
        xiaomarent = json.optInt("xiaomarent");
        xiaomazhuangxiu = json.optInt("xiaomazhuangxiu");
        xiaomaurl = json.optString("xiaomaurl");
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public void setCityalias(String cityalias) {
        this.cityalias = cityalias;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public void setComparename(String comparename) {
        this.comparename = comparename;
    }

    public void setCenter_x(String center_x) {
        this.center_x = center_x;
    }

    public void setCenter_y(String center_y) {
        this.center_y = center_y;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setMobiletype(String mobiletype) {
        this.mobiletype = mobiletype;
    }

    public void setCitypinyin(String citypinyin) {
        this.citypinyin = citypinyin;
    }

    public void setEsfalias(String esfalias) {
        this.esfalias = esfalias;
    }

    public void setXiaomarent(int xiaomarent) {
        this.xiaomarent = xiaomarent;
    }

    public void setXiaomazhuangxiu(int xiaomazhuangxiu) {
        this.xiaomazhuangxiu = xiaomazhuangxiu;
    }

    public void setXiaomaurl(String xiaomaurl) {
        this.xiaomaurl = xiaomaurl;
    }

    public String getCityid() {
        return cityid;
    }

    public String getCityalias() {
        return cityalias;
    }

    public String getCityname() {
        return cityname;
    }

    public String getComparename() {
        return comparename;
    }

    public String getCenter_x() {
        return center_x;
    }

    public String getCenter_y() {
        return center_y;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    public String getMobiletype() {
        return mobiletype;
    }

    public String getCitypinyin() {
        return citypinyin;
    }

    public String getEsfalias() {
        return esfalias;
    }

    public int getXiaomarent() {
        return xiaomarent;
    }

    public int getXiaomazhuangxiu() {
        return xiaomazhuangxiu;
    }

    public String getXiaomaurl() {
        return xiaomaurl;
    }
}
