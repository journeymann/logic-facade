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

public class Teacher extends Person{

    Set<Course> courses;
    Teacher(Integer id_, String name_, Set<Course> courses_){
        id=id_; name=name_; courses=courses_;
    }
    Teacher(){}
}
