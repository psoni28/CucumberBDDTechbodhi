package runnerPack;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\resources\\Feature"
        , glue = {"steps"}
        , tags = "@Sanity"
        ,plugin = { "pretty", "html:target/cucumber.html" }
)
public class RunnerTest {

}
