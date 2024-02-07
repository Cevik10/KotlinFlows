package com.hakancevik.flowproject

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MainViewModel : ViewModel() {

    val countDownTimerFlow = flow<Int> {

        val countDownFrom = 10
        var counter = countDownFrom
        emit(countDownFrom)  // we can emit different variables

        while (counter > 0) {
            delay(1000)
            counter--
            emit(counter)
        }
    }


}