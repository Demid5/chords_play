package music;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements AutoCloseable {
    static final Logger LOGGER = Logger.getLogger(Sound.class);

    private AudioInputStream stream;
    private Clip clip;

    public Sound(File soundFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
       this.stream = AudioSystem.getAudioInputStream(soundFile);
       this.clip = AudioSystem.getClip();
    }


    private void playOfPart(long time) {
        try {
            clip.setFramePosition(0); //устанавливаем указатель на старт
            clip.start(); //Поехали!!!
            Thread.sleep(time);
        } catch (InterruptedException e) {
            LOGGER.warn(e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * the sound lasts for time
     * @param time is time of sound
     */
    public void play(long time) {
        try {
            clip.open(stream);
            long timeOfClip = clip.getMicrosecondLength() / 1000;
            if (time < timeOfClip) {
                playOfPart(time);
            } else {
                long ost = time % timeOfClip;
                LOGGER.info(ost);
                long partCount = time / timeOfClip;
                LOGGER.info("start parts");
                for (int i = 0; i < partCount; ++i) {
                    LOGGER.info("start " + i);
                    playOfPart(timeOfClip);
                }
                LOGGER.info("Start ostatok");
                playOfPart(ost);
            }
        } catch (IOException e) {
            LOGGER.warn(e);
        } catch (LineUnavailableException er) {
            LOGGER.warn(er);
        } finally {
            clip.stop(); //Останавливаем
        }
    }


    @Override
    public void close() throws Exception {
        clip.close();
    }
}