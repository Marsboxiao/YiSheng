package com.zty.yisheng.ui.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseFragment;
import com.zty.yisheng.common.listener.MyOrientationListener;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.common.utils.SharePreUtil;
import com.zty.yisheng.common.utils.ToastUtil;
import com.zty.yisheng.model.bean.PushBean;
import com.zty.yisheng.model.bean.UpdataXYBean;
import com.zty.yisheng.presenter.contract.MapContract;
import com.zty.yisheng.presenter.presenter.MapPresenter;
import com.zty.yisheng.ui.activity.PushVideoActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 92915 on 2018/4/16.
 */

public class MapFragment extends BaseFragment<MapPresenter> implements MapContract.View {

    @BindView(R.id.common_text)
    TextView commonText;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.map_mapview)
    MapView mapMapview;
    @BindView(R.id.map_location)
    FloatingActionButton mapLocation;
    private BaiduMap baiduMap;
    private LatLng latLng;
    private LocationClient locationClient;
    private boolean isFirstLoc = true;
    private MyOrientationListener myOrientationListener = new MyOrientationListener(YiShengApplication.getInstance());
    public BDLocationListener myListener = new MyLocationListener();
    private float mXDirection=100;
    private MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
    private String docid;

    @Override
    public void showError(String msg) {

    }


    public static MapFragment getInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    protected void initView() {
        commonText.setText("地图");
        docid = SharePreUtil.getPrefString(YiShengApplication.getInstance(), "docid", "111");
        // 获得地图的实例
        isFirstLoc = true;
        // 初始化传感器
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mXDirection=x;
            }
        });
        // 初始化定位
        initMyLocation();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    //配置定位SDK参数
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation
        // .getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        option.setOpenGps(true); // 打开gps

        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        locationClient.setLocOption(option);
    }

    private void initMyLocation() {
        //获取地图控件引用
        baiduMap = mapMapview.getMap();
        //普通地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        baiduMap.setMyLocationEnabled(true);
        //默认显示普通地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        locationClient = new LocationClient(YiShengApplication.getInstance());     //声明LocationClient类
        //配置定位SDK参数
        initLocation();
        locationClient.registerLocationListener(myListener);    //注册监听函数
        //开启定位
        locationClient.start();
        //图片点击事件，回到定位点
        locationClient.requestLocation();
    }

    @OnClick(R.id.map_location)
    public void onViewClicked() {
        if (PushVideoActivity.activity != null) {
            startActivity(new Intent(getContext(), PushVideoActivity.class));
        } else {
            ToastUtil.showShort(getContext(),"当前没有视频通话，请勿点击。");
        }
    }

    @Override
    public void startMap(UpdataXYBean updataXYBean) {

    }

    @Override
    public void againMap(int code) {

    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
            LatLng desLatLng = getGctoBd(latLng);
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mXDirection).latitude(desLatLng.latitude)
                    .longitude(desLatLng.longitude).build();
            // 设置定位数据
            baiduMap.setMyLocationData(locData);
            BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                    .fromResource(R.drawable.navi_map_gps_locked);
            MyLocationConfiguration config = new MyLocationConfiguration(
                    mCurrentMode, true, mCurrentMarker);
            baiduMap.setMyLocationConfiguration(config);
            mPersenter.getUpdataXYAttribute(docid,String.valueOf(location.getLongitude()),String.valueOf(location.getLatitude()));
            // 当不需要定位图层时关闭定位图层
            //mBaiduMap.setMyLocationEnabled(false);
            if (isFirstLoc) {
                isFirstLoc = false;
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(desLatLng).zoom(14.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                Log.d("BaiDuMap",String.valueOf(location.getLongitude())+String.valueOf(location.getLatitude()));
                if (location.getLocType() == BDLocation.TypeGpsLocation) {
                    // GPS定位结果
//                    Toast.makeText(getContext(), location.getAddrStr(), Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    // 网络定位结果
//                    Toast.makeText(getContext(), location.getAddrStr(), Toast.LENGTH_SHORT).show();

                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
                    // 离线定位结果
//                    Toast.makeText(getContext(), location.getAddrStr(), Toast.LENGTH_SHORT).show();

                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    Toast.makeText(getContext(), "服务器错误，请检查", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    Toast.makeText(getContext(), "网络错误，请检查", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    Toast.makeText(getContext(), "手机模式错误，请检查是否飞行", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public LatLng getGctoBd(LatLng latLng) {
        CoordinateConverter converter  = new CoordinateConverter();
        converter.from(CoordinateConverter.CoordType.COMMON);
        // sourceLatLng待转换坐标
        converter.coord(latLng);
        LatLng desLatLng = converter.convert();
        return desLatLng;
    }

    @Override
    protected void initBaidu() {
        SDKInitializer.initialize(getActivity().getApplication());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMapEvent(PushBean pushBean) {
        Double lon = Double.valueOf(pushBean.getData().getStrX());
        Double lat = Double.valueOf(pushBean.getData().getStrY());
        baiduMap.clear();
        setMarker(lat,lon);
    }

    private void setMarker(Double lat,Double lon) {
        //定义Maker坐标点
        LatLng latLng = new LatLng(lat, lon);
        LatLng point = getGctoBd(latLng);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.location);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        baiduMap.addOverlay(option);
    }

    private void center2myLoc() {
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng);
        baiduMap.animateMapStatus(u);
    }

    @Override
    public void onStart()
    {
        // 开启图层定位
        baiduMap.setMyLocationEnabled(true);
        if (!locationClient.isStarted())
        {
            locationClient.start();
        }
        // 开启方向传感器
        myOrientationListener.start();
        super.onStart();
    }

    @Override
    public void onStop()
    {
        // 关闭图层定位
        baiduMap.setMyLocationEnabled(false);
        locationClient.stop();

        // 关闭方向传感器
        myOrientationListener.stop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        // 关闭方向传感器
        mapMapview.onDestroy();
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapMapview.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapMapview.onPause();
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(MapFragment.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_map;
    }

}
