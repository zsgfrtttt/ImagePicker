package com.csz.pick.core.provider;

import android.content.Context;

import androidx.core.content.FileProvider;

/**
 * @author caishuzhan
 */
public class ImagePickerProvider extends FileProvider {

    public static String getFileProviderName(Context context) {
        return context.getPackageName() + ".provider";
    }

}
