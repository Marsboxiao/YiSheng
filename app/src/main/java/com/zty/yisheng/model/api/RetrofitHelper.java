package com.zty.yisheng.model.api;

import com.zty.yisheng.BuildConfig;
import com.zty.yisheng.common.support.Constant;
import com.zty.yisheng.common.utils.SystemUtil;
import com.zty.yisheng.model.bean.BannerImageBean;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.ClassListBean;
import com.zty.yisheng.model.bean.DocDataBean;
import com.zty.yisheng.model.bean.DocImageBean;
import com.zty.yisheng.model.bean.DocSpecialityBean;
import com.zty.yisheng.model.bean.DocTimeBean;
import com.zty.yisheng.model.bean.KnowledgeBean;
import com.zty.yisheng.model.bean.LabelBean;
import com.zty.yisheng.model.bean.LabelListBean;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.model.bean.SDocDataBean;
import com.zty.yisheng.model.bean.SDocImageBean;
import com.zty.yisheng.model.bean.SDocIsanswerBean;
import com.zty.yisheng.model.bean.SDocOpenAppBean;
import com.zty.yisheng.model.bean.SDocSpecialityBean;
import com.zty.yisheng.model.bean.SDocTimeBean;
import com.zty.yisheng.model.bean.SickMessageBean;
import com.zty.yisheng.model.bean.SysSettingBean;
import com.zty.yisheng.model.bean.UpdataJGBean;
import com.zty.yisheng.model.bean.UpdataXYBean;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MarsBoxiao on 2017/4/11.
 * 配置文件
 */

public class RetrofitHelper {
    //懒汉式初始化请求配置文件
    private static OkHttpClient okHttpClient = null;
    private static YiShengService mYiShengService=null;

    public RetrofitHelper() {
        initOkhttp();
        mYiShengService = getApiService(Constant.BASE_URL, YiShengService.class);
    }

    //初始化okhttp，配置拦截器。
    private void initOkhttp() {
        //因为我们需要添加两种拦截器，所以调用build模式，一个一个添加比较方便查找修改。
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        //判断是否是debug模式，因为我们主要是为了调试，所以只需要BASIC记录请求响应行就够了。
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        //添加缓存拦截器，由于代码过长，为了界面整洁，我就单分出来一个类。
        File cacheFile = new File(Constant.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //超时设置
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    //勾连api路径
    private <T> T getApiService(String BaseUrl, Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    //默认请求示例
    public Observable<BannerImageBean> getBannerImageApproveInfo() {
        return mYiShengService.getBannerImageApprove();
    }

    /**
     * 登录接口
     */
    public Observable<LoginBean> getLoginApproveInfo(String username,String password){
        return mYiShengService.getLoginApprove(username, password);
    }

    /**
     * 获取科室列表
     */
    public Observable<ClassBean> getClassApproveInfo(){
        return mYiShengService.getClassApprove();
    }

    /**
     * 获取标签列表
     */
    public Observable<LabelBean> getLabelApproveInfo(){
        return mYiShengService.getLabelApprove();
    }

    /**
     * 获取科室常识列表
     */
    public Observable<ClassListBean> getClassListApproveInfo(String classid){
        return mYiShengService.getClassListApprove(classid);
    }

    /**
     * 获取标签常识列表
     */
    public Observable<LabelListBean> getLabelListApproveInfo(String labelid){
        return mYiShengService.getLabelListApprove(labelid);
    }

    /**
     * 获取具体常识知识
     */
    public Observable<KnowledgeBean> getKnowledgeApproveInfo(String knowledgeid){
        return mYiShengService.getKnowledgeApprove(knowledgeid);
    }

    /**
     * 获取医生数据
     */
    public Observable<DocDataBean> getDocDataApproveInfo(String docid){
        return mYiShengService.getDocDataApprove(docid);
    }

    /**
     * 获取医生头像
     */
    public Observable<DocImageBean> getDocImageApproveInfo(String docid){
        return mYiShengService.getDocImageApprove(docid);
    }

    /**
     * 获取医生特长
     */
    public Observable<DocSpecialityBean> getDocSpecialityApproveInfo(String docid){
        return mYiShengService.getDocSpecialityApprove(docid);
    }

    /**
     * 获取医生工作时间
     */
    public Observable<DocTimeBean> getDocTimeApproveInfo(String docid){
        return mYiShengService.getDocTimeApprove(docid);
    }

    /**
     * 获取患者数据
     */
    public Observable<SickMessageBean> getSickMessageApproveInfo(String sickid){
        return mYiShengService.getSickMessageApprove(sickid);
    }

    /**
     * 更新医生极光
     */
    public Observable<UpdataJGBean> getUpdataJGApproveInfo(String docid,String sickid){
        return mYiShengService.getUpdataJGApprove(docid, sickid);
    }

    /**
     * 更新医生坐标
     */
    public Observable<UpdataXYBean> getUpdataXYApproveInfo(String docid,String strx,String stry){
        return mYiShengService.getUpdataXYApprove(docid,strx,stry);
    }

    /**
     * 修改医生信息
     */
    public Observable<SDocDataBean> getSDocDataApproveInfo(String docid,String docname,String sex,String birthday,
                                                           String mobile,String professional, String hospital,String office){
        return mYiShengService.getSDocDataApprove(docid, docname, sex, birthday, mobile, professional, hospital, office);
    }

    /**
     * 修改医生头像
     */
    public Observable<SDocImageBean> getSDocImageApproveInfo(String docid,byte[] docimage){
        return mYiShengService.getSDocImageApprove(docid,docimage);
    }

    /**
     * 修改医生特长
     */
    public Observable<SDocSpecialityBean> getSDocSpecialityApproveInfo(String docid,String skills,String specialties){
        return mYiShengService.getSDocSpecialityApprove(docid, skills, specialties);
    }

    /**
     * 修改医生时间
     */
    public Observable<SDocTimeBean> getSDocTimeApproveInfo(String docid,String begintime,String endtime){
        return mYiShengService.getSDocTimeApprove(docid, begintime, endtime);
    }

    /**
     * 修改医生打开app
     */
    public Observable<SDocOpenAppBean> getSDocOpenAppApproveInfo(String docid){
        return mYiShengService.getSDocOpenAppApprove(docid);
    }

    /**
     * 修改医生接听状态
     */
    public Observable<SDocIsanswerBean> getSDocIsanswerApproveInfo(String docid,String isanswer){
        return mYiShengService.getSDocIsanswerApprove(docid,isanswer);
    }

    /**
     * 修改医生接听状态
     */
    public Observable<SysSettingBean> getTimeApproveInfo(){
        return mYiShengService.getTimeApprove();
    }

}
