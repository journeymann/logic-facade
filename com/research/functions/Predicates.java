package com.research.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import com.research.types.common.Data;

/**
 * @author cgordon
 *
 */
public class Predicates {

	public static Predicate<String> lengthVerify  = (s)-> s.length() > 5;
	public static Predicate<Data> presetAgeVerify  = (d)-> d.getAge() > 17;
	public static Predicate<Data> presetGradeVerify  = (d)-> d.getGrade() == 'C';
	public static Predicate<Data> presetDateVerify  = (d)-> d.getDate().before(new Date());	

	public List<Data> filter(List<Data> inventory, Predicate<Data> p) {
		List<Data> result = new ArrayList<>();
		for (Data data : inventory) {
			if (p.test(data)) {
				result.add(data);
			}
		}
		
		return result;
	}
	
	public static boolean isValueNamePreset(Data data_) {
		return data_.getName().contains("Gallagher");

	}

	public static boolean isValueAgePreset(Data data_) {
		return data_.getAge()==17;

	}

	public static boolean isValueGradePreset(Data data_) {
		return data_.getGrade()=='B';

	}

}
