package lan.tmsystem.gadsleaderboard.dialogs.googleForm;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class APIClient {
    private static Retrofit sRetrofit = null;

    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        sRetrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return sRetrofit;
    }
}
