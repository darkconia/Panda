package com.example.pandasoft.ui.news.page.newSingle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pandasoft.R
import com.example.pandasoft.ui.news.model.DataItem
import com.example.pandasoft.util.DateTimeConverter
import kotlinx.android.synthetic.main.single_news.view.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewSingleFragment : Fragment() {

    private lateinit var rootView: View
    private val viewModel: NewSingleViewModel by viewModel()
    private var data : DataItem? = null
    private val dateUtil : DateTimeConverter = get()

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
                txt_date.text = dateUtil.getDateTime(data.create.toString())
                Glide.with(this.context).load(data.image).into(img_news)

                btn_like.apply {
                    setOnClickListener(object : View.OnClickListener{
                        override fun onClick(v: View?) {
                            viewModel.doLike(data.id!!.toInt())
                        }

                    })
                }
            }

        }
    }
}