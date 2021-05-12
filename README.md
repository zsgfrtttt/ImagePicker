# Android图片选择器


该项目主要是在* [https://github.com/Lichenwei-Dev/ImagePicker](https://github.com/Lichenwei-Dev/ImagePicker) 的基础上开发，对原项目的一些bug修复以及优化

### 引入依赖 
在Project的build.gradle在添加以下代码
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
在Module的build.gradle在添加以下代码
```
	implementation 'com.github.zsgfrtttt:ImagePicker:1.0.1'
```
**注意：** 依赖迁移至Androidx

### 基本使用

**仅需一行代码**
```java
  ImagePicker.getInstance()
                        .setTitle("标题")//设置标题
                        .showCamera(true)//设置是否显示拍照按钮
                        .showImage(true)//设置是否展示图片
                        .showVideo(true)//设置是否展示视频
                        .filterGif(false)//设置是否过滤gif图片
                        .setSingleType(true)//设置图片视频不能同时选择
                        .setMaxCount(9)//设置最大选择图片数目(默认为1，单选)
                        .setImagePaths(mImageList)//保存上一次选择图片的状态，如果不需要可以忽略
                        .setImageLoader(new GlideLoader())//设置自定义图片加载器
                        .start(MainActivity.this, REQUEST_SELECT_IMAGES_CODE);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
```
如何自定义图片加载器（不定死框架，让框架更加灵活，需要去实现ImageLoader接口即可，如果需要显示视频，优先推荐Glide加载框架，可以参考Demo实现）：
```java
            public class GlideLoader implements ImageLoader {
                //to do something 可以参考Demo用法
                
            }
```
如何获取选中的图片集合：
```java
                @Override
                protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                    if (requestCode == REQUEST_SELECT_IMAGES_CODE && resultCode == RESULT_OK) {
                        List<String> imagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
                    }
                }
```


 

