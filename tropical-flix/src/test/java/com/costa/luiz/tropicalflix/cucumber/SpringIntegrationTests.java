package com.costa.luiz.tropicalflix.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        plugin = {"pretty",
                "html:target/cucumber/index.html"
        },
        extraGlue = "com.costa.luiz.tropicalflix.cucumber")
public class SpringIntegrationTests {

}
