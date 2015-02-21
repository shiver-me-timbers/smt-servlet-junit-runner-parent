package shiver.me.timbers.junit.runner.tomcat;

/**
 * @author Karl Bennett
 */
public interface ContextWrapper<FD extends FilterDefWrapper, FM extends FilterMapWrapper> {

    void setJarScanner(JarScannerWrapper jarScanner);

    void addFilterDef(FD filterDef);

    void addFilterMap(FM filterMap);

    void setAltDDName(String altDDName);

    FD createFilterDef();

    FM createFilterMap();
}
