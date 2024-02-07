package com.hakancevik.flowproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    init {
        collectInViewModel()
    }


    val countDownTimerFlow = flow<Int> {
        val countDownFrom = 10
        var counter = countDownFrom
        emit(countDownFrom)

        while (counter > 0) {
            delay(1000)
            counter--
            emit(counter)
        }
    }


    private fun collectInViewModel() {
        viewModelScope.launch {
            countDownTimerFlow
                .filter {
                    it % 3 == 0
                }
                .map {
                    it * it
                }
                .collect {
                    println("value: $it")
                }
        }
    }

    private fun collectInViewModel2() {
        countDownTimerFlow.onEach {
            println(it)
        }.launchIn(viewModelScope)
    }


}