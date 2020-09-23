package com.company.main;

import com.company.util.Constant;
import com.company.util.GameUtil;
import com.sun.tools.jconsole.JConsoleContext;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

//前景类，游戏中的遮挡层，包含多朵云
public class GameForeground {
    private final List<Cloud> clouds;   //云朵的容器

    private final BufferedImage[] cloudImages;  //图片资源

    private long time;  //控制云的逻辑运算周期
    public static final int CLOUD_INTERCAL = 100;   //云朵刷新的逻辑运算周期

    public GameForeground() {
        clouds = new ArrayList<>(); //云朵的容器

        //读入图片资源
        cloudImages = new BufferedImage[Constant.CLOUD_IMAGE_COUNT];
        for (int i = 0; i < Constant.CLOUD_IMAGE_COUNT; i++) {
            cloudImages[i] = GameUtil.loadBufferedImage(Constant.CLOUDS_ING_PATH[i]);
        }
        time = System.currentTimeMillis();  //获取当前时间，用于控制云朵的逻辑运算周期
    }

    //绘制方法
    public void draw(Graphics g, Bird bird) {
        cloudLogic();
        for (Cloud cloud : clouds) {
            cloud.draw(g, bird);
        }
    }

    //云朵的控制
    private void cloudLogic() {
        //100ms运算一次
        if (System.currentTimeMillis() - time > CLOUD_INTERCAL) {
            time = System.currentTimeMillis();  //重置time
            //如果屏幕的云朵的数量小于允许的最大数量，根据给定的概率随机添加云朵
            if (clouds.size() < Constant.MAX_CLOUD_COUNT) {
                try {
                    if (GameUtil.isInProbability(Constant.CLOUD_BORN_PERCENT, 100)) {
                        int index = GameUtil.getRandomNumber(0, Constant.CLOUD_IMAGE_COUNT);

                        //云朵刷新的坐标
                        int x = Constant.FRAME_WIDTH;   //从屏幕左侧开始刷新

                        //y坐标随机在上1/3屏选取
                        int y = GameUtil.getRandomNumber(Constant.TOP_BAR_HEIGHT, Constant.FRAME_HEIGHT / 3);

                        //向容器中添加云朵
                        Cloud cloud = new Cloud(cloudImages[index], x, y);
                        clouds.add(cloud);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //若云朵飞出屏幕则从容器移除
            for (int i = 0; i < clouds.size(); i++) {
                //遍历容器中的云朵
                Cloud tempCLoud = clouds.get(i);
                if (tempCLoud.isOutFrame()) {
                    clouds.remove(i);
                    i--;
                }
            }
        }
    }
}
