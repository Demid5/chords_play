import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features={"src/main/resources/ru/altstu"},
        glue = "test/java",
        snippets = SnippetType.CAMELCASE
)
public class CucuRunner {

}
