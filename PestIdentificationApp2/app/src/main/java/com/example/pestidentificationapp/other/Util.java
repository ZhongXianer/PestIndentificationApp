package com.example.pestidentificationapp.other;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;

import java.io.File;
import java.util.Calendar;

public class Util {

    public static String SavedListName = "history";
    public static String ShpName = "MySharedPreference";
    private static String[] OfflineLatinName = new String[]{
            "Anoplophora chinensis Forster",
            "Apriona germari",
            "Chalcophora japonica",
            "Clostera anachoreta",
            "Cnidocampa flavescens",
            "Drosicha corpulenta",
            "Erthesina fullo",
            "Hyphantria cunea",
            "Latoria consocia Walker",
            "Micromelalopha troglodyta",
            "Monochamus alternatus",
            "Plagiodera versicolora",
            "Psilogramma menephron",
            "Sericinus montelus Grey",
            "Spilarctia subcarnea"
    };
    private static String[] OfflineName = new String[]{
            "星天牛",
            "桑天牛",
            "日本脊吉丁",
            "杨扇舟蛾",
            "黄刺蛾",
            "草履蚧",
            "麻皮蝽",
            "美国白蛾",
            "褐边绿刺蛾",
            "杨小舟蛾",
            "松墨天牛",
            "柳蓝叶甲",
            "霜天蛾",
            "丝带凤蝶",
            "人纹污灯蛾"
    };

    /**
     * 获取系统当前日期
     *
     * @return 日期 格式例如“2021/7/5”
     */
    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "/" + month + "/" + day;
    }

    /**
     * 获取系统当前时间
     *
     * @return 时间 格式例如“9:20:17”
     */
    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return hour + ":" + minute + ":" + second;
    }

    /**
     * 根据文件名获取图片的bitmap
     *
     * @param fileName 文件名
     * @return 对应的bitmap
     */
    public static Bitmap getDecodeFileBitmap(String fileName) {
        File file = new File(fileName);
        Bitmap bm = null;
        if (file.exists()) {
            bm = BitmapFactory.decodeFile(fileName);
        }
        return bm;
    }

    /**
     * 判断是否为卵或幼虫
     *
     * @param latinName 返回的拉丁学名
     * @return 0 未知卵
     * 1 未知幼虫
     * 2 天牛科幼虫
     * 3 其他幼虫
     * 4 成虫
     */
    public static int judgeLatinName(String latinName) {
        if (latinName.equals("Eggs")) return 0;
        if (latinName.equals("Babys")) return 1;
        String[] splitRes = latinName.split("\\s+");
        int num = splitRes.length;
        if (splitRes[num - 1].equals("baby")) {
            if (num == 2) return 2;
            return 3;
        }
        return 4;
    }

    public static String parseBabyLatinName(String latinName) {
        String[] splitRes = latinName.split("\\s+");
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < splitRes.length; i++) {
            if (i == splitRes.length - 1) break;
            if (i == splitRes.length - 2)
                s.append(splitRes[i]);
            else s.append(splitRes[i]).append(" ");
        }
        return s.toString();
    }


//    public static String parsePercentage(Double percentage) {
//
//    }

    /**
     * 获取离线识别模式的拉丁文名
     *
     * @return 拉丁学名字符串数组
     */
    public static String[] getOfflineLatinName() {
        return OfflineLatinName;
    }

    /**
     * 获取离线识别模式的中文名
     *
     * @return 中文学名字符串数组
     */
    public static String[] getOfflineName() {
        return OfflineName;
    }
}
