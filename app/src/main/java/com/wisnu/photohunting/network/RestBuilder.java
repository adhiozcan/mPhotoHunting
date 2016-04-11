package com.wisnu.photohunting.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestBuilder {
    private static OkHttpClient     httpClient;
    private static Retrofit.Builder builder;

    /**
     * Create Retrofit.Builder instance
     */
    private static void setupBuilder() {
        builder = new Retrofit.Builder()
                .baseUrl(Request.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
    }

    /**
     * Set Configuration for client. Will be attach to Retrofit.Builder instance
     */
    private static void attachPlugin() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        httpClient = new OkHttpClient();
        httpClient.interceptors().add(logging);
    }

    /**
     * Establish new Request through this method.
     * This method is coupled more with RestRequest.class intensively.
     *
     * @param serviceClass
     * @param <E>
     * @return retrofit.create
     */
    public static <E> E openService(Class<E> serviceClass) {
        setupBuilder();
        attachPlugin();

        Retrofit retrofit = builder.client(httpClient).build();

        return retrofit.create(serviceClass);
    }
}
