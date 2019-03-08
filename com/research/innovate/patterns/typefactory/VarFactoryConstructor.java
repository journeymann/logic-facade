com.research.innovate.patterns.typefactory;

import java.io.Serializable;
import java.util.Date;
import org.junit.Test;

/**
 * @author cgordon
 * @created 03/08/2019
 * @version 1.0
 * 
 * Type Factory design pattern implementation. 
 *
 */
public class VarFactoryConstructor {

    @Test
    public void testVarFactory() throws Exception {
        DateVar dateVar = makeVar("D", "Date", DateVar::new);
        dateVar.setValue(new Date());
        System.out.println(dateVar);

        DateVar dateTypedVar = makeTypedVar("D", "Date", new Date(), DateVar::new);
        System.out.println(dateTypedVar);

        TypedVarFactory<Date, DateVar> dateTypedFactory = DateVar::new;
        System.out.println(dateTypedFactory.apply("D", "Date", new Date()));

        BooleanVar booleanVar = makeVar("B", "Boolean", BooleanVar::new);
        booleanVar.setValue(true);
        System.out.println(booleanVar);

        BooleanVar booleanTypedVar = makeTypedVar("B", "Boolean", true, BooleanVar::new);
        System.out.println(booleanTypedVar);

        TypedVarFactory<Boolean, BooleanVar> booleanTypedFactory = BooleanVar::new;
        System.out.println(booleanTypedFactory.apply("B", "Boolean", true));
    }

    private <V extends Var<T>, T extends Serializable> V makeVar(final String name, final String displayName,
            final VarFactory<V> varFactory) {
        V var = varFactory.apply(name, displayName);
        return var;
    }

    private <V extends Var<T>, T extends Serializable> V makeTypedVar(final String name, final String displayName, final T value,
            final TypedVarFactory<T, V> varFactory) {
        V var = varFactory.apply(name, displayName, value);
        return var;
    }
}
