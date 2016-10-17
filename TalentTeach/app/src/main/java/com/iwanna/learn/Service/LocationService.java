package com.iwanna.learn.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.iwanna.learn.MyApplication;

/**
 * 定位服务
 */
public class LocationService extends Service {
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    public LocationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initLocation();
        return super.onStartCommand(intent, flags, startId);
    }
    private void initLocation(){
        if(mlocationClient == null && mLocationOption == null) {
            mlocationClient = new AMapLocationClient(getApplicationContext());
            mLocationOption = new AMapLocationClientOption();
            mlocationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation amapLocation) {
                    if (amapLocation != null
                            && amapLocation.getErrorCode() == 0) {
                        MyApplication.Latitude = amapLocation.getLatitude();
                        MyApplication.Longitude = amapLocation.getLongitude();
                        Log.e("AmapErr", "=====getLatitude======" + amapLocation.getLatitude());
                        Log.e("AmapErr", "=====getLongitude======" + amapLocation.getLongitude());
                    } else {
                        String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                        Log.e("AmapErr", errText);
//                                    tvAddress.setVisibility(View.GONE);
                    }
                }
            });
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            mLocationOption.setInterval(30 * 1000);
            mLocationOption.setOnceLocationLatest(false);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
    }
}
