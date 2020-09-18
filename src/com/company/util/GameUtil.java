package com.company.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/*
工具类，游戏中用到的工具都在此类
 */
public class GameUtil {
    private GameUtil() {

    }   //私有化，防止被其他类实例化


    //装载图片
    public static BufferedImage loadBufferedImage(String imgpath) {
        try {
            return ImageIO.read(new FileInputStream(imgpath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //判断任意概率的概率性事件是否发生，发生则返回true，否则返回false
    public static boolean isInProbability(int numerator, int denominator) throws Exception {
        //分子分母若小于0
        if (numerator <= 0 || denominator <= 0) {
            throw new Exception("传入了非法参数");
        }
        //分子大于分母
        if (numerator >= denominator) {
            return true;
        }
        return getRandomNumber(1, denominator + 1) <= numerator;

    }

    //获得指定区间的随机数
    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    //获得指定字符串在指定字体的宽高
    public static int getStringWidth(Font font, String str) {
        AffineTransform affineTransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affineTransform, true, true);
        return (int) (font.getStringBounds(str, frc).getWidth());
    }

    public static int getStringHeight(Font font, String str) {
        AffineTransform affineTransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affineTransform, true, true);
        return (int) (font.getStringBounds(str, frc).getHeight());
    }

    public static void drawImage(BufferedImage image, int x, int y, Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
