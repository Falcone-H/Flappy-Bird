package com.company.main;

import java.awt.*;
import java.awt.image.BufferedImage;

//云朵类
public class Cloud {
    private final int speed;    //速度
    private int x;  //坐标
    private final int y;

    private final BufferedImage img;

    private final int scaleImageWidth;
    private final int scaleImageHeight;

    //构造器
    public Cloud(BufferedImage img, int x, int y) {
        super();
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = 2; //云朵的速度
        //云朵图片缩放的比例 1.0 ~ 2.0
        double scale = 1 + Math.random();
        //缩放云朵的图片
        scaleImageWidth = (int) (scale * img.getWidth());
        scaleImageHeight = (int) (scale * img.getWidth());
    }

    //绘制方法
    public void draw(Graphics g, Bird bird) {
        int speed = this.speed;
        if (bird.isDead()) {
            speed = 1;
        }
        x -= speed;
        g.drawImage(img, x, y, scaleImageWidth, scaleImageHeight, null);
    }

    //判断云朵是否飞出屏幕
    public boolean isOutFrame() {
        return x < -1 * scaleImageWidth;
    }
}
