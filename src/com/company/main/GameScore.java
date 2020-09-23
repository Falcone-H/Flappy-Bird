package com.company.main;

import com.company.util.Constant;

import java.io.*;

//游戏计时类
public class GameScore {
    private static final GameScore GAME_SCORE = new GameScore();

    private long score = 0; //分数
    private long bestScore; //最高分数

    private GameScore() {
        bestScore = -1;
        try {
            loadBestScore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GameScore getInstance() {
        return GAME_SCORE;
    }

    //装载最高记录
    private void loadBestScore() throws Exception {
        File file = new File(Constant.SCORE_FILE_PATH);
        if (file.exists()) {
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            bestScore = dis.readLong();
            dis.close();
        }
    }

    //保存最高记录
    public void saveBestScore(long score) throws Exception {
        File file = new File(Constant.SCORE_FILE_PATH);
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
        dos.writeLong(score);
        dos.close();
    }

    public long getBestScore() {
        return bestScore;
    }

    public long getScore() {
        return score;
    }

    public void setScore(Bird bird) {
        if (!bird.isDead()) {
            score += 1;
        }
    }

    //判断是否为最高记录
    public void isSaveScore() {
        long score = getScore();
        if (bestScore < score)
            bestScore = score;
        try {
            saveBestScore(bestScore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //重置计分器
    public void reset() {
        score = 0;
    }
}
