package com.nitesh.springBoot3SecurityCrud;

import com.nitesh.springBoot3SecurityCrud.entity.Emp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringBoot3RestCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3RestCrudApplication.class, args);

		List<Emp> emp = new ArrayList<>();
		emp.add(new Emp(1,"nitesh",1000,"hr"));
		emp.add(new Emp(2,"yash",3000,"dev"));
		emp.add(new Emp(3,"raj",1500,"production"));
		emp.add(new Emp(4,"nil",2000,"hr"));

//		Optional<Emp> maxSalaryEmployee =
//				emp.stream()
//						.collect(Collectors.maxBy(Comparator.comparing(Emp::getSalary)));


		Map<String, Optional<Emp>> retVal = new HashMap<String, Optional<Emp>>();
		retVal = emp.stream().filter(e->e.getDept().equals("hr")).collect(Collectors.groupingBy(Emp::getDept,Collectors.maxBy(Comparator.comparing(Emp::getSalary))));
//		retVal.get("hr").stream().forEach(System.out::println);

		Optional<Emp> hr = emp.stream().filter(emp1 -> emp1.getDept().equals("hr")).max(Comparator.comparing(Emp::getSalary));
//		System.out.println(retVal);
		System.out.println(hr.get());


//		List<Emp> emps= emp.stream().filter(e-> Collectors.groupingBy(Emp::getDept)).filter(e1->Collectors.maxBy(Comparator.comparing(Emp::getSalary))).collect(Collectors.toList());
	}

}
