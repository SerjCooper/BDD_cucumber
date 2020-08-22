
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"html:target/cucumber/html", "json:target/cucumber/json/cucumber.json", "pretty"},
        features = {"src/test/java/features"},
        glue = "steps")
public class CucumberRunner extends AbstractTestNGCucumberTests {
}
