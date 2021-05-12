package com.csz.pick.core.entity;

/**
 * @author caishuzhan
 * 媒体文件
 */
public class MediaFile {


    private String path;  //路径
    private String mime;  //媒体类型
    private Integer folderId; //媒体分类id
    private String folderName; //媒体分类名称
    private long duration; //媒体文件时长
    private long dateToken; //媒体文件创建日期

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDateToken() {
        return dateToken;
    }

    public void setDateToken(long dateToken) {
        this.dateToken = dateToken;
    }
}
