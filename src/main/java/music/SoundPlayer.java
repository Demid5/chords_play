package music;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SoundPlayer {
    private static final Logger LOGGER = Logger.getLogger(SoundPlayer.class);
    private static final long DEFAULT_TIME = 5;
    private static final long MIN_TIME = 1;
    private static final long MAX_TIME = 100;
    private static final long MILLISECONDS_IN_SECONDS = 100;
    private Map<Chord, Sound> chordToSound = new EnumMap<>(Chord.class);
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public SoundPlayer() {
        // TODO(ivankozlov98) configure chordToSound
    }

    public boolean run(String chord) {
        return run(chord, DEFAULT_TIME);
    }

    public boolean run(String chord, long time) {
        return run(Collections.<Map.Entry<String, Long>>singletonList(new AbstractMap.SimpleEntry<>(chord, time)));
    }

    private void runChord(Chord chord, long time) {
        Sound sound = chordToSound.get(chord);
        sound.play(time);
    }

    /**
     * @param chords must be contain entries with correct name chord and time in range (MIN_TIME, MAX_TIME)
     * */
    public boolean run(List<Map.Entry<String, Long>> chords) {
        // check input data
        if (!checkNamesChordsAndTimes(chords))
            return false;
        // to play this
        for (Map.Entry<String, Long> entry : chords) {
            String chord = entry.getKey();
            long time = entry.getValue();
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
