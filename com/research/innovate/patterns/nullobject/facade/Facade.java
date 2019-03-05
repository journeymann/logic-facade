com.research.innovate.patterns.nullobject.model

import com.research.innovate.patterns.nullobject.*

/**
 * @author cgordon
 * @created 03/05/2019
 * @version 1.0
 * 
 * Null Object design pattern implementation.
 *
 */

public final class Facade{

    public static <T> Stream<T> setToStream (Set<T> set)
    {
        return set.stream();
    }

    public static <T> Set<T> streamToSet (Stream<T> stream)
    {
        return stream.filter(Objects::nonNull).collect(Collectors.toSet());
    }

    public static Entity findStudent (Stream<Entity> stream, Integer key)
    {
        return stream.filter(a -> a instanceof Student).filter(s -> s.id == key)
                .findFirst().orElse(NullObject.get());
    }

    public static <T,R> List<R> map(final Function<T,R> function, final List<T> source){

        return source.stream().map(function).collect(Collectors.toList());
    }

    public static <T,S> List<T> convertEntityList(final List<S> list, final Class<T> klass) {

        return Facade.map(s -> klass.cast(s), list);
    }
}
