import music.SoundPlayer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        SoundPlayer soundPlayer = new SoundPlayer();
        System.out.println("Hello! This \"Chords play game\".");
        System.out.println("Example using:");
        System.out.println("Enter \"chord\" or \"chord, time\" or play(for play): Am");
        System.out.println("Enter \"chord\" or \"chord, time\" or play(for play): A, 4");
        System.out.println("Enter \"chord\" or \"chord, time\" or play(for play): Dm");
        System.out.println("Enter \"chord\" or \"chord, time\" or play(for play): play");
        System.out.println(">>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>");
        System.out.println("Start game:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter \"chord\" or \"chord, time\" or play(for play): ");
            String answer = scanner.nextLine();
            if (answer.equals("play")) {
                soundPlayer.run();
                break;
            } else {
                String[] chords = answer.split(",");
                switch (chords.length) {
                    case 1: {
                        soundPlayer.addChord(chords[0]);
                        break;
                    }
                    case 2: {
                        soundPlayer.addChord(chords[0], Long.parseLong(chords[1].trim()));
                        break;
                    }
                    default: {
                        System.out.println("No correct data. I'm sorry");
                    }
                }
            }
        }

    }
}
