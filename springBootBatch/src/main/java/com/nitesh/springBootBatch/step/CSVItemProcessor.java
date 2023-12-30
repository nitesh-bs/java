package com.nitesh.springBootBatch.step;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.nitesh.springBootBatch.entity.Employee;

@Component
public class CSVItemProcessor implements ItemProcessor<Employee, Employee>{

	private static final Map<String, String> DEPT_NAMES = new HashMap<>();
	
	public CSVItemProcessor() {
		DEPT_NAMES.put("1", "HR");
		DEPT_NAMES.put("2", "IT");
		DEPT_NAMES.put("3", "FINANCE");
	}
	
	@Override
	public Employee process(Employee item) throws Exception {
		String deptCode = item.getDept();
		String dept = DEPT_NAMES.get(deptCode);
		item.setDept(dept);
		item.setCreationTs(new Date());
		System.out.println(String.format("Converted from [%s] to [%s]", deptCode,dept));
		return item;
	}

}
