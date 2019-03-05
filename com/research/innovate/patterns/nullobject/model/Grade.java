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

public class Grade extends Entity{

    Course course;
    Integer grade;
    Grade(Integer id_, Course course_, Integer grade_){
        id=id_; course=course_; grade=grade_;
    }
    Grade(){}
}
