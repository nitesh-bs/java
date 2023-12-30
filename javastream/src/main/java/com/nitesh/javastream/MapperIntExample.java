package com.nitesh.javastream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public   class MapperIntExample {

	public static void main(String[] args) {

		List<String> names = Arrays.asList("Nitesh", "Kishan", "Raj", "Nikunj", "Sagar");

//		for (String name : names) {
//			if (!name.equals("Raj")) {
//				System.out.println(name);
//			}
//		}

		System.out.println("\nFunctional Style");

//		names.stream().filter(new Predicate<String>() {
//			public boolean test(String name) {
//				return !name.equals("Raj");
//			}
//
//		}).forEach(name -> System.out.println(name));
		
		names.stream()
		.filter(name -> !name.equals("Raj"))
		.forEach(System.out::println);

		System.out.println("Mapper Example");
		names.stream()
		.filter(t -> isNotRaj(t))
		.map(User::new) //use to convert type
		.forEach(System.out::println);
	
		System.out.println("Mapper Collect Example");
		List<User> userList = names.stream()
		.filter(t -> isNotRaj(t))
		.map(User::new)
		.collect(Collectors.toList());
		System.out.println(userList);

	}

	private static boolean isNotRaj(String name) {
		return !name.equals("Raj");
	}

	static class User {
		private String name;
		private Integer age = 30;

		public User(String name) {
			this.name = name;
		}

		public User(String name, Integer age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "User [name=" + name + ", age=" + age + "]";
		}

	}

}
