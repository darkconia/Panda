package com.example.pandasoft.util

import android.os.Handler
import android.os.Looper

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * How to use:
 *
 * - IO:
 *  AppExecutors.newInstance().io().execute {
 *
 *  }
 *
 * - Disk IO:
 *  AppExecutors.newInstance().diskIO().execute {
 *
 *  }
 *
 * - Network IO:
 *  AppExecutors.newInstance().networkIO().execute {
 *
 *  }
 *
 * - MainThread IO:
 *  AppExecutors.newInstance().mainThread().execute {
 *
 *  }
 */

open class AppExecutors(
    private val diskIO: Executor,
    private val backgroundIO: Executor,
    private val networkIO: Executor,
    private val mainThread: Executor
) {

    constructor() : this(
        Executors.newSingleThreadExecutor(),
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(3),
        MainThreadExecutor()
    )

    companion object {
        @JvmStatic
        fun newInstance(): AppExecutors = AppExecutors()
    }

    fun io(): Executor {
        return backgroundIO
    }

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}