package com.akokko.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // 获取前端数据和按钮
    val registName by lazy { findViewById<TextView>(R.id.registName) }
    val registPwd by lazy { findViewById<TextView>(R.id.registPwd) }
    val loginName by lazy { findViewById<TextView>(R.id.loginName) }
    val loginPwd by lazy { findViewById<TextView>(R.id.loginPwd) }
    val registButton by lazy { findViewById<Button>(R.id.btn_regist) }
    val loginButton by lazy { findViewById<Button>(R.id.btn_login) }
    val tips by lazy { findViewById<TextView>(R.id.tips) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        regist()
        login()
    }

    // 注册
    private fun regist() {
        // 绑定点击事件
        registButton.setOnClickListener {
            // 获取数据
            val rName: String = registName.text.trim().toString()
            val rPwd: String = registPwd.text.trim().toString()

            // 判断账号密码是否为空
            rName.run { if (isEmpty()) return@setOnClickListener tips.setText("请输入注册账号！") } ?: tips.setText("请输入注册账号！")
            /*if (rName.isNullOrEmpty()) {
                tips.setText("请输入注册账号！")
                return@setOnClickListener
            }*/
            rPwd.run { if ( isEmpty() ) return@setOnClickListener tips.setText("请输入注册密码！") } ?: tips.setText("请输入注册密码！")

            // 获取 SharedPreferences.Editor 对象并存值
            val editor = getSharedPreferences("userData", MODE_PRIVATE).edit()
            editor.putString("name", rName)
            editor.putString("pwd", rPwd)
            editor.commit()

            // 提示注册成功
            tips.setText("注册成功！")
        }
    }

    // 登录
    private fun login() {
        // 绑定点击事件
        loginButton.setOnClickListener {
            // 获取数据
            val lName: String = loginName.text.trim().toString()
            val lPwd: String = loginPwd.text.trim().toString()

            // 判断账号密码是否为空或空字符串
            lName.run { if ( isEmpty() ) return@setOnClickListener tips.setText("请输入登录账号！") } ?: tips.setText("请输入登录账号！")
            lPwd.run { if ( isEmpty() ) return@setOnClickListener tips.setText("请输入登录密码！") } ?: tips.setText("请输入登录密码！")

            // 获取 SharedPreferences 对象并取值
            val sp = getSharedPreferences("userData", MODE_PRIVATE)
            val name = sp.getString("name", "")
            val pwd = sp.getString("pwd", "")

            // 判断账号密码是否正确
            if (lName == name && lPwd == pwd) {
                tips.setText("登录成功！")
            } else {
                tips.setText("账号或密码错误！🙅")
            }
        }
    }
}