import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import music.SoundPlayer;

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

    }

    @Then("^I heard Am with default time equals (\\d+)$")
    public void iHeardAmWithDefaultTimeEquals(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I add \"([^\"]*)\"$")
    public void iAdd(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^then \"([^\"]*)\"$")
    public void then(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I heard my music (\\d+) \\* default time equals (\\d+)$")
    public void iHeardMyMusicDefaultTimeEquals(int arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I entered chord \"([^\"]*)\" with time equals (\\d+)$")
    public void iEnteredChordWithTimeEquals(String arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I heard my music (\\d+) time$")
    public void iHeardMyMusicTime(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I add \"([^\"]*)\" time equals (\\d+)$")
    public void iAddTimeEquals(String arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^then \"([^\"]*)\" equals (\\d+)$")
    public void thenEquals(String arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
