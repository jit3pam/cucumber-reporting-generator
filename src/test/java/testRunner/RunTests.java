package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        dryRun = true,
        features = "src/test/resources",
        glue = {"me.jit3pam.cucumber.steps"},
        plugin = {
                "json:target/cucumber.json",
                "me.jit3pam.cucumber.CucumberReportGen:target/cucumber-html-reports"
        }
)
public class RunTests extends AbstractTestNGCucumberTests {
}
