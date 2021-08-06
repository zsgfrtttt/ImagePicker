package com.csz.pick.core.util;

import android.content.Context;
import android.util.Log;

import com.csz.pick.core.R;
import com.csz.pick.core.entity.MediaFile;
import com.csz.pick.core.entity.MediaFolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caishuzhan
 */
public class MediaUtil {
    public static final int ALL_MEDIA_FOLDER = -1;//全部媒体
    public static final int ALL_VIDEO_FOLDER = -2;//全部视频

    /**
     * 对查询到的图片进行聚类（相册分类）
     *
     * @param context
     * @param imageFileList
     * @return
     */
    public static List<MediaFolder> getImageFolder(Context context, ArrayList<MediaFile> imageFileList) {
        return getMediaFolder(context, imageFileList, null);
    }

    /**
     * 对查询到的视频进行聚类（相册分类）
     *
     * @param context
     * @param imageFileList
     * @return
     */
    public static List<MediaFolder> getVideoFolder(Context context, ArrayList<MediaFile> imageFileList) {
        return getMediaFolder(context, null, imageFileList);
    }


    /**
     * 对查询到的图片和视频进行聚类（相册分类）
     *
     * @param context
     * @param imageFileList
     * @param videoFileList
     * @return
     */
    public static List<MediaFolder> getMediaFolder(Context context, ArrayList<MediaFile> imageFileList, ArrayList<MediaFile> videoFileList) {

        //根据媒体所在文件夹Id进行聚类（相册）
        Map<Integer, MediaFolder> mediaFolderMap = new HashMap<>();

        //全部图片、视频文件
        ArrayList<MediaFile> mediaFileList = new ArrayList<>();
        if (imageFileList != null) {
            mediaFileList.addAll(imageFileList);
        }
        if (videoFileList != null) {
            mediaFileList.addAll(videoFileList);
        }

        //对媒体数据进行排序
        Collections.sort(mediaFileList, new Comparator<MediaFile>() {
            @Override
            public int compare(MediaFile o1, MediaFile o2) {
                if (o1.getDateToken() > o2.getDateToken()) {
                    return -1;
                } else if (o1.getDateToken() < o2.getDateToken()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        //全部图片或视频
        if (!mediaFileList.isEmpty()) {
            MediaFolder allMediaFolder = new MediaFolder(ALL_MEDIA_FOLDER, context.getString(R.string.all_media), mediaFileList.get(0).getPath(), mediaFileList);
            allMediaFolder.setTag(String.valueOf(ALL_MEDIA_FOLDER));
            if (!(imageFileList == null && videoFileList != null)) { // 非只显示视频
                mediaFolderMap.put(ALL_MEDIA_FOLDER, allMediaFolder);
            }
        }

        //全部视频
        if (videoFileList != null && !videoFileList.isEmpty()) {
            MediaFolder allVideoFolder = new MediaFolder(ALL_VIDEO_FOLDER, context.getString(R.string.all_video), videoFileList.get(0).getPath(), videoFileList);
            allVideoFolder.setTag(String.valueOf(ALL_VIDEO_FOLDER));
            mediaFolderMap.put(ALL_VIDEO_FOLDER, allVideoFolder);
        }

        //对图片进行文件夹分类
        if (imageFileList != null && !imageFileList.isEmpty()) {
            int size = imageFileList.size();
            //添加其他的图片文件夹
            for (int i = 0; i < size; i++) {
                MediaFile mediaFile = imageFileList.get(i);
                int imageFolderId = mediaFile.getFolderId();
                MediaFolder mediaFolder = mediaFolderMap.get(imageFolderId);
                if (mediaFolder == null) {
                    mediaFolder = new MediaFolder(imageFolderId, mediaFile.getFolderName(), mediaFile.getPath(), new ArrayList<MediaFile>());
                    mediaFolderMap.put(imageFolderId, mediaFolder);
                }
                ArrayList<MediaFile> imageList = mediaFolder.getMediaFileList();
                imageList.add(mediaFile);
            }
        }

        //整理聚类数据
        List<MediaFolder> mediaFolderList = new ArrayList<>();
        for (Integer folderId : mediaFolderMap.keySet()) {
            mediaFolderList.add(mediaFolderMap.get(folderId));
        }
        //按照图片文件夹的数量排序
        Collections.sort(mediaFolderList, new Comparator<MediaFolder>() {
            @Override
            public int compare(MediaFolder o1, MediaFolder o2) {
                if(o1.getTag() == null || o2.getTag() == null) return 0;
                if (String.valueOf(ALL_MEDIA_FOLDER).equals(o1.getTag())) {
                    return -1;
                }
                if (String.valueOf(ALL_MEDIA_FOLDER).equals(o2.getTag())) {
                    return 1;
                }
                if (String.valueOf(ALL_VIDEO_FOLDER).equals(o1.getTag())) {
                    return -1;
                }
                if (String.valueOf(ALL_VIDEO_FOLDER).equals(o2.getTag())) {
                    return 1;
                }
                if (o1.getFolderName() != null && o2.getFolderName() != null) {
                    return o1.getFolderName().compareTo(o2.getFolderName());
                }
                if (o1.getFolderName() != null) {
                    return 1;
                }
                if (o2.getFolderName() != null) {
                    return 1;
                }
                return o1.getMediaFileList().size() - o2.getMediaFileList().size();
            }
        });


        return mediaFolderList;
    }
}
