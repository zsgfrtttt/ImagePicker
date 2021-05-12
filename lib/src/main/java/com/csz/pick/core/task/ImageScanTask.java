package com.csz.pick.core.task;

import android.content.Context;

import com.csz.pick.core.entity.MediaFile;
import com.csz.pick.core.loader.ImageScanner;
import com.csz.pick.core.util.MediaUtil;

import java.util.ArrayList;

/**
 * @author caishuzhan
 * 图片扫描任务
 */
public class ImageScanTask implements Runnable {

    private Context mContext;
    private ImageScanner mImageScanner;
    private MediaScanCallback mMediaScanCallback;
    private boolean mFilterGif;

    public ImageScanTask(Context context, MediaScanCallback mediaLoadCallback) {
        this.mContext = context;
        this.mMediaScanCallback = mediaLoadCallback;
        mImageScanner = new ImageScanner(context,mFilterGif);
    }

    @Override
    public void run() {
        //存放所有照片
        ArrayList<MediaFile> imageFileList = new ArrayList<>();

        if (mImageScanner != null) {
            imageFileList = mImageScanner.queryMedia();
        }

        if (mMediaScanCallback != null) {
            mMediaScanCallback.onLoadMedia(MediaUtil.getImageFolder(mContext, imageFileList));
        }

    }

    public void filterGif() {
        this.mFilterGif = true;
    }

}