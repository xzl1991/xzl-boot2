//package dataStruct;
//
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.Transparency;
//import java.awt.font.FontRenderContext;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Rectangle2D;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import javax.imageio.ImageIO;
//import org.im4java.core.CompositeCmd;
//import org.im4java.core.ConvertCmd;
//import org.im4java.core.GMOperation;
//import org.im4java.core.IM4JavaException;
//import org.im4java.core.IMOperation;
//
//public class GMUtil {
//
//	/**
//	 * GraphicsMagick的安装目录 windows
//	 */
//	private static final String graphicsMagickPath = "D:\\GM\\GraphicsMagick-1.3.18-Q8";
//	String osName = System.getProperty("os.name").toLowerCase();
//
//	/**
//	 * 按九宫格位置添加水印
//	 *
//	 * @param srcPath
//	 *            原图片路径
//	 * @param distPath
//	 *            新图片路径
//	 * @param watermarkImg
//	 *            水印图片路径
//	 * @param position
//	 *            九宫格位置[1-9],从上往下,从左到右排序
//	 * @param x
//	 *            横向边距
//	 * @param y
//	 *            纵向边距
//	 * @param alpha
//	 *            透明度
//	 * @throws IOException
//	 * @throws InterruptedException
//	 * @throws IM4JavaException
//	 */
//	public void watermarkImg(String srcPath, String distPath, String watermarkImg, int position, int x, int y,
//			int alpha) throws IOException, InterruptedException, IM4JavaException {
//		int[] watermarkImgSide = getImageSize(watermarkImg);
//		int[] srcImgSide = getImageSize(srcPath);
//		int[] xy = getXY(srcImgSide, watermarkImgSide, position, x, y);
//		addWatermarkToImg(srcPath, distPath, watermarkImg, watermarkImgSide[0], watermarkImgSide[1], xy[0], xy[1],
//				alpha);
//	}
//
//	/**
//	 * 获取图片尺寸
//	 *
//	 * @param imgPath
//	 * @return
//	 * @throws IOException
//	 */
//	public boolean isImage(String imgPath) {
//		Image img = null;
//		try {
//			img = ImageIO.read(new File(imgPath));
//			if(img==null){
//				return false;
//			}
//			return true;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//
//	}
//
//	/**
//	 * 获取图片尺寸
//	 *
//	 * @param imgPath
//	 * @return
//	 * @throws IOException
//	 */
//	public int[] getImageSize(String imgPath) throws IOException {
//		int[] size = new int[2];
//		Image img = ImageIO.read(new File(imgPath));
//
//		size[0] = img.getWidth(null);
//		size[1] = img.getHeight(null);
//		return size;
//	}
//
//	private int[] getXY(int[] image, int[] watermark, int position, int x, int y) {
//		int[] xy = new int[2];
//		if (position == 1) {
//			xy[0] = x;
//			xy[1] = y;
//		} else if (position == 2) {
//			xy[0] = (image[0] - watermark[0]) / 2; // 横向边距
//			xy[1] = y; // 纵向边距
//		} else if (position == 3) {
//			xy[0] = image[0] - watermark[0] - x;
//			xy[1] = y;
//		} else if (position == 4) {
//			xy[0] = x;
//			xy[1] = (image[1] - watermark[1]) / 2;
//		} else if (position == 5) {
//			xy[0] = (image[0] - watermark[0]) / 2;
//			xy[1] = (image[1] - watermark[1]) / 2;
//		} else if (position == 6) {
//			xy[0] = image[0] - watermark[0] - x;
//			xy[1] = (image[1] - watermark[1]) / 2;
//		} else if (position == 7) {
//			xy[0] = x;
//			xy[1] = image[1] - watermark[1] - y;
//		} else if (position == 8) {
//			xy[0] = (image[0] - watermark[0]) / 2;
//			xy[1] = image[1] - watermark[1] - y;
//		} else {
//			xy[0] = image[0] - watermark[0] - x;
//			xy[1] = image[1] - watermark[1] - y;
//		}
//		return xy;
//	}
//
//	/**
//	 * 添加图片水印
//	 *
//	 * @param srcPath
//	 *            原图片路径
//	 * @param distPath
//	 *            新图片路径
//	 * @param watermarkImg
//	 *            水印图片路径
//	 * @param width
//	 *            水印宽度（可以于水印图片大小不同）
//	 * @param height
//	 *            水印高度（可以于水印图片大小不同）
//	 * @param x
//	 *            水印开始X坐标
//	 * @param y
//	 *            水印开始Y坐标
//	 * @param alpha
//	 *            透明度[0-100]
//	 * @throws IOException
//	 * @throws InterruptedException
//	 * @throws IM4JavaException
//	 */
//	private void addWatermarkToImg(String srcPath, String distPath, String watermarkImg, int width, int height, int x,
//			int y, int alpha) throws IOException, InterruptedException, IM4JavaException {
//		CompositeCmd cmd = new CompositeCmd(true);
//		// cmd.setSearchPath(graphicsMagickPath);
//		IMOperation op = new IMOperation();
//
//		op.dissolve(alpha);
//		op.geometry(width, height, x, y);
//		op.addImage(watermarkImg, srcPath, distPath);
//		cmd.run(op);
//	}
//
//	/**
//	 * 把文字转化为一张背景透明的png图片
//	 *
//	 * @param str
//	 *            文字的内容
//	 * @param fontType
//	 *            字体，例如宋体
//	 * @param fontSize
//	 *            字体大小
//	 * @param colorStr
//	 *            字体颜色，不带#号，例如"990033"
//	 * @param outfile
//	 *            png图片的路径
//	 * @throws Exception
//	 */
//	public void converFontToImage(String str, String fontType, int fontSize, String colorStr, String outfile)
//			throws Exception {
//		Font font = new Font(fontType, Font.BOLD, fontSize);
//		// 获取font的样式应用在str上的整个矩形
//		Rectangle2D r = font.getStringBounds(str,
//				new FontRenderContext(AffineTransform.getScaleInstance(1, 1), false, false));
//		int unitHeight = (int) Math.floor(r.getHeight());// 获取单个字符的高度
//		// 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
//		int width = (int) Math.round(r.getWidth()) + 1;
//		int height = unitHeight + 3;// 把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
//		// 创建图片
//		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		Graphics2D g2d = image.createGraphics();
//		image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
//		g2d.dispose();
//		g2d = image.createGraphics();
//		g2d.setColor(Color.WHITE);
//		g2d.setStroke(new BasicStroke(1));
//		g2d.setColor(new Color(Integer.parseInt(colorStr, 16)));// 在换成所需要的字体颜色
//		g2d.setFont(font);
//		g2d.drawString(str, 0, font.getSize());
//
//		File file = new File(outfile);
//		ImageIO.write(image, "png", file);// 输出png图片
//	}
//
//	/**
//	 * 根据坐标裁剪图片
//	 *
//	 * @param srcPath
//	 *            要裁剪图片的路径
//	 * @param newPath
//	 *            裁剪图片后的路径
//	 * @param x
//	 *            起始横坐标
//	 * @param y
//	 *            起始纵坐标
//	 * @param x1
//	 *            结束横坐标
//	 * @param y1
//	 *            结束纵坐标
//	 */
//	public void cutImage(String srcPath, String newPath, int x, int y, int x1, int y1) throws Exception {
//		int width = x1 - x;
//		int height = y1 - y;
//		IMOperation op = new IMOperation();
//		op.addImage(srcPath);
//		/** width：裁剪的宽度 * height：裁剪的高度 * x：裁剪的横坐标 * y：裁剪纵坐标 */
//		op.crop(width, height, x, y);
//		op.addImage(newPath);
//		ConvertCmd convert = new ConvertCmd(true);
//		if (osName.indexOf("win") != -1) {
//			// linux下不要设置此值，不然会报错
//			convert.setSearchPath(graphicsMagickPath);
//		}
//		convert.run(op);
//	}
//
//	/**
//	 * 根据尺寸缩放图片
//	 *
//	 * @param width
//	 *            缩放后的图片宽度
//	 * @param height
//	 *            缩放后的图片高度
//	 * @param srcPath
//	 *            源图片路径
//	 * @param newPath
//	 *            缩放后图片的路径
//	 * @param sharpenNum
//	 *            锐化数值
//	 */
//	public void zoomImage(Integer width, Integer height, String srcPath, String newPath) throws Exception {
//		IMOperation op = new IMOperation();
//		op.addImage(srcPath);
//		if (width == null) {// 根据高度缩放图片
//			op.resize(null, height);
//		} else if (height == null) {// 根据宽度缩放图片
//			op.resize(width, null);
//		} else {
//			op.resize(width, height);
//		}
//		// op.modulate(200.0,100.0);//亮度
//		// if(sharpenNum!=null){
//		// op.sharpen(sharpenNum);//锐化
//		// }
//		op.addImage(newPath);
//		ConvertCmd convert = new ConvertCmd(true);
//		if (osName.indexOf("win") != -1) {
//			// linux下不要设置此值，不然会报错
//			convert.setSearchPath(graphicsMagickPath);
//		}
//		convert.run(op);
//	}
//
//	/**
//	 * 给图片加文字水印 可以加英文水印，中文会报错或者乱码，变通方法是将文字生成图片，然后加水印
//	 *
//	 * @param srcPath
//	 * @param content
//	 * @throws Exception
//	 */
//	public void addImgText(String srcPath, String content) throws Exception {
//		// IMOperation op = new IMOperation();
//		GMOperation op = new GMOperation();
//		op.font("Vrinda");
//		op.gravity("southeast");
//		op.pointsize(38).fill("#000000").draw("text 10,10 " + new String(content.getBytes("utf-8"), "gbk")); // ("x1
//																												// x2
//																												// x3
//																												// x4")
//																												// x1
//																												// 格式，x2
//																												// x轴距离
//																												// x3
//																												// y轴距离
//																												// x4名称，文字内容
//		op.addImage();
//		op.addImage();
//		ConvertCmd convert = new ConvertCmd(true);
//		if (osName.indexOf("win") != -1) {
//			// linux下不要设置此值，不然会报错
//			convert.setSearchPath(graphicsMagickPath);
//		}
//		try {
//			convert.run(op, srcPath, srcPath);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 图片旋转
//	 *
//	 * @param srcImagePath
//	 * @param destImagePath
//	 * @param angle
//	 */
//	public void rotate(String srcImagePath, String destImagePath, double angle) {
//		try {
//			IMOperation op = new IMOperation();
//			op.rotate(angle);
//			op.addImage(srcImagePath);
//			op.addImage(destImagePath);
//			ConvertCmd cmd = new ConvertCmd(true);
//			if (osName.indexOf("win") != -1) {
//				// linux下不要设置此值，不然会报错
//				cmd.setSearchPath(graphicsMagickPath);
//			}
//			cmd.run(op);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 图片合成
//	 *
//	 * @param args
//	 * @param maxWidth
//	 * @param maxHeight
//	 * @param newpath
//	 * @param mrg
//	 * @param type
//	 *            1:横,2:竖
//	 */
//	public void montage(String[] args, Integer maxWidth, Integer maxHeight, String newpath, Integer mrg, String type) {
//		IMOperation op = new IMOperation();
//		ConvertCmd cmd = new ConvertCmd(true);
//		if (osName.indexOf("win") != -1) {
//			// linux下不要设置此值，不然会报错
//			cmd.setSearchPath(graphicsMagickPath);
//		}
//		String thumb_size = maxWidth + "x" + maxHeight + "^";
//		String extent = maxWidth + "x" + maxHeight;
//		if ("1".equals(type)) {
//			op.addRawArgs("+append");
//		} else if ("2".equals(type)) {
//			op.addRawArgs("-append");
//		}
//
//		op.addRawArgs("-thumbnail", thumb_size);
//		op.addRawArgs("-gravity", "center");
//		op.addRawArgs("-extent", extent);
//
//		Integer border_w = maxWidth / 40;
//		op.addRawArgs("-border", border_w + "x" + border_w);
//		op.addRawArgs("-bordercolor", "#ccc");
//
//		op.addRawArgs("-border", 1 + "x" + 1);
//		op.addRawArgs("-bordercolor", "#fff");
//
//		for (String img : args) {
//			op.addImage(img);
//		}
//		if ("1".equals(type)) {
//			Integer whole_width = ((mrg / 2) + 1 + border_w + maxWidth + border_w + (mrg / 2) + 1) * args.length - mrg;
//			Integer whole_height = maxHeight + border_w + 1;
//			op.addRawArgs("-extent", whole_width + "x" + whole_height);
//		} else if ("2".equals(type)) {
//			Integer whole_width = maxWidth + border_w + 1;
//			Integer whole_height = ((mrg / 2) + 1 + border_w + maxHeight + border_w + (mrg / 2) + 1) * args.length
//					- mrg;
//			op.addRawArgs("-extent", whole_width + "x" + whole_height);
//		}
//		op.addImage(newpath);
//		try {
//			cmd.run(op);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 计算图片返回
//	 *
//	 * @param srcImagePath
//	 * @param destImagePath
//	 * @param angle
//	 */
//	public int getbit(byte[] b) {
//		int i = 2;
//		while (true) {
//			int marker = (b[i] & 0xff) << 8 | (b[i + 1] & 0xff);
//			int sizes = (b[i + 2] & 0xff) << 8 | (b[i + 3] & 0xff);
//			if (marker >= 0xffc0 && marker <= 0xffcf && marker != 0xffc4 && marker != 0xffc8) {
//				int bitsPerPixel = (b[i + 4] & 0xff) * (b[i + 9] & 0xff);
//				break;
//			} else {
//				i += sizes + 2;
//			}
//		}
//
//		return i;
//	}
//
//	// public static void main(String[] args) {
//	// try {
//	// String src="F://src.jpg"; //需要加水印的源图片
//	// String desc="F://desc.jpg"; //生成的水印图片的路径
//	// String water="F://water.png"; //用中文转换成的背景透明的png图片
//	// String fontType="C:\\Windows\\Fonts\\simsun.ttc"; //指定字体文件为宋体
//	// String colorStr="000"; //颜色
//	// int fontSize=18;
//	//
//	// WaterTest watermark=new WaterTest();
//	//
//	//
//	// watermark.converFontToImage("中国四川成都", fontType, fontSize, colorStr,
//	// water);
//	//
//	//
//	// watermark.watermarkImg(src, desc, water, 5, 20,20, 100);
//	//// watermark.cutImage(src, desc, 0, 0, 300, 300);
//	//// watermark.zoomImage(100, null, src, desc);
//	//// watermark.addImgText(src, "中国四成都");
//	//// watermark.rotate(src, desc, 90);
//	//
//	//// String[] files = new String[5];
//	//// files[0] = "f://1.jpg";
//	//// files[1] = "f://2.png";
//	//// files[2] = "f://2.png";
//	//// files[3] = "f://2.png";
//	//// watermark.montage(files, 280, 200, "f://liboy1.jpg", 0,"2");
//	//
//	// } catch (Exception e) {
//	// // TODO Auto-generated catch block
//	// e.printStackTrace();
//	// }
//	// }
//
//}
