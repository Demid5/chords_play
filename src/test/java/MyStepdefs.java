import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import music.SoundPlayer;
import org.junit.Assert;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyStepdefs {

    private SoundPlayer soundPlayer;
    private List<Map.Entry<String, Long>> chords;

    @Given("^I have tools for sound on Java$")
    public void iHaveToolsForSoundOnJava() throws Throwable {
        this.soundPlayer = new SoundPlayer();
        this.chords = new LinkedList<>();
    }

    @When("^I entered chord \"([^\"]*)\"$")
    public void iEnteredChord(String chord) throws Throwable {
        this.iAdd(chord);
    }

    @Then("^I heard Am with default time equals (\\d+)$")
    public void iHeardAmWithDefaultTimeEquals(int totalTime) throws Throwable {
        long startTime = System.nanoTime();
        Assert.assertTrue(soundPlayer.run());
        long estimatedTime = (System.nanoTime() - startTime) / 1000000;
        Assert.assertTrue(Math.abs(estimatedTime - totalTime * 1000) < 1000);
    }

    @When("^I add \"([^\"]*)\"$")
    public void iAdd(String chord) throws Throwable {
        soundPlayer.addChord(chord);
    }

    @When("^I entered chord \"([^\"]*)\" with time equals (\\d+)$")
    public void iEnteredChordWithTimeEquals(String chord, int time) throws Throwable {
        this.iAddTimeEquals(chord, time);
    }

    @And("^I add \"([^\"]*)\" time equals (\\d+)$")
    public void iAddTimeEquals(String chord, int time) throws Throwable {
        soundPlayer.addChord(chord, time);
    }

    @Then("^I nothing to heard$")
    public void iNothingToHeard() throws Throwable {
        Assert.assertFalse(soundPlayer.run());
    }
}
