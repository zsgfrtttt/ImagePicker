package com.csz.pick.core.task;

import com.csz.pick.core.entity.MediaFolder;

import java.util.List;

/**
 * 媒体文件扫描回调
 */
public interface MediaScanCallback {

    void onLoadMedia(List<MediaFolder> mediaFolderList);
}
