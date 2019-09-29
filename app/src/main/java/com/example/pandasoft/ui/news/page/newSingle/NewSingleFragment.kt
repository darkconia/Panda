package com.example.pandasoft.ui.news.page.newSingle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pandasoft.R
import com.example.pandasoft.ui.news.model.DataItem
import kotlinx.android.synthetic.main.single_news.view.*

class NewSingleFragment : Fragment() {

    private lateinit var rootView: View
    private var data : DataItem? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.single_news, container, false)

        if (arguments != null) {
            data = arguments!!.getParcelable("newsDataSelected")
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBindView()
    }

    fun onBindView(){
        rootView.apply {
            data?.let {data ->
                txt_title.text = data.title
                txt_content.text = data.detail
                txt_date.text = data.create.toString()
                Glide.with(this.context).load(data.image).into(img_news)
            }

        }
    }
}