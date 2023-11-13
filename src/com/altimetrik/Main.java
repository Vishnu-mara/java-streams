package com.altimetrik;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee(101, "Suresh G",29, "Male", "Software Engineer", 2014, 1000000, new String[]{"java","scala"} ));
		empList.add(new Employee(101, "Gowri",34, "Male", "Senior Software Engineer", 2004, 3000000,new String[]{"java, C"} ));
		empList.add(new Employee(101, "Venkatesh",32, "Male", "Senior Software Engineer", 2014, 2200000,new String[]{"java"} ));
		empList.add(new Employee(101, "Meena",35, "Female", "Senior Software Engineer", 2000, 3400000,new String[]{"python","ruby"} ));
		empList.add(new Employee(101, "Ganesh",24, "Male", "Software Engineer", 2022, 1000000, new String[]{"scala"} ));
		empList.add(new Employee(101, "Ravi",30, "Male", "Software Engineer", 2018, 2500000, new String[]{"java, C, C++"} ));
		empList.add(new Employee(101, "Ramya",27, "Female", "Software Engineer", 2011, 1300000,new String[]{"C","C++"} ));
		empList.add(new Employee(101, "Krishna",29, "Male", "Software Engineer", 2014, 1500000,new String[]{".Net","VB"} ));
		empList.add(new Employee(101, "Teja",39, "Male", "Manager", 2000, 3500000,new String[]{"java"} ));
		
		
//		count based on Gender
		Map<String, Long> genderWiseCount = empList.stream()
				.collect(Collectors.groupingBy(e -> e.getGender(), Collectors.counting()));
		System.out.println("Gender wise count:: " + genderWiseCount);

		System.out.println("----------------------------");
		
//		 Print the name of all departments in the organization
		System.out.println("Below are the list of all Departments :: ");
		empList.stream().map(Employee:: getDepartment).distinct().forEach( e -> System.out.println(e));
		
		System.out.println("----------------------------");
		
//		average age of male and female employees
		Map<String, Double> avgAgeMap = empList.stream()
//	            .filter(e -> e.getDepartment().equalsIgnoreCase("Senior Software Engineer"))
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getAge)));
		System.out.println("Gender wise Age average :: "+avgAgeMap);
		
		System.out.println("----------------------------");
		
//		 Get the details of highest paid employee in the organization
		Optional<Employee> highestEmployeeOpt=
				empList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
		if(highestEmployeeOpt.isPresent()) {
			System.out.println("Highest emp :: "+highestEmployeeOpt.get());
		}
		
		System.out.println("----------------------------");
		
//		Get the names of all employees who have joined after 2010
		List<Employee> empYearAfterList = empList.stream().filter(e -> e.getYearOfJoining() > 2010).collect(Collectors.toList());
		
		System.out.println("employees after 2010 :: "+empYearAfterList);
		
		System.out.println("----------------------------");
		
//		Count the number of employees in each department
		Map<String, Long> deptWiseCount = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println("Deptartment wise count :: "+deptWiseCount);
		
		System.out.println("----------------------------");
		
//		 average salary of each department
		Map<String, Double> salAvgMap = empList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println("Average sal department wise :: "+salAvgMap);
		
		System.out.println("----------------------------");
		
//		Get the details of youngest male employee in the product development department
		Optional<Employee> youngestEmpOpt
				= empList.stream()
						.filter(e -> e.getGender().equalsIgnoreCase("male")
								&& e.getDepartment().equalsIgnoreCase("Software Engineer"))
						.min(Comparator.comparingInt(Employee::getAge));

		if(youngestEmpOpt.isPresent()) {
			System.out.println("Youngest male employee details :: "+ youngestEmpOpt.get());
		}
		
		System.out.println("----------------------------");
		
//		 most working experience in the organization
		Optional<Employee> highExpEmpOpt = empList.stream().sorted(Comparator.comparingInt(Employee :: getYearOfJoining)).findFirst();
		if(highExpEmpOpt.isPresent()) {
			System.out.println("Highest work experience :: "+highExpEmpOpt.get());
		}
		
		System.out.println("----------------------------");
		
//		List down the names of all employees in each department
		Map<String, Set<String>> empNameListByDepartment=
				empList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.mapping(Employee:: getName, Collectors.toSet())));
		empNameListByDepartment.forEach((k,v) -> System.out.println("Department :: " + k + ", Emp Names :: "+v));
		
		System.out.println("----------------------------");
		
//		 average salary and total salary of the whole organization
		DoubleSummaryStatistics empSalaryStatistics = empList.stream()
				.collect(Collectors.summarizingDouble(Employee::getSalary));

		System.out.println("Average Salary = " + Math.round(empSalaryStatistics.getAverage()));

		System.out.println("Total Salary = " + empSalaryStatistics.getSum());
		
		
//		 Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
		Map<Boolean, List<Employee>> partitionEmployeesByAge = empList.stream()
				.collect(Collectors.partitioningBy(e -> e.getAge() > 25));

		Set<Entry<Boolean, List<Employee>>> entrySet = partitionEmployeesByAge.entrySet();

		for (Entry<Boolean, List<Employee>> entry : entrySet) {
			System.out.println("----------------------------");

			if (entry.getKey()) {
				System.out.println("Employees older than 25 years :");
			} else {
				System.out.println("Employees younger than or equal to 25 years :");
			}

			System.out.println("----------------------------");

			List<Employee> list = entry.getValue();

			for (Employee e : list) {
				System.out.println(e.getName());
			}
		}
		
		System.out.println("----------------------------");
		
		// print list of languages from the Employee list
		List<String> list =
		        empList.stream()
		                .map(x -> x.getLanguages())      //Stream<Set<String>>
		                .flatMap(x -> x.stream())   //Stream<String>
		                .distinct()
		                .collect(Collectors.toList());
		
		list.stream().parallel().forEach(System.out::println);
//		collect.forEach(x -> System.out.println(x));
		
		System.out.println("----------------------------");
	}
}
