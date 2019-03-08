com.research.innovate.patterns.typefactory;

/**
 * @author cgordon
 * @created 03/08/2019
 * @version 1.0
 * 
 * Type Factory design pattern implementation. 
 *
 */
public static class Var<T extends Serializable> {
    private String name;
    private String displayName;
    private T value;

    public Var(final String name, final String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public Var(final String name, final String displayName, final T value) {
        this(name, displayName);
        this.value = value;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s[name=%s, displayName=%s, value=%s]", getClass().getSimpleName(), this.name, this.displayName,
                this.value);
    }
}
