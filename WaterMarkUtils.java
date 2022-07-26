package com.example.watermarkdemo.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片添加水印
 * @author qzz
 */
public class WaterMarkUtils {

    public static void main(String[] args) throws IOException {
        // 读取原图片信息 得到文件（本地图片）
        File srcImgFile = new File("F:/image/1.png");
        //将文件对象转化为图片对象
        Image srcImg = ImageIO.read(srcImgFile);
        //获取图片的宽
        int srcImgWidth = srcImg.getWidth(null);
        //获取图片的高
        int srcImgHeight = srcImg.getHeight(null);
        System.out.println("图片的宽:"+srcImgWidth);
        System.out.println("图片的高:"+srcImgHeight);

        //创建一个URL对象,获取网络图片的地址信息（网络图片）
//        URL url = new URL("https://pngimg.com/distr/img/ukraine.png");
//        //将URL对象输入流转化为图片对象 (url.openStream()方法，获得一个输入流)
//        Image srcImg = ImageIO.read(url.openStream());
//        //获取图片的宽
//        int srcImgWidth = srcImg.getWidth(null);
//        //获取图片的高
//        int srcImgHeight = srcImg.getHeight(null);
//        System.out.println("图片的宽:"+srcImgWidth);
//        System.out.println("图片的高:"+srcImgHeight);



        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
        // 加水印
        //创建画笔
        Graphics2D g = bufImg.createGraphics();
        //绘制原始图片
        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        //-------------------------文字水印 start----------------------------
//        //根据图片的背景设置水印颜色
//        g.setColor(new Color(255,255,255,128));
//        //设置字体  画笔字体样式为微软雅黑，加粗，文字大小为60pt
//        g.setFont(new Font("微软雅黑", Font.BOLD, 60));
//        String waterMarkContent="图片来源：https://image.baidu.com/";
//        //设置水印的坐标(为原图片中间位置)
//        int x=(srcImgWidth - getWatermarkLength(waterMarkContent, g)) / 2;
//        int y=srcImgHeight / 2;
//        //画出水印 第一个参数是水印内容，第二个参数是x轴坐标，第三个参数是y轴坐标
//        g.drawString(waterMarkContent, x, y);
//        g.dispose();
        //-------------------------文字水印 end----------------------------

        //-------------------------图片水印 start----------------------------
        // 水印文件
        String waterMarkImage="F:/image/s.png";
        Image srcWaterMark = ImageIO.read(new File(waterMarkImage));
        int widthWaterMark= srcWaterMark.getWidth(null);
        int heightWaterMark = srcWaterMark.getHeight(null);
        //alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.9f));
        //绘制水印图片  坐标为中间位置
        g.drawImage(srcWaterMark, (srcImgWidth - widthWaterMark) / 2,
                (srcImgHeight - heightWaterMark) / 2, widthWaterMark, heightWaterMark, null);
        // 水印文件结束
        g.dispose();
        //-------------------------图片水印 end----------------------------

        //待存储的地址
        String tarImgPath="F:/image/t.png";
        // 输出图片
        FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
        ImageIO.write(bufImg, "png", outImgStream);
        System.out.println("添加水印完成");
        outImgStream.flush();
        outImgStream.close();

    }

    /**
     * 获取水印文字的长度
     * @param waterMarkContent
     * @param g
     * @return
     */
    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }
}
