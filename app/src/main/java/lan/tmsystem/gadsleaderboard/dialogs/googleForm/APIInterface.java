package lan.tmsystem.gadsleaderboard.dialogs.googleForm;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {
    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<ResponseBody> doSubmitProject(@Field("entry.1824927963") String emailAddress, @Field("entry.1877115667") String Name, @Field("entry.2006916086") String lastName, @Field("entry.284483984") String linkToProject);
}
