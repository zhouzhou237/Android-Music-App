package com.example.app.feature.splash

import android.content.IntentSender
import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/*
启动界面VM
 */
class SplashViewModel : ViewModel(){
    private var timer: CountDownTimer? = null
    private val _timeLeft = MutableStateFlow(0L)
    val timeLeft: StateFlow<Long> = _timeLeft


    /*
      是否跳转到主界面
     */
    val navigateToMain = MutableStateFlow(false)

    init {
        delayTONext()
    }

    private fun delayTONext(time: Long = 3000) {
        timer=object : CountDownTimer(time,1000) {
            /*
            每次倒计时执行
             */
            override fun onTick(millisUnitFinished: Long) {
                _timeLeft.value = millisUnitFinished/1000 + 1
            }

            /*
            倒计时结束
             */
            override fun onFinish() {
                toNext()
            }
        }.start()
    }

    private fun toNext() {
        navigateToMain.value = true
    }

}