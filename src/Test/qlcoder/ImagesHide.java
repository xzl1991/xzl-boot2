package qlcoder;

import utils.CommonConstants;
import utils.HttpUtils;
import utils.ImagesUtils;
import utils.ReadDataFromText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static utils.CommonConstants.IMAGE_SAVE_PATH;

/**
 * Created by xzl on 2017/12/7.
 *
 * @author xzl
 * @date 2017/12/7  16:30.
 */
public class ImagesHide {
    public static void main(String[] args) {
        try {
//            ImagesUtils.getRGB(ImagesUtils.getImageByUrl("http://www.qlcoder.com/uploads/61244.jpg","BeautyGirl2.jpg"));

            //处理分量图片
            ImagesUtils.resetImgColor(ImagesUtils.getImageByUrl("http://www.qlcoder.com/uploads/145303100168558.png","fenliang/原图.jpg"),1,"fenliang/绿色分量.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
