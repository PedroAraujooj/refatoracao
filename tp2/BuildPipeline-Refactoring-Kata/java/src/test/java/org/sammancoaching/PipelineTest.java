package org.sammancoaching;

import org.junit.jupiter.api.Test;
import org.sammancoaching.dependencies.Config;
import org.sammancoaching.dependencies.Emailer;
import org.sammancoaching.dependencies.Project;
import org.sammancoaching.dependencies.TestStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PipelineTest {

    @Test
    void testsPass_deploySucceeds_emailEnabled_sendsSuccessEmail_andLogs() {
        Config config = () -> true;
        Emailer emailer = mock(Emailer.class);
        CapturingLogger log = new CapturingLogger();

        Pipeline pipeline = new Pipeline(config, emailer, log);

        Project project = Project.builder()
                .setTestStatus(TestStatus.PASSING_TESTS)
                .setDeploysSuccessfully(true)
                .build();

        pipeline.run(project);

        assertEquals(
                List.of(
                        "INFO: Tests passed",
                        "INFO: Deployment successful",
                        "INFO: Sending email"
                ),
                log.getLoggedLines()
        );
        verify(emailer).send("Deployment completed successfully");
    }

    @Test
    void testsFail_emailEnabled_sendsTestsFailedEmail_andDoesNotDeploy() {
        Config config = () -> true;
        Emailer emailer = mock(Emailer.class);
        CapturingLogger log = new CapturingLogger();

        Pipeline pipeline = new Pipeline(config, emailer, log);

        Project project = Project.builder()
                .setTestStatus(TestStatus.FAILING_TESTS)
                .setDeploysSuccessfully(true)
                .build();

        pipeline.run(project);

        assertEquals(
                List.of(
                        "ERROR: Tests failed",
                        "INFO: Sending email"
                ),
                log.getLoggedLines()
        );
        verify(emailer).send("Tests failed");
    }

    @Test
    void noTests_deployFails_emailEnabled_sendsDeployFailedEmail_andLogs() {
        Config config = () -> true;
        Emailer emailer = mock(Emailer.class);
        CapturingLogger log = new CapturingLogger();

        Pipeline pipeline = new Pipeline(config, emailer, log);

        Project project = Project.builder()
                .setTestStatus(TestStatus.NO_TESTS)
                .setDeploysSuccessfully(false)
                .build();

        pipeline.run(project);

        assertEquals(
                List.of(
                        "INFO: No tests",
                        "ERROR: Deployment failed",
                        "INFO: Sending email"
                ),
                log.getLoggedLines()
        );
        verify(emailer).send("Deployment failed");
    }

    @Test
    void emailDisabled_doesNotSendEmail_butLogsEmailDisabled() {
        Config config = () -> false;
        Emailer emailer = mock(Emailer.class);
        CapturingLogger log = new CapturingLogger();

        Pipeline pipeline = new Pipeline(config, emailer, log);

        Project project = Project.builder()
                .setTestStatus(TestStatus.PASSING_TESTS)
                .setDeploysSuccessfully(true)
                .build();

        pipeline.run(project);

        assertEquals(
                List.of(
                        "INFO: Tests passed",
                        "INFO: Deployment successful",
                        "INFO: Email disabled"
                ),
                log.getLoggedLines()
        );
        verifyNoInteractions(emailer);
    }
}