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

public class Course extends Entity{

    String code, year, name;
    Course(Integer id_, String name_, String year_){
        id=id_; name=name_; year=year_;
    }
    Course(){}
}
