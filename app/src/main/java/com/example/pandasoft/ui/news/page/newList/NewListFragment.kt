package com.example.pandasoft.ui.news.page.newList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.pandasoft.R
import com.example.pandasoft.ui.news.model.DataItem
import kotlinx.android.synthetic.main.item_news.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.net.Uri
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.list_news.*
import kotlinx.android.synthetic.main.list_news.view.*


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

        viewModel.newsData.observe(this, Observer {
            oRecyclerViewAdapter?.notifyDataSetChanged()
        })


        rootView.apply {
            val bundle = Bundle()
            val navController = Navigation.findNavController(activity!!, com.example.pandasoft.R.id.my_nav_host_fragment)

            viewModel.newsData.value?.let {
                oRecyclerViewAdapter = RecyclerViewAdapter(context, viewModel.newsData.value!!)
            }

            rv_news.layoutManager = LinearLayoutManager(context)
            rv_news.setHasFixedSize(true)
            rv_news.adapter = oRecyclerViewAdapter

            /*rv_news.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)

                viewModel.newsData.value?.let {
                    oRecyclerViewAdapter = RecyclerViewAdapter(context, viewModel.newsData.value!!)
                }

                oRecyclerViewAdapter.let {
                    adapter = oRecyclerViewAdapter
                }

            }*/

        }
    }

    open class RecyclerViewAdapter(private var context: Context, var data: List<DataItem>) :
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
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

                    val imgUri = Uri.parse(data[position].image)
                    img_news.setImageURI(imgUri)
                }
            }
        }

    }

}