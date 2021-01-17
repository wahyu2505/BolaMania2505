import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.list.bolamania2505.R
import com.list.bolamania2505.api.ApiRepository
import com.list.bolamania2505.event.EventAdapter
import com.list.bolamania2505.event.EventView
import com.list.bolamania2505.model.Event
import com.list.bolamania2505.util.invisible
import com.list.bolamania2505.util.visible

class Fragment : Fragment(), EventView {

    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: Presenter
    private lateinit var adapter: EventAdapter
    var event: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_event, container, false)
        listEvent = view.findViewById(R.id.list_event)
        progressBar = view.findViewById(R.id.progress_bar)
        swipeRefresh = view.findViewById(R.id.swipe_refresh)
        event = arguments.getString("event")

        listEvent.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = Presenter(this, request, gson)
        presenter.getEventList("4328", event)
        swipeRefresh.onRefresh {
            presenter.getEventList("4328",event)
        }
        return view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(event: String?): Fragment {
            val fragment = Fragment()
            val args = Bundle()
            args.putString("event",event)
            fragment.arguments = args
            return fragment
        }
    }
}