package shiver.me.timbers.junit.runner.tomcat;

import org.apache.catalina.Engine;

/**
 * @author Karl Bennett
 */
class WrappedEngine implements EngineWrapper {

    private final Engine engine;

    public WrappedEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String getName() {
        return engine.getName();
    }

    @Override
    public void setName(String name) {
        engine.setName(name);
    }
}
