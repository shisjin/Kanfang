package kanfang.qf.com.kanfang.inter;

import kanfang.qf.com.kanfang.utils.ApiManager;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/11/21.
 */
public interface CityChoiceInterface {

    @GET(ApiManager.CITY_CHOICE)
    Call<String> getCity();
}
