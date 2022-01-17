package org.autojs.autojs.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import com.stardust.util.NetworkUtils;

import org.autojs.autojs.network.api.UpdateCheckApi;
import org.autojs.autojs.network.entity.VersionInfo;
import org.autojs.autojs.tool.SimpleObserver;

import java.util.Date;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Stardust on 2017/9/20.
 */

public class VersionService {
    private static final String KEY_DEPRECATED_VERSION_CODE = "KEY_DEPRECATED_VERSION_CODE";
    private static final VersionService sInstance = new VersionService();
    private VersionInfo mVersionInfo;
    private SharedPreferences mSharedPreferences;
    private final Retrofit mRetrofit;

    public VersionService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://www.autojs.org/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    public static VersionService getInstance() {
        return sInstance;
    }

    public Observable<VersionInfo> checkForUpdates() {
        return mRetrofit.create(UpdateCheckApi.class)
                .checkForUpdates()
                .subscribeOn(Schedulers.io());
    }

    public Observable deviceInfo(String imei,String sfid) {
        return mRetrofit.create(UpdateCheckApi.class)
                .deviceInfo(imei,sfid,new Date().getTime())
                .subscribeOn(Schedulers.io());
    }

    //获取当前版本的一些bug
    public String getCurrentVersionIssues() {
        if (mVersionInfo == null)
            return null;
        return mVersionInfo.description;
    }

    public Observable<VersionInfo> checkForUpdatesIfNeededAndUsingWifi(Context context) {
        if (mVersionInfo == null) {
            return checkUpdateIfUsingWifi(context);
        }
        return Observable.just(mVersionInfo);

    }

    private Observable<VersionInfo> checkUpdateIfUsingWifi(Context context) {
        if (!NetworkUtils.isWifiAvailable(context)) {
            return Observable.empty();
        }
        Observable<VersionInfo> observable = checkForUpdates();
        observable.subscribe(new SimpleObserver<VersionInfo>() {
            @Override
            public void onNext(@NonNull VersionInfo versionInfo) {
                    setVersionInfo(versionInfo);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
            }
        });
        return observable;
    }

    private void setVersionInfo(VersionInfo result) {
        mVersionInfo = result;

    }
}
