package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features",
		glue = {"stepDefinitions"},
		plugin = "json:target/jsonReports/cucumber-report.json"
//		,
//		tags = "@DeletePlace"
		// komentda do puszczenia testów z tagiem w Mavenie: mvn test -Dcucumber.filter.tags=" @DeletePlace"
)
public class TestRunner {

}
