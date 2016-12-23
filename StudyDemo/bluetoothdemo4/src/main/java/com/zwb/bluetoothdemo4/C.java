package com.zwb.bluetoothdemo4;

/**
 * Created by zwb
 * Description 定义的一些常量
 * Date 16/11/17.
 */
public interface C {
    /**
     * 用来获取蓝牙传输数据的UUID，通用写法
     */
    String GATT_DESCRIPTOR = "00002902-0000-1000-8000-00805f9b34fb";

    /**
     * 该蓝牙设备的UUID
     */
    String CLIENT_CHARACTERISTIC_CONFIG = "0000fee9-0000-1000-8000-00805f9b34fb";
    /**
     * OTA数据通信UUID
     */
    String OTA_UUID = "0000FEE8-0000-1000-8000-00805F9B34FB";//OTA
}
