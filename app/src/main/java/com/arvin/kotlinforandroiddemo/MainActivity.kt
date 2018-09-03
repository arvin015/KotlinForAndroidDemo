package com.arvin.kotlinforandroiddemo

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var loginName: String by PreferenceWrapper(this, "loginName", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testBtn.setOnClickListener {
            testText.text = testEdit.text
            toast("welcome to kotlin")

//            testImage.load("http://gb.cri.cn/mmsource/images/2007/09/07/ep070907032.jpg")
            load {
                url = "http://gb.cri.cn/mmsource/images/2007/09/07/ep070907032.jpg"
                imageView = testImage
                bitmapType = BitmapType.CIRCLE
            }

            testBtn.isEnabled = false

            loginName = testEdit.text.toString() //保存SharePreference

            //apply运用
            container.addView(Button(this).apply {
                text = "新按鈕"
                textSize = 20f

                layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                    topMargin = 440
                }

                setOnClickListener {
                    startActivity(Intent(this@MainActivity, TestActivity::class.java))
                }
            })

            if (!checkHasPermission(android.Manifest.permission.CAMERA)) {
                getPermissions(arrayOf(android.Manifest.permission.CAMERA), 100)
            }
        }

        choiceBtn.setOnCheckedChangeListener {
            compoundButton, b ->
            kotlin.run {
                //如果是block语句块需要加上kotlin.run{}
                val indexs = (0 until checkboxContainer.childCount)
                indexs
                        .map { checkboxContainer.getChildAt(it) } //转换
                        .filter {
                            //拦截
                            if (b) {
                                !(it as CheckBox).isChecked
                            } else {
                                (it as CheckBox).isChecked
                            }
                        }
                        .forEach { (it as CheckBox).isChecked = b } //遍历
            }
        }

        testEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                Log.d("print", "afterTextChanged---")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("print", "beforeTextChanged---$p0")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("print", "onTextChanged---$p0")
            }
        })

        Log.d("print", "SharePreference---loginName2 = $loginName")

        Thread {
            Log.d("print", "Thread 1 = ${Thread.currentThread().name}")
            runOnUiThread {
                Log.d("print", "Thread 2 = ${Thread.currentThread().name}")
            }
        }.start()

        testEdit.postDelayed({
            Log.d("print", "postDelayed = ${Thread.currentThread().name}")
        }, 2000)

        bubbleSort()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        var isOk = true
        if (requestCode == 100) {
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    isOk = false
                }
            }

            if (isOk) {
                toast("已经获取到相机权限了！")
            } else {
                if (isDenyPermission(android.Manifest.permission.CAMERA)) {
                    toast("用户拒绝了使用相机权限！")
                }
            }
        }
    }

    fun bubbleSort() {
        var nums = intArrayOf(3, 5, 8, 4, 1, 6, 9)
//        nums.sortedArray().forEach { Log.d("print", "$it") } //默认从小到大排序
        nums.sortedArrayDescending().forEach { Log.d("print", "$it") } //从大到小排序
    }
}
