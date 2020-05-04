import music.SoundPlayer;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        SoundPlayer soundPlayer = new SoundPlayer();
        List<Map.Entry<String, Long>> stringLongMap = new LinkedList<>();
        stringLongMap.add(new AbstractMap.SimpleEntry<>("C", 5L));
        stringLongMap.add(new AbstractMap.SimpleEntry<>("D", 10L));
        stringLongMap.add(new AbstractMap.SimpleEntry<>("Dm", 5L));
        stringLongMap.add(new AbstractMap.SimpleEntry<>("A", 5L));
        stringLongMap.add(new AbstractMap.SimpleEntry<>("Am", 5L));
        stringLongMap.add(new AbstractMap.SimpleEntry<>("E", 5L));
        stringLongMap.add(new AbstractMap.SimpleEntry<>("Em", 5L));
        stringLongMap.add(new AbstractMap.SimpleEntry<>("G", 5L));
        stringLongMap.add(new AbstractMap.SimpleEntry<>("C", 5L));
        soundPlayer.addChord(stringLongMap);
        soundPlayer.run();
    }

}
