com.research.innovate.patterns.nullobject

/**
 * @author cgordon
 * @created 03/05/2019
 * @version 1.0
 * 
 * Null Object design pattern implementation.
 *
 */

public class NullObject extends Entity{
    /** Lazy instantiation effeciency pattern */
    private static NullObject instance_= null;

    public static NullObject get() {
        /** double check idiom for thread safe */
        if (instance_ == null) {
            synchronized (NullObject.class) {

                if (instance_ == null)
                    instance_ = new NullObject();
            }
        }
        return instance_;
    }

    @Override
    public boolean isNull(){return true;}
}
