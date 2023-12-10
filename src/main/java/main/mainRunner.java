package main;

import io.cucumber.core.cli.Main;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.utils.jsonreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.utils.jsonreader.getValueFromEnvParams;

@RunWith(Cucumber.class)
public class mainRunner {
    private static final Logger logger = LoggerFactory.getLogger(mainRunner.class);

    public static void main(String[] args) {
        String[] env_args = envSetup();
        logger.info("Cucumber RunTime Arguments are :" + env_args);
        Main.main(env_args);
    }

    private static String[] envSetup() {
        List<String> paramlist = new ArrayList<>();
        try {
            paramlist.add(getValueFromEnvParams("cucumber/features"));
            paramlist.addAll(List.of("-t", getValueFromEnvParams("cucumber/suites")));
            paramlist.addAll(List.of("-p", getValueFromEnvParams("cucumber/xml")));
            paramlist.addAll(List.of("-p", getValueFromEnvParams("cucumber/json")));
            paramlist.addAll(List.of("-p", getValueFromEnvParams("cucumber/html")));
            paramlist.addAll(List.of("-g", getValueFromEnvParams("cucumber/steps")));

        } catch (Exception e) {
            e.getStackTrace();
        }
        return paramlist.toArray(new String[0]);
    }


}
