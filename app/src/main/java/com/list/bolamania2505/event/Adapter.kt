package com.list.bolamania2505.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.list.bolamania2505.R
import kotlinx.android.synthetic.main.item_event.view.*
import java.text.SimpleDateFormat

class EventAdapter(private val context: Context, private val events: List<Event>, private val listener: (Event) -> Unit)
    : RecyclerView.Adapter<EventViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    fun notifyDataSetChanged() {
        TODO("Not yet implemented")
    }

}

class EventViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bindItem(events: Event, listener: (Event) -> Unit){
        if(events.scoreHome.isNullOrEmpty() && events.scoreAway.isNullOrEmpty()){
            itemView.tv_skor.text = "VS"
        }else{
            itemView.tv_skor.text = events.scoreHome+" VS "+events.scoreAway
        }
        var tanggalBaru = SimpleDateFormat("EEE, d MMM yyyy")
            .format(SimpleDateFormat("yyyy-MM-dd")
                .parse(events.eventDate))
        itemView.tv_date.text = tanggalBaru
        itemView.tv_home.text = events.teamHome
        itemView.tv_away.text = events.teamAway
        itemView.onClick1 { listener(events) }
    }
}