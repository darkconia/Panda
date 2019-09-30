package com.example.pandasoft.ui.news.page.newList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.pandasoft.R
import com.example.pandasoft.ui.news.model.DataItem
import kotlinx.android.synthetic.main.item_news.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_news.*
import com.example.pandasoft.util.OnItemClickListener


class NewListFragment : Fragment(){

    private lateinit var rootView: View
    private val viewModel: NewListViewModel by viewModel()
    private var oRecyclerViewAdapter: RecyclerViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.list_news, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews()
        onBindView()
    }

    private fun onBindView() {

        val bundle = Bundle()
        val navController = Navigation.findNavController(activity!!, com.example.pandasoft.R.id.my_nav_host_fragment)

        rv_news.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true)
            setHasFixedSize(true)
            oRecyclerViewAdapter = RecyclerViewAdapter(context!!, ArrayList()).apply {
                setOnItemClickListener(object : OnItemClickListener<DataItem>{
                    override fun onClick(v: View, data: DataItem) {
                        viewModel.newsDataSelected.postValue(data)
                    }
                })
            }
            oRecyclerViewAdapter
            adapter = oRecyclerViewAdapter

        }


        viewModel.newsData.observe(this, Observer {
            oRecyclerViewAdapter?.data = it
            oRecyclerViewAdapter?.notifyDataSetChanged()
        })

        viewModel.newsDataSelected.observe(this , Observer {
            bundle.putParcelable("newsDataSelected" ,it)
            navController.navigate(R.id.action_newsListFragment_to_newsSingleFragment, bundle)
        })

        viewModel.noNewsData.observe(this, Observer {
            Toast.makeText(context ,"no news data" , Toast.LENGTH_SHORT).show()
        })

    }

    open class RecyclerViewAdapter(private var context: Context, var data: List<DataItem>) :
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

        private var onItemClickListener: OnItemClickListener<DataItem>? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
            holder.bindView(position)
        }

        inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            fun bindView(position: Int) {
                view.apply {
                    txt_title.text = data[position].title
                    Glide.with(this.context).load(data[position].image).into(img_news)
                    setOnClickListener {
                        onItemClickListener?.onClick(it, data[position])
                    }
                }
            }
        }

        fun setOnItemClickListener(onItemClickListener: OnItemClickListener<DataItem>) {
            this.onItemClickListener = onItemClickListener
        }

    }

}