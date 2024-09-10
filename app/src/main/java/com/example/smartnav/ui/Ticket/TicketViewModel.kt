package com.example.smartnav.ui.Ticket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TicketViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = null
    }
    val text: LiveData<String> = _text
}