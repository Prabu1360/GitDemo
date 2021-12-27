package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(features ="src/test/java/feature" ,glue = {"stepDefinitions"},  plugin = "json:target/jsonReports/cucumber-report.json", dryRun = false, monochrome = true)
@RunWith(Cucumber.class)
public class TestRunner {

}
