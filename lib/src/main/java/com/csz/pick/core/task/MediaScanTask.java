package com.csz.pick.core.task;

import android.content.Context;

import com.csz.pick.core.entity.MediaFile;
import com.csz.pick.core.loader.ImageScanner;
import com.csz.pick.core.loader.VideoScanner;
import com.csz.pick.core.util.MediaUtil;

import java.util.ArrayList;

/**
 * @author caishuzhan
 */
public class MediaScanTask implements Runnable {

    private Context mContext;
    private ImageScanner mImageScanner;
    private VideoScanner mVideoScanner;
    private MediaScanCallback mMediaScanCallback;
    private boolean mFilterGif;

    public MediaScanTask(Context context, MediaScanCallback mediaLoadCallback) {
        this.mContext = context;
        this.mMediaScanCallback = mediaLoadCallback;
        mImageScanner = new ImageScanner(context, mFilterGif);
        mVideoScanner = new VideoScanner(context);
    }

    @Override
    public void run() {
        //存放所有照片
        ArrayList<MediaFile> imageFileList = new ArrayList<>();
        //存放所有视频
        ArrayList<MediaFile> videoFileList = new ArrayList<>();

        if (mImageScanner != null) {
            imageFileList = mImageScanner.queryMedia();
        }
        if (mVideoScanner != null) {
            videoFileList = mVideoScanner.queryMedia();
        }

        if (mMediaScanCallback != null) {

            mMediaScanCallback.onLoadMedia(MediaUtil.getMediaFolder(mContext, imageFileList, videoFileList));
        }

    }

    public void filterGif() {
        this.mFilterGif = true;
    }

}

