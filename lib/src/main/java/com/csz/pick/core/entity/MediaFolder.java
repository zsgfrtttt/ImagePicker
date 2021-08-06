package com.csz.pick.core.entity;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * @author caishuzhan
 * 媒体分类
 */
public class MediaFolder {

    private int folderId; //媒体分类id
    private @Nullable String folderName; //媒体分类名称
    private String folderCoverPath; //分类封面图片路径
    private boolean isCheck;
    private ArrayList<MediaFile> mediaFileList; //分类下的子文件
    private Object tag;//标记

    public MediaFolder(int folderId, String folderName, String folderCoverPath, ArrayList<MediaFile> mediaFileList) {
        this.folderId = folderId;
        this.folderName = folderName;
        this.folderCoverPath = folderCoverPath;
        this.mediaFileList = mediaFileList;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderCoverPath() {
        return folderCoverPath;
    }

    public void setFolderCoverPath(String folderCoverPath) {
        this.folderCoverPath = folderCoverPath;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public ArrayList<MediaFile> getMediaFileList() {
        return mediaFileList;
    }

    public void setMediaFileList(ArrayList<MediaFile> mediaFileList) {
        this.mediaFileList = mediaFileList;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "MediaFolder{" +
                "folderId=" + folderId +
                ", folderName='" + folderName + '\'' +
                ", folderCoverPath='" + folderCoverPath + '\'' +
                ", isCheck=" + isCheck +
                ", mediaFileList=" + mediaFileList +
                ", tag=" + tag +
                '}';
    }
}
