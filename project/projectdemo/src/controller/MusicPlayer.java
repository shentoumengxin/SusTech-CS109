package controller;
import javax.sound.sampled.*;
import java.util.Objects;

public class MusicPlayer {

    public static void playMusic() {
        Thread musicThread = new Thread(() -> {
            try {
                // 打开音频文件
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(MusicPlayer.class.getResourceAsStream("welcomeMusic.wav")));

                // 获取音频格式
                AudioFormat format = audioInputStream.getFormat();

                // 创建数据行
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip clip = (Clip) AudioSystem.getLine(info);

                // 打开数据行并开始播放音频
                clip.open(audioInputStream);
                clip.start();
                // 等待音频播放完成
                Thread.sleep(clip.getMicrosecondLength() / 1000);
                // 关闭数据行和音频文件
                clip.stop();
                clip.close();
                clip.open(audioInputStream);
                clip.start();
                // 等待音频播放完成
                Thread.sleep(clip.getMicrosecondLength() / 1000);
                // 关闭数据行和音频文件
                clip.stop();
                clip.close();


                audioInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        musicThread.start();
    }
}
