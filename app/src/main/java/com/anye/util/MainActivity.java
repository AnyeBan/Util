package com.anye.util;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anye.util.util.AES;
import com.anye.util.util.ArithUtil;
import com.anye.util.util.DateUtils;
import com.anye.util.util.MD5Util;
import com.anye.util.util.NetUtils;
import com.anye.util.util.NetWorkUtil;
import com.anye.util.util.RSA;
import com.anye.util.util.RegularUtils;
import com.anye.util.util.ScreenUtils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class MainActivity extends AppCompatActivity {
    /***
     * 获取TAG的activity名称
     **/
    protected final String TAG = this.getClass().getSimpleName();
    private String content = "这是内容";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        md5util();//md5加密
//        aesRsa();//aesRsa加密
//        arith();//浮点型进行加减乘除运算
//        dataTime();//时间戳相关的处理
//        regular();//正则验证手机号，邮箱等
//        net();//获取网络状态
//        screen();//获取屏幕信息

    }

    /**
     * 获取屏幕信息
     */
    private void screen() {
        ScreenUtils.getScreenHeight(this);//获取屏幕高度
        ScreenUtils.getScreenWidth(this);//获取屏幕宽度
        Log.e(TAG, "高:" + ScreenUtils.getScreenHeight(this));
        Log.e(TAG, "宽:" + ScreenUtils.getScreenWidth(this));
    }

    /**
     * 获取网络状态
     */
    private void net() {
//        NetUtils.isConnected(this);//网络是否链接
//        NetUtils.isWifi(this);//是否是wifi
//        NetUtils.openSetting(this);//打开设置界面

        NetWorkUtil.getNetWorkType(this);//获取网络类型
        Log.e(TAG, "mNetWorkType:" + NetWorkUtil.getNetWorkType(this));
    }

    /**
     * 正则验证手机号，邮箱等
     */
    private void regular() {
        String mobile = "15513121233";
        RegularUtils.isMobileSimple(mobile);//简单验证是否为手机号
        if (RegularUtils.isMobileSimple(mobile)) {
            Log.e(TAG, "是");
        } else {
            Log.e(TAG, "否");
        }
    }

    /**
     * 时间戳相关的处理
     */
    private void dataTime() {
        DateUtils.getCurrentTime();//获取当前时间戳
        DateUtils.getDateToString(DateUtils.getCurrentTime());//将时间戳转换为字符串
        DateUtils.getStringToDate(DateUtils.getDateToString(DateUtils.getCurrentTime()));//将字符串转换为时间戳
        Log.e(TAG, "" + DateUtils.getCurrentTime());
        Log.e(TAG, DateUtils.getDateToString(DateUtils.getCurrentTime()));
        Log.e(TAG, "" + DateUtils.getStringToDate(DateUtils.getDateToString(DateUtils.getCurrentTime())));
    }

    /**
     * 浮点型进行加减乘除运算
     */
    private void arith() {
        double a = 3.0;
        double b = 4.0;
        ArithUtil.add(a, b);//加法a+b
        ArithUtil.sub(a, b);//a-b
        ArithUtil.mul(a, b);//a*b
        ArithUtil.div(a, b);//a/b
        Log.e(TAG, " " + ArithUtil.add(a, b) + "\n" + ArithUtil.sub(a, b) + "\n" + ArithUtil.mul(a, b) + "\n" + ArithUtil.div(a, b));
    }

    /**
     * AES RSA加密
     */
    private void aesRsa() {
        try {

            String modulus = "124154491807379356527297196116244187398273152668522158815656983660125289855679391879147158541293195275003868038178130285773496115879686500212701890526773738804744344079377128286954164101119976122831174092529201953258836547899462381646328232737663365272773821547834721606029953887435356969122008097322807069021";
            String publicExponent = "65537";
            String privateExponent = "33386975871321803666451263730887475835573162973456070835020160802550545947926513118412518115719177487062768632695226169176997091622033201975193931868196638713298309919409357016591683996259177257354897423241562278633316296652805265316086195554813413654345177218467289505127801198771631206809967423233540896605";

            //加密
            byte[] aesKeyBytes = AES.createKey();//aes创建key
            String key = RSA.encrypt(RSA.getPublicKey(modulus, publicExponent), aesKeyBytes);//RSA公钥加密的key 传到服务器段端
            String text = AES.encrypt(aesKeyBytes, content.getBytes());//aes加密内容 传到服务器段=端
            Log.e(TAG, "aesKeyBytes:" + aesKeyBytes + "\n" + "key:" + key + "\n" + "text:" + text + "\n");

            byte[] decryptBytes = AES.decrypt(aesKeyBytes, text);
            String d = new String(decryptBytes);
            Log.e(TAG, "正确返回" + d);
            //以下是服务器端进行的解密
            //服务器用RSA解码key,然后AES
            byte[] aesKeyBytes2 = RSA.decrypt(RSA.getPrivateKey(modulus, privateExponent), key);
            Log.e(TAG, "aesKeyBytes2:" + aesKeyBytes2);
            byte[] decryptBytes2 = AES.decrypt(aesKeyBytes, text);
            String e = new String(decryptBytes2);
            Log.e(TAG, "正确返回2:" + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * md5加密
     */
    private void md5util() {
        String a = "121";
        String b = MD5Util.string2MD5(a);//转换为32位
        String c = MD5Util.convertMD5(b);//加密
        String d = MD5Util.convertMD5(c);//解密
        Log.e(TAG, "b:" + b + "\n" + "c:" + c + "\n" + "d:" + d + "\n");
    }


}
