package com.github.eirslett.maven.plugins.frontend.lib;

import java.util.Map;

public interface JspmRunner {
    void execute(String args, Map<String, String> environment) throws TaskRunnerException;
}

final class DefaultJspmRunner extends NodeTaskExecutor implements JspmRunner {

    static final String TASK_LOCATION = "node_modules/jspm/jspm.js";

    DefaultJspmRunner(NodeExecutorConfig config) {
        super(config, TASK_LOCATION);
    }

}
