package com.example.sseduproject.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.SectionIndexer
import android.widget.TextView

import com.example.sseduproject.R
import com.example.sseduproject.adapter.SelectAdapter
import com.example.sseduproject.bean.SelectBean
import com.example.sseduproject.tools.NetTools
import com.example.sseduproject.url.Urls
import com.example.sseduproject.view.SortNameListView.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hhkj.highschool.base.BaseActivity
import kotlinx.android.synthetic.main.activity_select_school.*
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.ArrayList

class SelectSchoolActivity : BaseActivity() {

    companion object {
        var callBack: SelectDictionaryCallBack? = null
    }

    fun selectDictionary(activity: Activity, title: String, gradeId: String, classId: String, type: String, callBack: SelectDictionaryCallBack) {
        SelectSchoolActivity.callBack = callBack
        val intent = Intent(activity, SelectSchoolActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("gradeId", gradeId)
        intent.putExtra("classId", classId)
        intent.putExtra("type", type)
        activity.startActivity(intent)
    }

    interface SelectDictionaryCallBack {
        fun getData(bean: SelectBean)
    }

    private var list1: ArrayList<SelectBean>? = null
    private var list2: ArrayList<SelectBean>? = null
    private var list3: ArrayList<SelectBean>? = null

    private var list4: ArrayList<SelectBean>? = null

    private var selectAdapter: SelectAdapter? = null
    private var adapter: SortAdapter? = null

    /**
     * 汉字转换成拼音的类
     */
    private var characterParser: CharacterParser? = null
    private var sidrbarxx: SideBar? = null
    private var sidrbarxk: SideBar? = null

    private var SourceDateList: ArrayList<SortModel>? = null
    private var SourceDateList2: ArrayList<SortModel>? = null
    private var pinyinComparator: PinyinComparator? = null

    private var pos = 0

    private var xyId = "";//学院Id
    private var xyName = "";//学院名称

    private var zyId = "";//专业Id
    private var zyName = "";//专业名称

    private var bean = SelectBean()

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_school)

