package music;

import org.apache.log4j.Logger;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class SoundPlayer {
    private static final Logger LOGGER = Logger.getLogger(SoundPlayer.class);
    public static final long DEFAULT_TIME = 5;
    private static final long MIN_TIME = 1;
    private static final long MAX_TIME = 100;
    private static final long MILLISECONDS_IN_SECONDS = 1000;
    private Map<Chord, Sound> chordToSound = new EnumMap<>(Chord.class);
    List<Map.Entry<Chord, Long>> chords = new LinkedList<>();

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

    /**
     * add chord to list of sounds
     * @param chord is adding chord
     * @return true if successfully added , not - otherwise
     */
    public boolean addChord(String chord) {
        if (checkChord(chord)) {
            chords.add(
                    new AbstractMap.SimpleEntry<>(
                            Chord.valueOf(chord),
                            DEFAULT_TIME
                    )
            );
            return true;
        }
        LOGGER.info("Chord: " + chord + " unimplemented yet");
        return false;
    }

    /**
     * add chord to list of sounds
     * @param chord is adding chord
     * @param time is sound time
     * @return true if successfully added , not - otherwise
     */
    public boolean addChord(String chord, long time) {
        if (!checkChord(chord)) {
            LOGGER.info("Chord: " + chord + " unimplemented yet");
            return false;
        }
        if (!checkTime(time)) {
            LOGGER.info("Time must be in range: (" + MIN_TIME + ", " + MAX_TIME + ")");
            return false;
        }
        chords.add(
                new AbstractMap.SimpleEntry<>(
                        Chord.valueOf(chord),
                        time
                )
        );
        return true;
    }

    /**
     * this method need for debugging
     * @param chordsStr - list of chord with time
     * @return true if successfully added , not - otherwise
     */
    public boolean addChord(List<Map.Entry<String, Long>> chordsStr) {
        for (Map.Entry<String, Long> entry : chordsStr) {
            String chord = entry.getKey();
            long time = entry.getValue();
            if (!addChord(chord, time)) {
                return false;
            }
        }
        return true;
    }


    private void runChord(Chord chord, long time) {
        Sound sound = chordToSound.get(chord);
        sound.play(time * MILLISECONDS_IN_SECONDS);
    }

    /**
     * plays a melody
     * @return true if successfully ring out , not - otherwise
     */
    public boolean run() {
        if (chords.isEmpty()) {
            LOGGER.warn("Empty music");
            return false;
        }
        LOGGER.info("start play");
        // to play this
        for (Map.Entry<Chord, Long> entry : chords) {
            Chord chord = entry.getKey();
            long time = entry.getValue();
            LOGGER.info("start run chord");
            runChord(chord, time);
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

}
