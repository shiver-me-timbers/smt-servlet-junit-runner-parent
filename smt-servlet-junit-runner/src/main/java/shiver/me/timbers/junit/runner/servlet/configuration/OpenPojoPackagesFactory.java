package shiver.me.timbers.junit.runner.servlet.configuration;

import com.openpojo.reflection.PojoClass;
import shiver.me.timbers.junit.runner.servlet.Factory;
import shiver.me.timbers.junit.runner.servlet.Packages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.openpojo.reflection.impl.PojoClassFactory.enumerateClassesByExtendingType;

/**
 * @author Karl Bennett
 */
public class OpenPojoPackagesFactory<T, C> implements PackagesFactory<T> {

    private final Factory<List<Class<? extends C>>, T> typeConverter;
    private final Class<C> type;

    public OpenPojoPackagesFactory(Factory<List<Class<? extends C>>, T> typeConverter, Class<C> type) {
        this.typeConverter = typeConverter;
        this.type = type;
    }

    @Override
    public T create(Packages packages) {

        final List<Class<? extends C>> allTypes = new ArrayList<>();

        for (String pkg : packages) {

            final Set<Class<? extends C>> types = transform(enumerateClassesByExtendingType(pkg, type, null));

            allTypes.addAll(types);
        }

        return typeConverter.create(allTypes);
    }

    @SuppressWarnings("unchecked")
    private static <T> Set<Class<? extends T>> transform(List<PojoClass> pojoClasses) {

        final Set<Class<? extends T>> filteredTypes = new HashSet<>();

        for (PojoClass pojoClass : pojoClasses) {
            filteredTypes.add((Class<? extends T>) pojoClass.getClazz());
        }

        return filteredTypes;
    }
}
