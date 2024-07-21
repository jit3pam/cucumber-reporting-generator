package me.jit3pam.cucumber;


import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.Plugin;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CucumberReportGen implements ConcurrentEventListener {

    public CucumberReportGen(String aString){
    }

    private final String jsonReportPath = "target/cucumber.json";
    private final String outputDirectory = "target/cucumber-html-reports";

    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted.class, this::onTestRunStarted);
        eventPublisher.registerHandlerFor(TestRunFinished.class, this::onTestRunFinished);
    }

    private <T> void onTestRunStarted(T t) {
        System.out.println("Test Run started.....");
    }

    private <T> void onTestRunFinished(T t) {
        generateReport();
    }

    private void generateReport() {
        File reportOutputDir = new File(outputDirectory);
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add(jsonReportPath);

        Configuration configuration =
                new Configuration(reportOutputDir, "Cucumber Report");

        ReportBuilder reportBuilder =
                new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
