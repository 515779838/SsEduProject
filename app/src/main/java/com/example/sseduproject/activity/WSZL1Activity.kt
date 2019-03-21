package com.example.sseduproject.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide

import com.example.sseduproject.R
import com.example.sseduproject.bean.UserInfoBean
import com.example.sseduproject.tools.FileTools
import com.example.sseduproject.tools.MyCountTimer
import com.example.sseduproject.tools.NetTools
import com.example.sseduproject.url.Urls
import com.google.gson.Gson
import com.hhkj.highschool.base.BaseActivity
import kotlinx.android.synthetic.main.activity_wszl1.*
import org.jetbrains.anko.toast
import java.io.FileNotFoundException
import java.io.IOException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

/**
 * 完善资料1
 */
class WSZL1Activity : BaseActivity() {
    private var imgPath = ""

    private var timeCount: MyCountTimer? = null

    private var gender = "m"
    private var demand_tags = "考研"

    private var type = ""

    private var flag1 = true
    private var flag2 = false
    companion object {
        var instance: WSZL1Activity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wszl1)
        setLeftBtn(true)
        setTextTitle("完善资料")
        instance = this
        type = intent.getStringExtra("type")

        if (type == "1"){
            ll_phone.visibility = View.GONE
            ll_code.visibility = View.GONE
        }else{
            ll_phone.visibility = View.VISIBLE
            ll_code.visibility = View.VISIBLE

        }
        setRightBtn(true, "下一步", View.OnClickListener {
            Log.e("zj","aaa = "+flag1 +","+flag2)
            net_update_info()
//            startActivity(Intent(this@WSZL1Activity,WSZL2Activity::class.java))

        })
        onClick()
        timeCount = MyCountTimer(tv_code, 0xffffffff.toInt(), 0xffffffff.toInt())//传入了文字颜色值
        net_user_info()
    }


    private fun onClick() {
        ll_1.setOnClickListener {
            gender = "m"
            v_1.setBackgroundColor(resources.getColor(R.color.color_login_sel))
            v_2.setBackgroundColor(resources.getColor(android.R.color.transparent))
        }
        ll_2.setOnClickListener {
            gender = "f"
            v_2.setBackgroundColor(resources.getColor(R.color.color_login_sel))
            v_1.setBackgroundColor(resources.getColor(android.R.color.transparent))

        }

        ll_3.setOnClickListener {
            demand_tags = "考研"

            if(flag1 == false){
                v_3.setBackgroundColor(resources.getColor(R.color.color_login_sel))
                demand_tags = "考研"
                flag1 = true
            }else{
                v_3.setBackgroundColor(resources.getColor(android.R.color.transparent))
                flag1 = false
            }
//            v_3.setBackgroundColor(resources.getColor(R.color.color_login_sel))
//            v_4.setBackgroundColor(resources.getColor(android.R.color.transparent))
        }
        ll_4.setOnClickListener {
            demand_tags = "四六级"
//            v_4.setBackgroundColor(resources.getColor(R.color.color_login_sel))
//            v_3.setBackgroundColor(resources.getColor(android.R.color.transparent))

            if(flag2 == false){
                v_4.setBackgroundColor(resources.getColor(R.color.color_login_sel))
                flag2 = true
            }else{
                v_4.setBackgroundColor(resources.getColor(android.R.color.transparent))
                flag2 = false
            }

        }

        tv_code.setOnClickListener {
            net_common_verCode()
        }
        iv_img.setOnClickListener {
            showImgDialog()
        }
    }

    private fun showImgDialog() {
        val builder = AlertDialog.Builder(this@WSZL1Activity)
        builder.setItems(arrayOf("相册", "拍照")) { _, p1 ->
            when (p1) {
                0 -> {
                    openSysAlbum()
                }
                1 -> {
                    openSysCamera()
                }
            }
        }
        dialog = builder.create()
        dialog!!.show()
    }

    private fun net_common_verCode() {
        val map = hashMapOf<String, String>()
        map.put("phone", et_name.text.toString())
        NetTools.net("post",map, Urls.captcha, this) { response ->
            timeCount!!.start()
            toast(response.msg!!)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                requestCode_SysAlbum -> {
                    cropPic("zp", data!!.data, 358, 441, 358, 441)
                }
                requestCode_SysCamera -> {
                    try {
                        // 刷新在系统相册中显示
                        MediaStore.Images.Media.insertImage(contentResolver,
                                MediaStore.Images.Media.getBitmap(this.contentResolver, mUriphotoFile),
                                resources.getString(R.string.app_name), "")
                        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                        intent.data = mUriphotoFile
                        sendBroadcast(intent)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    // 三星等手机拍照旋转90度
                    if (readPictureDegree(mStringphotoFile) != 0) {
                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, mUriphotoFile)
                        val bitmap2 = toturn(bitmap)
                        saveBitmapFile(mStringphotoFile, bitmap2)
                    }
                    cropPic("zp", mUriphotoFile, 358, 441, 358, 441)
                }
                requestCode_CropPic -> {
                    getImg()
                }
            }
        }
    }

    private var avatar = ""
    private fun getImg() {
        //将Uri图片转换为Bitmap
        try {
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(mUritempFile))
            //TODO，将裁剪的bitmap显示在imageview控件上
            imgPath = getRealFilePath(mUritempFile)
//            iv_img.setImageBitmap(bitmap)
            // iv_delete.visibility = View.VISIBLE

            Glide.with(this).load(imgPath).apply(RequestOptions.bitmapTransform(CircleCrop())).into(iv_img)


            Log.e("zj","imgPath = "+imgPath)

            var str64 = FileTools.imageToBase64(imgPath)
            avatar = "data:image/jpeg;base64,"+str64
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun net_update_info() {

        if (type == "1"){
            ll_phone.visibility = View.GONE
            ll_code.visibility = View.GONE
        }else{
            ll_phone.visibility = View.VISIBLE
            ll_code.visibility = View.VISIBLE

            if (et_phone.text.isEmpty()){
                et_phone.setHintTextColor(resources.getColor(R.color.color_fe0002))
                toast("手机号不能为空")
                return
            }

            if (et_code.text.isEmpty()){
                et_phone.setHintTextColor(resources.getColor(R.color.color_fe0002))
                toast("验证码不能为空")
                return
            }
        }



        if (flag1 == true && flag2 == true){
            demand_tags = "考研,四六级"
        }else if (flag1 == true && flag2 == false){
            demand_tags = "考研"
        }else if (flag1 == false && flag2 == true){
            demand_tags = "四六级"
        }else if (flag1 == false && flag2 == false){
            toast("需求不能为空")
            return
        }
        val map = hashMapOf<String, String>()
        if (!avatar.startsWith("http")){
            map.put("avatar", avatar)
        }
        Log.e("zj","demand_tags = "+demand_tags)

        map.put("phone", et_phone.text.toString())
        map.put("nickname", et_name.text.toString())
        map.put("captcha", et_code.text.toString())
        map.put("demand_tags", demand_tags)//需求
        map.put("gender",gender)

        map.put("share_code", et_share_code.text.toString())

        NetTools.net("patch",map, Urls.update_info, this) { response ->
            Log.e("zj","response =   "+response.toString())

            startActivity(Intent(this@WSZL1Activity,WSZL2Activity::class.java))
        }
    }

    private fun net_user_info() {

        NetTools.net("get", Urls.user_info, this) { response ->
            Log.e("zj","net_user_info =   "+response.data)
            val resultBean = Gson().fromJson<UserInfoBean>(response.data.toString(), UserInfoBean::class.java!!)
            Log.e("zj","resultBean =   "+resultBean.avatar.url)
            avatar = resultBean.avatar.url

            Log.e("zj","avatar = "+avatar)
            Glide.with(this).load(resultBean.avatar.url+"?"+System.currentTimeMillis()).apply(RequestOptions.bitmapTransform(CircleCrop())).into(iv_img)

            et_name.setText(resultBean.nickname)
            if (resultBean.gender == "m"){
                v_1.setBackgroundColor(resources.getColor(R.color.color_login_sel))
                v_2.setBackgroundColor(resources.getColor(android.R.color.transparent))
            }else if(resultBean.gender == "f"){
                v_2.setBackgroundColor(resources.getColor(R.color.color_login_sel))
                v_1.setBackgroundColor(resources.getColor(android.R.color.transparent))
            }

            et_phone.setText(resultBean.phone)
            for (i in 0 until resultBean.demand_tags.size){

                if (resultBean.demand_tags[i].toString() == "考研"){
                    v_3.setBackgroundColor(resources.getColor(R.color.color_login_sel))
                    flag1 = true
                }
                if(resultBean.demand_tags[i].toString() == "四六级"){
                    v_4.setBackgroundColor(resources.getColor(R.color.color_login_sel))
                    flag2 = true
                }
            }
        }
    }
}
