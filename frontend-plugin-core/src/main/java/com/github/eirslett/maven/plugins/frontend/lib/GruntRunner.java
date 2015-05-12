package com.github.eirslett.maven.plugins.frontend.lib;

import java.util.Arrays;
import java.util.Map;

public interface GruntRunner {
    public void execute(String args, Map<String, String> environment) throws TaskRunnerException;
}

final class DefaultGruntRunner extends NodeTaskExecutor implements GruntRunner {

    private static final String TASK_LOCATION = "node_modules/grunt-cli/bin/grunt";

    DefaultGruntRunner(NodeExecutorConfig config) {
        super(config, TASK_LOCATION, Arrays.asList("--no-color"));
    }
}
