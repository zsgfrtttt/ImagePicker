package com.csz.pick.core.task;

import android.content.Context;

import com.csz.pick.core.entity.MediaFile;
import com.csz.pick.core.loader.VideoScanner;
import com.csz.pick.core.util.MediaUtil;

import java.util.ArrayList;

/**
 * @author caishuzhan
 * 视频扫描任务
 */
public class VideoScanTask implements Runnable {

    private Context mContext;
    private VideoScanner mVideoScanner;
    private MediaScanCallback mMediaScanCallback;

    public VideoScanTask(Context context, MediaScanCallback mediaLoadCallback) {
        this.mContext = context;
        this.mMediaScanCallback = mediaLoadCallback;
        mVideoScanner = new VideoScanner(context);
    }

    @Override
    public void run() {

        //存放所有视频
        ArrayList<MediaFile> videoFileList = new ArrayList<>();

        if (mVideoScanner != null) {
            videoFileList = mVideoScanner.queryMedia();
        }

        if (mMediaScanCallback != null) {
            mMediaScanCallback.onLoadMedia(MediaUtil.getVideoFolder(mContext, videoFileList));
        }


    }

}
