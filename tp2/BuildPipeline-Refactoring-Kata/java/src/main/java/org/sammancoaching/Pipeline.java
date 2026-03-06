package org.sammancoaching;

import org.sammancoaching.dependencies.Config;
import org.sammancoaching.dependencies.Emailer;
import org.sammancoaching.dependencies.Logger;
import org.sammancoaching.dependencies.Project;

public class Pipeline {
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {
        boolean unitTestsPassedOrAbsent;
        boolean productionDeploymentSucceeded;

        if (project.hasTests()) {
            if ("success".equals(project.runTests())) {
                log.info("Tests passed");
                unitTestsPassedOrAbsent = true;
            } else {
                log.error("Tests failed");
                unitTestsPassedOrAbsent = false;
            }
        } else {
            log.info("No tests");
            unitTestsPassedOrAbsent = true;
        }

        if (unitTestsPassedOrAbsent) {
            if ("success".equals(project.deploy())) {
                log.info("Deployment successful");
                productionDeploymentSucceeded = true;
            } else {
                log.error("Deployment failed");
                productionDeploymentSucceeded = false;
            }
        } else {
            productionDeploymentSucceeded = false;
        }

        if (config.sendEmailSummary()) {
            log.info("Sending email");
            if (unitTestsPassedOrAbsent) {
                if (productionDeploymentSucceeded) {
                    emailer.send("Deployment completed successfully");
                } else {
                    emailer.send("Deployment failed");
                }
            } else {
                emailer.send("Tests failed");
            }
        } else {
            log.info("Email disabled");
        }
    }
}
