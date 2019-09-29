package com.example.pandasoft.ui.news.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("create")
	val create: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("detail")
	val detail: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	)

	override fun writeToParcel(p0: Parcel?, p1: Int) = with(p0){
		this!!.writeString(image!!)
		this!!.writeInt(create!!)
		this!!.writeString(id!!)
		this!!.writeString(detail!!)
		this!!.writeString(title!!)
		this!!.writeString(uuid!!)
    }

    override fun describeContents() = 0

	companion object CREATOR : Parcelable.Creator<DataItem> {
		override fun createFromParcel(parcel: Parcel): DataItem {
			return DataItem(parcel)
		}

		override fun newArray(size: Int): Array<DataItem?> {
			return arrayOfNulls(size)
		}
	}
}