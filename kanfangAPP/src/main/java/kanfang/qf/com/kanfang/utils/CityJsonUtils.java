package kanfang.qf.com.kanfang.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kanfang.qf.com.kanfang.bean.CityBean;

/**
 * 解析城市数据
 */
public class CityJsonUtils {

   public static final String[] lettes = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public static List<CityBean> getCityByJson(String value) throws Exception {
        //结果数据集合
        ArrayList<CityBean> data = new ArrayList<>();
        //最外层的JSON
        JSONObject json = new JSONObject(value);
        //拿到cities数据
        JSONObject cities = json.getJSONObject("cities");
        //取出对应字母的jsonArray
        //循环letters数组，得到每个字母，从cities中取出对应的JSONArray
        for (int i = 0; i < lettes.length; i++) {
            JSONArray arr = cities.optJSONArray(lettes[i]);
            //再循环遍历arr中的每个JSONObject
            if (arr != null) {
                int len = arr.length();
                for (int j = 0; j < len; j++) {
                    //取出对应的JSONObject
                    JSONObject tmp = arr.getJSONObject(j);
                    //构建CityBean
                    CityBean city = new CityBean(tmp, i, lettes[i]);
                    //添加到集合中
                    data.add(city);
                }
            }
        }
        return data;

    }
}
