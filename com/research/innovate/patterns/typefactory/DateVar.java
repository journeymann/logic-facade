com.research.innovate.patterns.typefactory;

/**
 * @author cgordon
 * @created 03/08/2019
 * @version 1.0
 * 
 * Type Factory design pattern implementation. 
 *
 */
public static class DateVar extends Var<Date> {
    public DateVar(final String name, final String displayName) {
        super(name, displayName);
    }

    public DateVar(final String name, final String displayName, final Date value) {
        super(name, displayName, value);
    }
}
