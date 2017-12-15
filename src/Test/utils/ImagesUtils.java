package utils;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzl on 2017/12/7.
 *
 * @author xzl
 * @date 2017/12/7  16:11.
 */
public class ImagesUtils {

    //按偏量提取图片
    public static void  resetImgColor(File imgFile,int site,String newName){
        //site 按 R =0 G = 1 B =2 取偏量 其余不重写
        List<int[]>  imgList = new ArrayList<>();
        BufferedImage bfImg ;
        int[] rgb = null ;
        try {
            bfImg = ImageIO.read(imgFile);
            int width = bfImg.getWidth();
            int height = bfImg.getHeight();
            int minX = bfImg.getMinX();
            int minY = bfImg.getMinY();
            int alpha = 100;
            // newArray 保存处理后的像素
            int startX = 0;// 开始的横坐标
            int startY = 0;// 开始的纵坐标
            int offset = 0;// 偏移量
            int scansize = width;// 扫描间距
            int dd = width - startX;// 被遍历的宽度间距
            int hh = height - startY;// 被遍历的高度间距
            int x0 = width / 2;// 横向中间点
            int y0 = height / 2;// 纵向中间点
            // rgb的数组，保存像素，用一维数组表示二位图像像素数组
            int[] rgbArray = new int[offset + hh * scansize + dd];// 偏移量+纵向开始坐标*扫描间距+横向开始坐标
            int[] newArray = new int[offset + hh * scansize + dd];// 偏移量+纵向开始坐标*扫描间距+横向开始坐标
            int rgb1 = rgbArray[offset + (y0 - startY) * scansize
                    + (x0 - startX)]; // 位于图片正中央的rgb像素点
            Color c = new Color(rgb1);
            System.out.println("中间像素点的rgb:"+c);
            for (int i = minX; i < width; i++) {
                for (int j = minY; j < height; j++) {
                    rgb = new int[3];
                    int pixel = bfImg.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
                    c = new Color(offset+i * scansize + j);
                    //偏量测试
                    newArray[i*dd + j] = new Color(0, c.getGreen(), 0).getRGB();
                    switch (site){
                        case 0:
                            rgb[2]=rgb[1]=rgb[0] = (pixel & 0xff0000) >> 16;
                            break;
                        case 1:
                            rgb[0]=rgb[2]=rgb[1] = (pixel & 0xff00) >> 8;
                            break;
                        case 2:
                            rgb[0]=rgb[1]=rgb[2] = (pixel & 0xff);
                            break;
                        default:
                            rgb[0] = (pixel & 0xff0000) >> 16;
                            rgb[1] = (pixel & 0xff00) >> 8;
                            rgb[2] = (pixel & 0xff);
                    }
                    System.out.println("i=" + i + ",j=" + j + ":(" + rgb[0] + ","
                            + rgb[1] + "," + rgb[2] + ")");

                    bfImg.setRGB(i, j, c.getGreen());
                    imgList.add(rgb);
                }
            }
            ImageIO.write(bfImg,"jpg",new File(CommonConstants.IMAGE_SAVE_PATH+newName+"old"));

            // 新建一个图像
            File out = new File(CommonConstants.IMAGE_SAVE_PATH+newName);
            if (!out.exists())
                out.createNewFile();
           FileOutputStream output = new FileOutputStream(out);
            BufferedImage imgOut = new BufferedImage(width, height,
                    BufferedImage.TYPE_3BYTE_BGR);
            imgOut.setRGB(startX, startY, width, height, newArray, offset,
                    scansize);
            ImageIO.write(imgOut, "jpg", output);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<int[]> getRGB(File imgFile ){
        List<int[]>  imgList = new ArrayList();
        int[] rgb = null ;
        BufferedImage bfImg ;
        try {
            bfImg = ImageIO.read(imgFile);
            int width = bfImg.getWidth();
            int height = bfImg.getHeight();
            int minX = bfImg.getMinX();
            int minY = bfImg.getMinY();
            for (int i = minX; i < width; i++) {
                for (int j = minY; j < height; j++) {
                    rgb = new int[3];
                    int pixel = bfImg.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
                    rgb[0] = (pixel & 0xff0000) >> 16;
                    rgb[1] = (pixel & 0xff00) >> 8;
                    rgb[2] = (pixel & 0xff);
                    System.out.println("i=" + i + ",j=" + j + ":(" + rgb[0] + ","
                            + rgb[1] + "," + rgb[2] + ")");
                    imgList.add(rgb);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  imgList;
    }
    /**
     * 返回屏幕色彩值
     *
     * @param x
     * @param y
     * @return
     * @throws AWTException
     */
    public int getScreenPixel(int x, int y)  { // 函数返回值为颜色的RGB值。
        Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
        int pixelColor = 0;
        try {
            rb = new Robot();
            Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
            Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
            System.out.println(di.width);
            System.out.println(di.height);
            Rectangle rec = new Rectangle(0, 0, di.width, di.height);
            BufferedImage bi = rb.createScreenCapture(rec);
            pixelColor = bi.getRGB(x, y);

        } catch (AWTException e) {
            e.printStackTrace();
        }
        return 16777216 + pixelColor; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
    }


    public static  File getImageByUrl(String imgUrl,String imageName){
        try {
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            File imageFile = new File(CommonConstants.IMAGE_SAVE_PATH+imageName);
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            byte[] bytes = ReadDataFromText.readInputStream(HttpUtils.getHttpURLConnection(imgUrl,null,null,"get").getInputStream());
            //写入数据
            outStream.write(bytes);
            //关闭输出流
            outStream.close();
//            ImagesUtils.getRGB(imageFile);
            return imageFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Test
    public void test(){
        System.out.println( getScreenPixel(100, 345));
    }
}
