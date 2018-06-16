package com.zty.yisheng.model.api;

import com.zty.yisheng.model.bean.BannerImageBean;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.ClassListBean;
import com.zty.yisheng.model.bean.CodeBean;
import com.zty.yisheng.model.bean.DocDataBean;
import com.zty.yisheng.model.bean.DocImageBean;
import com.zty.yisheng.model.bean.DocSpecialityBean;
import com.zty.yisheng.model.bean.DocTimeBean;
import com.zty.yisheng.model.bean.ForgetBean;
import com.zty.yisheng.model.bean.KnowledgeBean;
import com.zty.yisheng.model.bean.LabelBean;
import com.zty.yisheng.model.bean.LabelListBean;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.model.bean.RegisterBean;
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

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by 92915 on 2018/3/26.
 */

public interface YiShengService {

    /**
     * 获取Banner图片
     */
    @GET("/travel/banner/select")
    Observable<BannerImageBean> getBannerImageApprove();

    /**
     * 登录接口
     */
    @GET("/CQ120Service.asmx/doclogin")
    Observable<LoginBean> getLoginApprove(@Query("strname")String username,@Query("strpassword")String password);

    /**
     * 获取科室列表
     */
    @GET("/CQ120Service.asmx/getknowledgeclass")
    Observable<ClassBean> getClassApprove();

    /**
     * 获取标签列表
     */
    @GET("/CQ120Service.asmx/getknowledgelabel")
    Observable<LabelBean> getLabelApprove();

    /**
     * 获取科室常识列表
     */
    @GET("/CQ120Service.asmx/getknowledgebyclass")
    Observable<ClassListBean> getClassListApprove(@Query("strclassid")String classid);

    /**
     * 获取标签常识列表
     */
    @GET("/CQ120Service.asmx/getknowledgebylabel")
    Observable<LabelListBean> getLabelListApprove(@Query("strlabelid")String labelid);

    /**
     * 获取具体常识知识
     */
    @GET("/CQ120Service.asmx/getknowledge")
    Observable<KnowledgeBean> getKnowledgeApprove(@Query("strid")String knowledgeid);

    /**
     * 获取医生数据
     */
    @GET("/CQ120Service.asmx/getdocdata")
    Observable<DocDataBean> getDocDataApprove(@Query("strdocid")String docid);

    /**
     * 获取医生头像
     */
    @GET("/CQ120Service.asmx/getdocavatar")
    Observable<DocImageBean> getDocImageApprove(@Query("strdocid")String docid);

    /**
     * 获取医生特长
     */
    @GET("/CQ120Service.asmx/getdocspecialskill")
    Observable<DocSpecialityBean> getDocSpecialityApprove(@Query("strdocid")String docid);

    /**
     * 获取医生工作时间
     */
    @GET("/CQ120Service.asmx/getdoctimeperiod")
    Observable<DocTimeBean> getDocTimeApprove(@Query("strdocid")String docid);

    /**
     * 获取患者数据
     */
    @GET("/CQ120Service.asmx/getuserinfo")
    Observable<SickMessageBean> getSickMessageApprove(@Query("struserid")String sickid);

    /**
     * 更新医生极光
     */
    @GET("/CQ120Service.asmx/docupdatajgid")
    Observable<UpdataJGBean> getUpdataJGApprove(@Query("strdocid")String docid,@Query("strjgid")String jgid);

    /**
     * 更新医生坐标
     */
    @GET("/CQ120Service.asmx/docupdataxy")
    Observable<UpdataXYBean> getUpdataXYApprove(@Query("strdocid")String docid,@Query("strx")String strx,
                                                @Query("stry")String stry);

    /**
     * 修改医生信息
     */
    @GET("/CQ120Service.asmx/setdocdata")
    Observable<SDocDataBean> getSDocDataApprove(@Query("strdocid")String docid,@Query("doctor_name")String docname,
                                                @Query("gender")String sex,@Query("birthday")String birthday,
                                                @Query("mobile")String mobile,@Query("professional")String professional,
                                                @Query("hospital")String hospital,@Query("office")String office);

    /**
     * 修改医生头像
     */
    @GET("/CQ120Service.asmx/setdocavatar")
    Observable<SDocImageBean> getSDocImageApprove(@Query("strdocid")String docid, @Query("imageByte")byte[] docimage);

    /**
     * 修改医生特长
     */
    @GET("/CQ120Service.asmx/setdocspecialskill")
    Observable<SDocSpecialityBean> getSDocSpecialityApprove(@Query("strdocid")String docid,@Query("skills")String skills,
                                                            @Query("specialties")String specialties);

    /**
     * 修改医生工作时间
     */
    @GET("/CQ120Service.asmx/setdoctimeperiod")
    Observable<SDocTimeBean> getSDocTimeApprove(@Query("strdocid")String docid, @Query("begintime")String begintime,
                                                @Query("endtime")String endtime);

    /**
     * 设置打开APP状态
     */
    @GET("/CQ120Service.asmx/setdocopenapp")
    Observable<SDocOpenAppBean> getSDocOpenAppApprove(@Query("strdocid")String docid);

    /**
     * 设置打开APP状态
     */
    @GET("/CQ120Service.asmx/setdocisanswer")
    Observable<SDocIsanswerBean> getSDocIsanswerApprove(@Query("strdocid")String docid, @Query("isanswer")String isanswer);

    /**
     * 获取时间
     */
    @GET("/CQ120Service.asmx/getsyssetting")
    Observable<SysSettingBean> getTimeApprove();

    /**
     * 获取短信验证码
     */
    @GET("/CQ120Service.asmx/docsendsms")
    Observable<CodeBean> getCodeApprove(@Query("strmobile")String docphone);

//    /**
//     * 注册(以后可以使用，添加了code)
//     */
//    @GET("/CQ120Service.asmx/docregistration")
//    Observable<RegisterBean> getRegisterApprove(@Query("strname")String docphone,@Query("strpassword")String password,@Query("strcode")String doccode);

    /**
     * 注册
     */
    @GET("/CQ120Service.asmx/docregistration")
    Observable<RegisterBean> getRegisterApprove(@Query("strname")String docphone,@Query("strpassword")String password);

    /**
     * 忘记密码
     */
    @GET("/CQ120Service.asmx/docmodifypwd")
    Observable<ForgetBean> getForgetApprove(@Query("strname")String docphone,@Query("strpassword")String password,@Query("strcode")String doccode);

}
