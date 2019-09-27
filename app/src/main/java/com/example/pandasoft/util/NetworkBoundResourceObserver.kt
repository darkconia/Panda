package com.example.pandasoft.util

import io.reactivex.Observable
import io.reactivex.observers.DefaultObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class NetworkBoundResourceObserver<RequestType, EntityType> constructor(private val appExecutors: AppExecutors,
                                                                        private val callback: Callback<RequestType, EntityType>) {

    init {
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {
        appExecutors.networkIO().execute {
            callback.onCreateObservable().apply {
                timeout(callback.onTimeout(), TimeUnit.SECONDS)
                subscribeOn(Schedulers.newThread())
                subscribe(object : DefaultObserver<Response<RequestType>>() {

                    override fun onComplete() {
                        cancel()
                    }

                    override fun onNext(response: Response<RequestType>) {
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

                    override fun onError(e: Throwable) {
                        when (e) {
                            is NoNetworkException -> // handle 'no network'
                                callback.onNetworkUnavailable(e)
                            is TimeoutException -> // handle timeout
                                callback.onTimeoutException(e)
                            else -> // handle some other error
                                callback.onError(e)
                        }
                    }
                })
            }
        }
    }

    abstract class Callback<RequestType, EntityType> {
        abstract fun onCreateObservable(): Observable<Response<RequestType>>
        abstract fun onResponse(code: Int, message: String, item: RequestType?)
        abstract fun onTimeout(): Long
        abstract fun onUnauthorized()
        abstract fun onError(t: Throwable)
        abstract fun onWarning(t: Throwable)
        abstract fun onNetworkUnavailable(t: NoNetworkException)
        abstract fun onTimeoutException(e: TimeoutException)

    }

}
interface Callback<RequestType, EntityType> {
    fun onResponse(code: Int, message: String, item: RequestType?){}
    fun onUnauthorized(){}
    fun onError(t: Throwable){}
    fun onWarning(t: Throwable){}
    fun onNetworkUnavailable(t: NoNetworkException){}
    fun onTimeoutException(e: TimeoutException){}
}