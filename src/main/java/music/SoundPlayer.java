package music;

import org.apache.log4j.Logger;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SoundPlayer {
    private static final Logger LOGGER = Logger.getLogger(SoundPlayer.class);
    private static final long DEFAULT_TIME = 5;
    private static final long MIN_TIME = 1;
    private static final long MAX_TIME = 100;
    private static final long MILLISECONDS_IN_SECONDS = 1000;
    private Map<Chord, Sound> chordToSound = new EnumMap<>(Chord.class);
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    private String getPath(String nameChord) {
        String prefix = "C:\\Users\\Ivan\\Desktop\\collf\\lab4\\src\\main\\resources\\ru\\altstu\\chords\\";
        String suffix = ".wav";
        return prefix + nameChord + suffix;
    }

    public SoundPlayer() {
        try {
            chordToSound.put(Chord.A, new Sound(new File(getPath("A"))));
            chordToSound.put(Chord.Am, new Sound(new File(getPath("Am"))));
            chordToSound.put(Chord.D, new Sound(new File(getPath("D"))));
            chordToSound.put(Chord.Dm, new Sound(new File(getPath("Dm"))));
            chordToSound.put(Chord.E, new Sound(new File(getPath("E"))));
            chordToSound.put(Chord.Em, new Sound(new File(getPath("Em"))));
            chordToSound.put(Chord.C, new Sound(new File(getPath("C"))));
            chordToSound.put(Chord.G, new Sound(new File(getPath("G"))));
        } catch (IOException e) {
            LOGGER.warn(e);
        } catch (UnsupportedAudioFileException er) {
            LOGGER.warn(er);
        } catch (LineUnavailableException err) {
            LOGGER.warn(err);
        }
    }

    public boolean run(String chord) {
        return run(chord, DEFAULT_TIME);
    }

    public boolean run(String chord, long time) {
        return run(Collections.<Map.Entry<String, Long>>singletonList(new AbstractMap.SimpleEntry<>(chord, time)));
    }

    private void runChord(Chord chord, long time) {
        Sound sound = chordToSound.get(chord);
        sound.play(time * MILLISECONDS_IN_SECONDS);
    }

    /**
     * @param chords must be contain entries with correct name chord and time in range (MIN_TIME, MAX_TIME)
     * */
    public boolean run(List<Map.Entry<String, Long>> chords) {
        // check input data
        if (!checkNamesChordsAndTimes(chords)) {
            LOGGER.info("check failed");
            return false;
        }
        LOGGER.info("start play");
        // to play this
        for (Map.Entry<String, Long> entry : chords) {
            String chord = entry.getKey();
            long time = entry.getValue();
            LOGGER.info("start run chord");
            runChord(Chord.valueOf(chord), time);
        }
        return true;
    }

    private boolean checkChord(String name) {
        try {
            Chord.valueOf(name);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private boolean checkTime(long time) {
        return time >= MIN_TIME && time <= MAX_TIME;
    }

    private boolean checkNamesChordsAndTimes(List<Map.Entry<String, Long>> chords) {
        for (Map.Entry<String, Long> entry : chords) {
            String chord = entry.getKey();
            long time = entry.getValue();
            if (!(checkChord(chord) &&
                    checkTime(time)))
                return false;
        }
        return true;
    }

}
