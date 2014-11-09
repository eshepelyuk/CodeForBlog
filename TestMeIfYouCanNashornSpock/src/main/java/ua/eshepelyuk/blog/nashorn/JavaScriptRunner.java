package ua.eshepelyuk.blog.nashorn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.InputStreamReader;
import java.util.Map;

public class JavaScriptRunner {
    public static Object run(String script, Map<String, Object> params) throws Exception {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            engine.getBindings(ScriptContext.ENGINE_SCOPE).put(entry.getKey(), entry.getValue());
        }

        return engine.eval(new InputStreamReader(JavaScriptRunner.class.getResourceAsStream(script)));
    }
}
