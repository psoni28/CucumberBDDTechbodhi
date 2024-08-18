package runnerPack;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.text.SimpleDateFormat;

import java.util.Date;


import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\resources\\Feature"
        , glue = {"steps"}
        , tags = "@Sanity"
        , plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:target/cucumber-report.json"}

)
public class RunnerTest2 {
/*    @BeforeClass
    public static void setup() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("cucumber-reports/" + timeStamp.replace(":", "_").replace(".", "_") + ".html");
    }


    @AfterClass
    public static void writeExtentReport() {

        Reporter.loadXMLConfig(new File("C:\\Users\\Admin\\IdeaProjects\\BDD_SeleniumJavaTraining\\src\\test\\resources\\extent-config.xml"));

        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
    }*/
}
