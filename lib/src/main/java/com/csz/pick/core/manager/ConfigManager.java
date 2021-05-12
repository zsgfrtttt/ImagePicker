package com.csz.pick.core.manager;


import com.csz.pick.core.util.MediaFileUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * 配置属性
 *
 * @author caishuzhan
 */
public class ConfigManager {

    public static final int SELECT_MODE_SINGLE = 0;//单选
    public static final int SELECT_MODE_MULTI = 1;//多选

    private String title;//标题
    private boolean showCamera;//是否显示拍照Item，默认不显示
    private boolean showImage = true;//是否显示图片，默认显示
    private boolean showVideo = true;//是否显示视频，默认显示
    private boolean filterGif = false;//是否过滤GIF图片，默认不过滤
    private int selectionMode = SELECT_MODE_SINGLE;//选择模式，默认单选
    private int maxCount = 1;//最大选择数量，默认为1
    private boolean singleType;//是否只支持选单类型（图片或者视频）
    private File picDir;//拍照存储路径
    private ArrayList<String> imagePaths;//上一次选择的图片地址集合

    private ImageLoader imageLoader;

    private static volatile ConfigManager mConfigManager;

    private ConfigManager() {
    }

    public static ConfigManager getInstance() {
        if (mConfigManager == null) {
            synchronized (SelectionManager.class) {
                if (mConfigManager == null) {
                    mConfigManager = new ConfigManager();
                }
            }
        }
        return mConfigManager;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShowCamera() {
        return showCamera;
    }

    public void setShowCamera(boolean showCamera) {
        this.showCamera = showCamera;
    }

    public boolean isShowImage() {
        return showImage;
    }

    public void setShowImage(boolean showImage) {
        this.showImage = showImage;
    }

    public boolean isShowVideo() {
        return showVideo;
    }

    public void setShowVideo(boolean showVideo) {
        this.showVideo = showVideo;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        if (maxCount > 1) {
            setSelectionMode(SELECT_MODE_MULTI);
        }
        this.maxCount = maxCount;
    }

    public boolean isFilterGif() {
        return filterGif;
    }

    public void setFilterGif(boolean filterGif) {
        this.filterGif = filterGif;
    }

    public int getSelectionMode() {
        return selectionMode;
    }

    public void setSelectionMode(int mSelectionMode) {
        this.selectionMode = mSelectionMode;
    }

    public ArrayList<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(ArrayList<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public ImageLoader getImageLoader() throws Exception {
        if (imageLoader == null) {
            throw new Exception("imageLoader is null");
        }
        return imageLoader;
    }

    public boolean isSingleType() {
        return singleType;
    }

    public void setSingleType(boolean singleType) {
        this.singleType = singleType;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public void setCachePicDir(File dir){
        picDir = dir;
    }


    public File getCachePicDir() {
        if (picDir != null &&  picDir.exists() && picDir.isDirectory()) {
            return picDir;
        } else {
            return MediaFileUtil.getCachePicDir();
        }
    }

}
