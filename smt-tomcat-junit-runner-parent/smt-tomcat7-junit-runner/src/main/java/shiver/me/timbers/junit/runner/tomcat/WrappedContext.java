package shiver.me.timbers.junit.runner.tomcat;

import org.apache.catalina.Context;
import org.apache.tomcat.JarScanner;

/**
 * @author Karl Bennett
 */
class WrappedContext implements ContextWrapper<JarScanner, WrappedFilterDef, WrappedFilterMap> {

    private final Context context;

    public WrappedContext(Context context) {
        this.context = context;
    }

    @Override
    public void setJarScanner(JarScanner jarScanner) {
        context.setJarScanner(jarScanner);
    }

    @Override
    public void addFilterDef(WrappedFilterDef filterDef) {
        context.addFilterDef(filterDef);
    }

    @Override
    public void addFilterMap(WrappedFilterMap filterMap) {
        context.addFilterMap(filterMap);
    }

    @Override
    public void setAltDDName(String altDDName) {
        context.setAltDDName(altDDName);
    }

    @Override
    public WrappedFilterDef createFilterDef() {
        return new WrappedFilterDef();
    }

    @Override
    public WrappedFilterMap createFilterMap() {
        return new WrappedFilterMap();
    }
}
