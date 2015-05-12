package com.github.eirslett.maven.plugins.frontend.lib;

import java.util.Map;

public interface BowerRunner {
    public void execute(String args, Map<String, String> environment) throws TaskRunnerException;
}

final class DefaultBowerRunner extends NodeTaskExecutor implements BowerRunner {

    private static final String TASK_LOCATION = "node_modules/bower/bin/bower";

    DefaultBowerRunner(NodeExecutorConfig config) {
        super(config, TASK_LOCATION);
    }
}
