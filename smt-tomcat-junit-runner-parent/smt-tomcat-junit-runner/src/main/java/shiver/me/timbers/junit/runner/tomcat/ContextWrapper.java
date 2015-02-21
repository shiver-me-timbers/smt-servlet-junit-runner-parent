package shiver.me.timbers.junit.runner.tomcat;

/**
 * @author Karl Bennett
 */
public interface ContextWrapper<JS, FD extends FilterDefWrapper, FM extends FilterMapWrapper> {

    void setJarScanner(JS jarScanner);

    void addFilterDef(FD filterDef);

    void addFilterMap(FM filterMap);

    void setAltDDName(String altDDName);

    FD createFilterDef();

    FM createFilterMap();
}
