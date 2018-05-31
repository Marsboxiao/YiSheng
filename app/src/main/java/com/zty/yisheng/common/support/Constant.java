package com.zty.yisheng.common.support;

import java.io.File;

/**
 * Created by 92915 on 2018/3/26.
 */

public class Constant {

    //默认请求类示例
    public static final String SampleUrl="http://www.suiuxing.com";

    //数据路径
    public static final String PATH_DATA = YiShengApplication.getInstance().getCacheDir().getAbsolutePath() +
            File.separator + "data";

    //缓存路径
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    //120项目请求路径
    public static final String BASE_URL="http://120.91tele.cn:12011";

}
