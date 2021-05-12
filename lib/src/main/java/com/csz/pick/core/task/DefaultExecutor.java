package com.csz.pick.core.task;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import androidx.annotation.NonNull;

/**
 * @author caishuzhan
 * 默认线程池
 */
public class DefaultExecutor {

    private static final int THREAD_COUNT = 3;

    private static volatile DefaultExecutor mCommonExecutor;
    private ExecutorService mExecutorService;

    private DefaultExecutor() {
        mExecutorService = Executors.newFixedThreadPool(THREAD_COUNT,new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("CommonExecutor");
                return thread;
            }
        });
    }

    public static DefaultExecutor getInstance() {
        if (mCommonExecutor == null) {
            synchronized (DefaultExecutor.class) {
                if (mCommonExecutor == null) {
                    mCommonExecutor = new DefaultExecutor();
                }
            }
        }
        return mCommonExecutor;
    }


    public void execute(Runnable runnable) {
        mExecutorService.execute(runnable);
    }

}
