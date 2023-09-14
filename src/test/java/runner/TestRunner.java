package runner;

import com.natwest.utility.WebServiceConfig;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty"
				, "html:target/cucumber/report.html"
				, "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		}
		,features = {"src/test/resources/features"}
		,glue = {"stepdef"}
		,snippets = CAMELCASE
		,monochrome=true
)

public class TestRunner {

	@BeforeClass
	public static void setUpBeforeClass(){
		WebServiceConfig.setup();
	}

	@AfterClass
	public static void tearDownAfterClass(){
		WebServiceConfig.tearDown();
	}
}
