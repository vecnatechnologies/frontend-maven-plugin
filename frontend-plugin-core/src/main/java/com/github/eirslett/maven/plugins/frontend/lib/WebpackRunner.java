package com.github.eirslett.maven.plugins.frontend.lib;

import java.util.ArrayList;
import java.util.Map;

public interface WebpackRunner {
    void execute(String args, Map<String, String> environment) throws TaskRunnerException;
}

final class DefaultWebpackRunner extends NodeTaskExecutor implements WebpackRunner {

    private static final String TASK_LOCATION = "node_modules/webpack/bin/webpack.js";

    DefaultWebpackRunner(NodeExecutorConfig config) {
        super(config, TASK_LOCATION, new ArrayList<String>());
    }
}
