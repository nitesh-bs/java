package com.nitesh;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

	public static void main(String[] args) {

		List<Integer> list = List.of(22, 3, 5, 66, 85);

		List<Integer> list2 = new ArrayList<>();
		list2.add(25);
		list2.add(30);
		list2.add(33);
		list2.add(56);

		System.out.println("List 1 :: " + list);
		System.out.println("List 2 :: " + list2);

		List<Integer> evenList = list2.stream().filter(e -> e % 2 == 0).collect(Collectors.toList());
		System.out.println(evenList);

		list2.stream().filter(i -> i % 5 == 0).forEach(System.out::println);

		String name[] = { "nitesh", "drashti", "luv", "raj" };

		Stream<String> stream1 = Stream.of(name);
		stream1.forEach(e -> {
			System.out.println(e);
		});

		List<String> list3 = List.of("nitesh", "drashti", "luv", "raj");
		list3.stream().filter(e -> e.contains("a")).forEach(System.out::println);

		list.stream().sorted().forEach(System.out::println);

		Integer integer = list.stream().min((x, y) -> x.compareTo(y)).get();
		System.out.println("Min : " + integer);

		Integer max = list.stream().max((x, y) -> x.compareTo(y)).get();
		System.out.println("max :: " + max);

		List<Emp> emp = new ArrayList<>();
		emp.add(new Emp(1, "nitesh", 1000, "hr"));
		emp.add(new Emp(2, "yash", 3000, "dev"));
		emp.add(new Emp(3, "raj", 1500, "production"));
		emp.add(new Emp(4, "nil", 2000, "hr"));

//		Optional<Emp> maxSalaryEmployee =
//				emp.stream()
//						.collect(Collectors.maxBy(Comparator.comparing(Emp::getSalary)));

		Map<String, Optional<Emp>> retVal = new HashMap<String, Optional<Emp>>();
		retVal = emp.stream().filter(e -> e.getDept().equals("hr"))
				.collect(Collectors.groupingBy(Emp::getDept, Collectors.maxBy(Comparator.comparing(Emp::getSalary))));
//		retVal.get("hr").stream().forEach(System.out::println);

		Optional<Emp> hr = emp.stream().filter(emp1 -> emp1.getDept().equals("hr"))
				.max(Comparator.comparing(Emp::getSalary));
//		System.out.println(retVal);
		System.out.println(hr.get());

//		List<Emp> emps= emp.stream().filter(e-> Collectors.groupingBy(Emp::getDept)).filter(e1->Collectors.maxBy(Comparator.comparing(Emp::getSalary))).collect(Collectors.toList());

		Optional<Emp> getMaxSalary = emp.stream().collect(Collectors.maxBy(Comparator.comparing(Emp::getSalary)));
		System.out.println(getMaxSalary.isPresent() ? getMaxSalary.get() : "Not available");

		Optional<Emp> getMinSalary = emp.stream().collect(Collectors.minBy(Comparator.comparing(Emp::getSalary)));
		System.out.println(getMinSalary.isPresent() ? getMinSalary.get() : "Not Available");

		List<Product> productsList = new ArrayList<Product>();
		// Adding Products
		productsList.add(new Product(1, "HP Laptop", 25000f));
		productsList.add(new Product(2, "Dell Laptop", 30000f));
		productsList.add(new Product(3, "Lenevo Laptop", 28000f));
		productsList.add(new Product(4, "Sony Laptop", 28000f));
		productsList.add(new Product(5, "Apple Laptop", 90000f));

		productsList.stream().filter(p -> p.getPrice() == 30000).forEach(System.out::println);
		
		

	}
}
