com.research.innovate.patterns.typefactory

/**
 * @author cgordon
 * @created 03/08/2019
 * @version 1.0
 * 
 * Type Factory design pattern implementation. 
 *
 */
@FunctionalInterface
public static interface VarFactory<R> {
    // Don't need type variables for name and displayName because they are always String
    R apply(String name, String displayName);
}
