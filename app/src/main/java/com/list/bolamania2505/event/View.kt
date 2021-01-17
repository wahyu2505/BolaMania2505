package com.list.bolamania2505.event

import com.list.bolamania2505.model.Event

interface EventView{
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}