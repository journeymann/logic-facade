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

public class Student extends Person{

    Set<Grade> grades;
    Student(Integer id_, String name_, Set<Grade> grades_){
        id=id_; name=name_; grades=grades_;
    }
    Student(){}
}
