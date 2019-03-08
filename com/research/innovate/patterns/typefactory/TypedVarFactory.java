com.research.innovate.patterns.typefactory;

/**
 * @author cgordon
 * @created 03/08/2019
 * @version 1.0
 * 
 * Type Factory design pattern implementation. 
 *
 */

@FunctionalInterface
public static interface TypedVarFactory<T extends Serializable, R extends Var<T>> {
    R apply(String name, String displayName, T value);
}
