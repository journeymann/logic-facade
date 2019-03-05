com.research.innovate.patterns.nullobject

/**
 * @author cgordon
 * @created 03/05/2019
 * @version 1.0
 * 
 * Null Object design pattern implementation. [Entity]
 *
 */

public abstract class Entity implements BaseEntity{
    public Integer id;
    public boolean isNull(){return false;}
}
