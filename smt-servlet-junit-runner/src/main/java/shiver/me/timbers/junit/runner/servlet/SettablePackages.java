package shiver.me.timbers.junit.runner.servlet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Karl Bennett
 */
public class SettablePackages implements Packages {

    private static List<String> transform(String... packageStrings) {

        final List<String> details = new ArrayList<>(packageStrings.length);

        Collections.addAll(details, packageStrings);

        return details;
    }

    private final List<String> servlets;

    public SettablePackages(String... packageStrings) {
        this(transform(packageStrings));
    }

    public SettablePackages(List<String> servlets) {
        this.servlets = servlets;
    }

    public SettablePackages(Packages... servletses) {
        this.servlets = new ArrayList<>();

        for (Packages servlets : servletses) {
            add(servlets);
        }
    }

    @Override
    public List<String> asList() {
        return servlets;
    }

    public void add(Packages servlets) {
        this.servlets.addAll(servlets.asList());
    }

    @Override
    public Iterator<String> iterator() {
        return servlets.iterator();
    }
}
