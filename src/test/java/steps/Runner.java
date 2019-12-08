package steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:reports", "json:reports/cucumber.json"},
        features = "src/test/resources/features/",
        glue = {"classpath:steps/"},
        tags = {"not @Ignore","@EnergyQuoteTests"})

public class Runner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    @AfterClass
    public static void reports() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                String jsonFile = "reports/cucumber.json";
                try {
                    if (Files.notExists(Paths.get(jsonFile)) ||
                            Files.size(Paths.get(jsonFile)) == 0) {
                        logger.info("File {} does not valid, skipping reports generation", jsonFile);
                    }
                } catch (IOException e) {
                    logger.error("Json has not found " + e);
                }
                String currentInstant = "report-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
                File reportOutputDirectory = new File("reports", currentInstant);
                Configuration configuration = new Configuration(reportOutputDirectory, "runner");
                ReportBuilder reportBuilder = new ReportBuilder(Collections.singletonList("reports/cucumber.json"), configuration);
                reportBuilder.generateReports();}
        });
    }
}