        setLeftBtn(true)
        if (intent.hasExtra("title")) {
            setTextTitle(intent.getStringExtra("title"))
        }
        net_province()
        initView()
    }

    private fun initView() {
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance()

        pinyinComparator = PinyinComparator()

        sidrbarxx = findViewById<SideBar>(R.id.sidrbarxx) as SideBar
        sidrbarxk = findViewById<SideBar>(R.id.sidrbarxk) as SideBar
//        dialog = findViewById<TextView>(R.id.dialog) as TextView
//        sideBar!!.setTextView(dialog)

        //设置右侧触摸监听
        sidrbarxk!!.setOnTouchingLetterChangedListener { s ->
            //该字母首次出现的位置
            if (adapter != null && s != null && s.isNotEmpty()) {
                val position = adapter!!.getPositionForSection(s[0].toInt())
                if (position != -1) {
                    lv_xk!!.setSelection(position + 1)
                }
            }
        }

        //设置右侧触摸监听
        sidrbarxx!!.setOnTouchingLetterChangedListener { s ->
            //该字母首次出现的位置
            if (adapter != null && s != null && s.isNotEmpty()) {
                val position = adapter!!.getPositionForSection(s[0].toInt())
                if (position != -1) {
                    lv_school!!.setSelection(position + 1)
                }
            }
        }

        et_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.e("zj", "afterTextChanged")
                Log.e("zj", "s = " + s.toString())
                if (s.toString() != "") {
//                    et_name.setText(s)
                    if (xyId == "") {
                        net_target_schools(s.toString(), list1!![pos].code)
                    } else {
                        net_professions(s.toString(), xyId)
                    }

                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.e("zj", "beforeTextChanged")

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.e("zj", "onTextChanged")

            }

        })

        tv_cancle.setOnClickListener {
            et_name.setText("")
//            net_target_schools(list1!![pos].code)
        }


    }

    private fun net_province() {
//        list1 = ArrayList()
//        // 模拟数据
//        for (i in 0 until 20) {
//            val bean = SelectBean()
//            bean.name = "吉林"+i
//            list1!!.add(bean)
//        }
//        selectAdapter = SelectAdapter(this@SelectSchoolActivity,list1!!)
//
//        lv_province.adapter =selectAdapter
//        selectAdapter!!.setIndex(2)
//        lv_province.setOnItemClickListener { parent, view, position, id ->
//            pos = position
//            selectAdapter!!.setIndex(position)
////            net_target_schools(list1!![position].code)
//        }

        NetTools.net("get", Urls.provinces, this) { response ->

            Log.e("zj", "response =" + response.data.toString())

            list1 = Gson().fromJson<ArrayList<SelectBean>>(response.data, object : TypeToken<ArrayList<SelectBean>>() {}.type)
            for (i in 0 until list1!!.size) {
                if ("54" == list1!![i].code){//吉林
                    index = i
                    break
                }
            }
            selectAdapter = SelectAdapter(this@SelectSchoolActivity, list1)

            selectAdapter!!.setIndex(index)

            lv_province.adapter = selectAdapter
            if (!list1!!.isEmpty()) {
                net_target_schools(list1!![index].code)
            }

            lv_province.setOnItemClickListener { parent, view, position, id ->
                pos = position
                selectAdapter!!.setIndex(position)
                net_target_schools(list1!![position].code)
            }
        }
    }

    private fun net_target_schools(province_code: String) {
        val map = HashMap<String, String>()


        map["province_code"] = province_code
        NetTools.net("get", map, Urls.target_schools, this) { response ->

            Log.e("zj", "response =" + response.data.toString())

            list2 = Gson().fromJson<ArrayList<SelectBean>>(response.data, object : TypeToken<ArrayList<SelectBean>>() {}.type)

            initData("1", list2!!)

        }
    }

    private fun net_target_schools(key: String, province_code: String) {
        val map = HashMap<String, String>()
        lv_search.visibility = View.VISIBLE
        if (!key.trim().isEmpty()) {
            map["key"] = key
        }

        map["province_code"] = province_code
        NetTools.net("get", map, Urls.target_schools, this, {

            Log.e("zj", "data = " + it.data)

            list4 = Gson().fromJson<ArrayList<SelectBean>>(it.data, object : TypeToken<ArrayList<SelectBean>>() {}.type)

            selectAdapter = SelectAdapter(this@SelectSchoolActivity, list4)

            lv_search.adapter = selectAdapter

        }, "", false, false)

        lv_search.setOnItemClickListener { parent, view, position, id ->
            lv_search.visibility = View.GONE

            net_professions(list4!![position].code)

            xyId = list4!![position].code
            xyName = list4!![position].name

            ll_xk.visibility = View.VISIBLE
            ll_xx.visibility = View.GONE
            tv_2.text = list4!![position].name
            et_name.setText(list4!![position].name)

            if (xyId != "") {
                et_name.setText("")
                et_name.hint = "输入专业名称"
            }
        }

    }

    private fun net_professions(school_code: String) {
        lv_search.visibility = View.GONE
        val map = HashMap<String, String>()

        map["school_code"] = school_code
        NetTools.net("get", map, Urls.professions, this) { response ->

            Log.e("zj", "professions =" + response.data.toString())

            list3 = Gson().fromJson<ArrayList<SelectBean>>(response.data, object : TypeToken<ArrayList<SelectBean>>() {}.type)
//
//            initData(list2!!)
//
            initData("2", list3!!)


        }
    }

    private fun net_professions(key: String, school_code: String) {
        lv_search.visibility = View.GONE
        val map = HashMap<String, String>()

        if (!key.trim().isEmpty()) {
            map["key"] = key
        }
        map["school_code"] = school_code
        NetTools.net("get", map, Urls.professions, this) { response ->

            Log.e("zj", "professions =" + response.data.toString())

            list3 = Gson().fromJson<ArrayList<SelectBean>>(response.data, object : TypeToken<ArrayList<SelectBean>>() {}.type)
//
//            initData(list2!!)
//
            initData("2", list3!!)


        }
    }

    private fun initData(type: String, beans: ArrayList<SelectBean>) {
        lv_search.visibility = View.GONE
        if (type == "1") {
            SourceDateList = filledData(beans!!)
            // 根据a-z进行排序源数据
            Collections.sort(SourceDateList!!, pinyinComparator)
            // 更新sideBar字母表
            sidrbarxx!!.setXing(getStrs(SourceDateList!!))

            adapter = SortAdapter(this, SourceDateList!!)
            lv_school!!.adapter = adapter

            if (SourceDateList!! != null && SourceDateList!!.isNotEmpty()) {
                sidrbarxx!!.visibility = View.VISIBLE
            } else {
                sidrbarxx!!.visibility = View.GONE
            }

        } else if (type == "2") {
            SourceDateList2 = filledData(beans!!)
            // 根据a-z进行排序源数据
            Collections.sort(SourceDateList2!!, pinyinComparator)
            // 更新sideBar字母表
            sidrbarxk!!.setXing(getStrs(SourceDateList2!!))

            adapter = SortAdapter(this, SourceDateList2!!)
            lv_xk!!.adapter = adapter
            if (SourceDateList2!! != null && SourceDateList2!!.isNotEmpty()) {
                sidrbarxk!!.visibility = View.VISIBLE
            } else {
                sidrbarxk!!.visibility = View.GONE
            }

        }

        lv_school.setOnItemClickListener { parent, view, position, id ->
            ll_xk.visibility = View.VISIBLE
            ll_xx.visibility = View.GONE
            Log.e("zj", "SourceDateList name = " + SourceDateList!![position].name)
            tv_2.text = SourceDateList!![position].name
            net_professions(SourceDateList!![position].id)
            xyName = tv_2.text.toString()
            xyId = SourceDateList!![position].id

            et_name.hint = "输入专业名称"
        }

        lv_xk.setOnItemClickListener { parent, view, position, id ->
            //tiaozhuan

            Log.e("zj", "111111 = " + xyId + "," + xyName)

            zyId = SourceDateList2!![position].other1
            zyName = SourceDateList2!![position].name

            Log.e("zj", "1111112 = " + zyId + "," + zyName)

            bean.xyId = xyId
            bean.xyName = xyName

            bean.zyId = zyId
            bean.zyName = zyName

            callBack!!.getData(bean)
            finish()
        }

        iv_del.setOnClickListener {
            Log.e("zj", "1111")
            ll_xk.visibility = View.GONE
            ll_xx.visibility = View.VISIBLE

            xyName = ""
            xyId = ""

            et_name.hint = "输入学院名称"
        }
    }

    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    private fun filledData(date: java.util.ArrayList<SelectBean>): java.util.ArrayList<SortModel> {
        val mSortList = java.util.ArrayList<SortModel>()
        for (i in date.indices) {
            val sortModel = SortModel()
            sortModel.name = date[i].name
            sortModel.id = date[i].code
            sortModel.other1 = date[i].id
            //汉字转换成拼音
            val pinyinList = if (date[i].name != null && date[i].name!!.length > 10) {
                HanziToPinyin.getInstance().get(date[i].name!!.substring(0, 10))
            } else {
                HanziToPinyin.getInstance().get(date[i].name)
            }
            var pinyin = ""
            for (j in 0 until pinyinList.size) {
                pinyin += pinyinList[j].target
            }

            val sortString = pinyin.substring(0, 1).toUpperCase()
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]".toRegex())) {
                sortModel.setSortLetters(sortString.toUpperCase())
            } else {
                sortModel.setSortLetters("#")
            }
            mSortList.add(sortModel)
        }
        return mSortList

    }

    class SortAdapter(private val mContext: Context, list: List<SortModel>) : BaseAdapter(), SectionIndexer {
        private var list: List<SortModel>? = null

        init {
            this.list = list
        }

        /**
         * 当ListView数据发生变化时,调用此方法来更新ListView
         * @param list
         */
        fun updateListView(list: List<SortModel>) {
            this.list = list
            notifyDataSetChanged()
        }

        override fun getCount(): Int {
            return this.list!!.size
        }

        override fun getItem(position: Int): Any {
            return list!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, view: View?, arg2: ViewGroup): View {
            var view = view
            var viewHolder: ViewHolder? = null
            val mContent = list!![position]
            if (view == null) {
                viewHolder = ViewHolder()
                view = LayoutInflater.from(mContext).inflate(R.layout.item_student, null)
                viewHolder.tv_name = view!!.findViewById(R.id.tv_name)
                viewHolder.tvLetter = view.findViewById(R.id.catalog)
                viewHolder.tv_status = view.findViewById(R.id.tv_status)
                viewHolder.view_line = view.findViewById(R.id.view_line)
                view.tag = viewHolder
            } else {
                viewHolder = view.tag as ViewHolder
            }
            //根据position获取分类的首字母的char ascii值
            val section = getSectionForPosition(position)

//            //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
//            if (position == getPositionForSection(section)) {
//                viewHolder.tvLetter!!.visibility = View.VISIBLE
//                viewHolder.tvLetter!!.text = mContent.sortLetters
//                if (position + 1 <= count - 1) {
//                    if (position + 1 == getPositionForSection(getSectionForPosition(position + 1))) {
//                        viewHolder.view_line!!.visibility = View.GONE
//                    } else {
//                        viewHolder.view_line!!.visibility = View.VISIBLE
//                    }
//                } else {
//                    viewHolder.view_line!!.visibility = View.GONE
//                }
//            } else {
//                viewHolder.tvLetter!!.visibility = View.GONE
//                viewHolder.view_line!!.visibility = View.VISIBLE
//                if (position + 1 <= count - 1) {
//                    if (position + 1 == getPositionForSection(getSectionForPosition(position + 1))) {
//                        viewHolder.view_line!!.visibility = View.GONE
//                    } else {
//                        viewHolder.view_line!!.visibility = View.VISIBLE
//                    }
//                } else {
//                    viewHolder.view_line!!.visibility = View.GONE
//                }
//            }

            viewHolder.tv_name!!.text = list!![position].name

            return view

        }

        internal class ViewHolder {
            var tvLetter: TextView? = null
            var tv_name: TextView? = null
            var tv_status: TextView? = null
            var view_line: View? = null
        }

        /**
         * 根据ListView的当前位置获取分类的首字母的char ascii值
         */
        override fun getSectionForPosition(position: Int): Int {
            return list!![position].sortLetters[0].toInt()
        }

        /**
         * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
         */
        override fun getPositionForSection(section: Int): Int {
            for (i in 0 until count) {
                val sortStr = list!![i].sortLetters
                val firstChar = sortStr.toUpperCase()[0]
                if (firstChar.toInt() == section) {
                    return i
                }
            }

            return -1
        }

        /**
         * 提取英文的首字母，非英文字母用#代替。
         *
         * @param str
         * @return
         */
        private fun getAlpha(str: String): String {
            val sortStr = str.trim { it <= ' ' }.substring(0, 1).toUpperCase()
            // 正则表达式，判断首字母是否是英文字母
            return if (sortStr.matches("[A-Z]".toRegex())) {
                sortStr
            } else {
                "#"
            }
        }

        override fun getSections(): Array<Any>? {
            return null
        }
    }
}
