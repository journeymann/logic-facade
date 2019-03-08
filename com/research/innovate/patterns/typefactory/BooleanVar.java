com.research.innovate.patterns.typefactory;

/**
 * @author cgordon
 * @created 03/08/2019
 * @version 1.0
 * 
 * Type Factory design pattern implementation. 
 *
 */
public static class BooleanVar extends Var<Boolean> {
    public BooleanVar(final String name, final String displayName) {
        super(name, displayName);
    }

    public BooleanVar(final String name, final String displayName, final Boolean value) {
        super(name, displayName, value);
    }
}
