package com.example.pandasoft.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

open class CallNetworkBoundResource<RequestType, EntityType> constructor(private val appExecutors: AppExecutors,
                                                                         private val callback: Callback<RequestType, EntityType>) {

    init {
        //on load data from Db setup
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {
        appExecutors.networkIO().execute {
            try {
                callback.onCreateCall().enqueue(object : retrofit2.Callback<RequestType> {
                    override fun onResponse(call: Call<RequestType>?, response: Response<RequestType>) {
                        when (response.isSuccessful) {
                            true -> {
                                val body = response.body()
                                if (body != null) {
                                    //save on Db and query data for retuen
                                    callback.onResponse(response.code(), response.message(), body)
                                } else {
                                    callback.onError(Throwable(response.message()))
                                }
                            }
                            else -> {
                                when (response.code()) {
                                    401 -> callback.onUnauthorized()
                                    else -> {
                                        CallResponseErrorHandler(response, object : CallResponseErrorHandler.OnErrorListener<EntityType> {
                                            override fun onClientError(message: String, errorBody: ResponseErrorBody<EntityType>?) {
                                                var errorMessage = message
                                                if (null != errorBody) {
                                                    errorMessage = errorBody.message
                                                }
                                                callback.onWarning(Throwable(errorMessage))
                                            }

                                            override fun onServerError(message: String, errorBody: ResponseErrorBody<EntityType>?) {
                                                callback.onError(Throwable(message))
                                            }

                                        })
                                    }
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<RequestType>?, e: Throwable) {
                        if (e is NoNetworkException) {
                            // handle 'no network'
                            callback.onNetworkUnavailable(e)
                        } else {
                            // handle some other error
                            callback.onError(e)
                        }
                    }

                })
            } catch (e: Exception) {
                callback.onError(e)
            }
        }
    }

    companion object {
        val LOAD_FROM_DB_DISABLE = false
        val LOAD_FROM_DB_ENABLE = true
    }

    abstract class Callback<RequestType, EntityType> {
        abstract fun onUnauthorized()
        abstract fun onCreateCall(): Call<RequestType>
        abstract fun onResponse(code: Int, message: String, item: RequestType?)
        abstract fun onError(t: Throwable)
        abstract fun onWarning(t: Throwable)
        abstract fun onNetworkUnavailable(t: NoNetworkException)
    }
}

class CallResponseErrorHandler<RequestType, EntityType>(response: Response<RequestType>, listener: OnErrorListener<EntityType>?) {

    init {

        val errorBody = mapToObject(response)

        when (response.code()) {
            in 402..499 -> {
                listener?.onClientError(response.message(), errorBody)
            }
            in 500..599 -> {
                listener?.onServerError(response.message(), errorBody)
            }
            else -> {
                listener?.onClientError("Other", errorBody)
            }
        }
    }

    interface OnErrorListener<T> {
        fun onClientError(message: String, errorBody: ResponseErrorBody<T>?)
        fun onServerError(message: String, errorBody: ResponseErrorBody<T>?)
    }

    private fun mapToObject(response: Response<RequestType>): ResponseErrorBody<EntityType>? {
        return try {
            val objError = JSONObject(response.errorBody()?.string())
            Gson().fromJson<ResponseErrorBody<EntityType>>(objError.toString(), ResponseErrorBody::class.java)
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

}

class ResponseErrorBody<T>(
    @SerializedName("code") var code: String,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: T
)