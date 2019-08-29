package com.gdut.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.LocationSource
import com.amap.api.maps2d.MapView
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.MyLocationStyle
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.core.PoiItem
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import com.gdut.order.R
import com.gdut.order.ui.adapter.AroundRvAdapter

import kotlinx.android.synthetic.main.activity_map_location.*
import org.jetbrains.anko.toast
import java.util.*


class MapLocationActivity : CheckPermissionsActivity(), AMapLocationListener, LocationSource,
    PoiSearch.OnPoiSearchListener {


    override fun onPoiItemSearched(poiItem: PoiItem?, rcode: Int) {

    }

    override fun onPoiSearched(poiResult: PoiResult?, rcode: Int) {
        if (rcode == 1000) {
            if (poiResult != null) {
                val poiItems: ArrayList<PoiItem> = poiResult.pois!!
                adapter.setPoiItemList(poiItems)
                toast("一共有${poiItems.size}个兴趣点")
            }

        }
    }

    private var aMap: AMap? = null
    private var mapView: MapView? = null
    lateinit var mLocationClient: AMapLocationClient
    lateinit var mLocationOption: AMapLocationClientOption
    private var mListener: LocationSource.OnLocationChangedListener? = null
    private var isFirstLoc = true
    lateinit var adapter: AroundRvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_location)
        mapView = findViewById(R.id.map) as MapView
        rv_around.layoutManager = LinearLayoutManager(this)
        adapter = AroundRvAdapter(this)
        rv_around.adapter = adapter

        mapView!!.onCreate(savedInstanceState)
        if (aMap == null) {
            aMap = mapView!!.getMap()
            val settings = aMap!!.getUiSettings()
            aMap!!.setLocationSource(this)//设置了定位的监听,这里要实现LocationSource接口
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true)
            aMap!!.setMyLocationEnabled(true)//显示定位层并且可以触发定位,默认是flase
            val myLocationStyle = MyLocationStyle()
            aMap!!.setMyLocationStyle(myLocationStyle)
            //初始化定位
            mLocationClient = AMapLocationClient(getApplicationContext())
            //设置定位回调监听，这里要实现AMapLocationListener接口，AMapLocationListener接口只有onLocationChanged方法可以实现，用于接收异步返回的定位结果，参数是AMapLocation类型。
            mLocationClient!!.setLocationListener(this)
            //初始化定位参数
            mLocationOption = AMapLocationClientOption()
            //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy)
            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption!!.setNeedAddress(true)
            //设置是否只定位一次,默认为false
            mLocationOption!!.setOnceLocation(false)
            //设置是否强制刷新WIFI，默认为强制刷新
            mLocationOption!!.setWifiScan(true)
            //设置是否允许模拟位置,默认为false，不允许模拟位置
            mLocationOption!!.setMockEnable(false)
            //设置定位间隔,单位毫秒,默认为2000ms
            mLocationOption!!.setInterval(2000)
            //给定位客户端对象设置定位参数
            mLocationClient!!.setLocationOption(mLocationOption)
            //启动定位
            mLocationClient!!.startLocation()
        }
    }


    override fun onLocationChanged(aMapLocation: AMapLocation?) {

        if (aMapLocation != null) {
            if (aMapLocation!!.getErrorCode() === 0) {
                //定位成功回调信息，设置相关消息
                toast(aMapLocation.address)
                doSearchQuery(aMapLocation)

                //停止定位，只需要一次
                mLocationClient?.stopLocation()

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置

                if (isFirstLoc) {
                    //设置缩放级别
                    aMap!!.moveCamera(CameraUpdateFactory.zoomTo(17f))
                    //将地图移动到定位点
                    aMap!!.moveCamera(
                        CameraUpdateFactory.changeLatLng(
                            LatLng(
                                aMapLocation!!.getLatitude(),
                                aMapLocation!!.getLongitude()
                            )
                        )
                    )
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener!!.onLocationChanged(aMapLocation)

                    isFirstLoc = false
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e(
                    "AmapError", "location Error, ErrCode:"
                            + aMapLocation!!.getErrorCode() + ", errInfo:"
                            + aMapLocation!!.getErrorInfo()
                )
                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun doSearchQuery(aMapLocation: AMapLocation) {
        val query = PoiSearch.Query(
            "", "190104|010100|020000|050000|050200|050300|060000|070700|080600|100100|110000|120000|141200|150500",
            aMapLocation.city
        )
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(30) // 设置每页最多返回多少条poiitem
        query.setPageNum(1) //设置查询页码
        val poiSearch = PoiSearch(this, query)
        //搜索范围
        poiSearch.bound =
            PoiSearch.SearchBound(LatLonPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()), 5000)
        poiSearch.setOnPoiSearchListener(this)
        poiSearch.searchPOIAsyn()
    }

    override fun activate(onLocationChangedListener: LocationSource.OnLocationChangedListener) {
        mListener = onLocationChangedListener
    }

    override fun deactivate() {
        mListener = null

    }

    override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView!!.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView!!.onDestroy()
        //mLocationClient.stopLocation();//停止定位
        mLocationClient!!.onDestroy()//销毁定位客户端。

    }


}













