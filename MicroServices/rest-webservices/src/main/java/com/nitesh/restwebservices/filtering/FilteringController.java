package com.nitesh.restwebservices.filtering;


import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.nitesh.restwebservices.bean.FilterningBean;


@RestController
public class FilteringController {

	@GetMapping("/filter")
	public MappingJacksonValue filtering() {
		FilterningBean bean = new FilterningBean("a","b","c");
		MappingJacksonValue jacksonValue = new MappingJacksonValue(bean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
		FilterProvider filters = new SimpleFilterProvider().addFilter("filterBean", filter );
		jacksonValue.setFilters(filters);
		return jacksonValue;
	}
	
	@GetMapping("/filter-list")
	public MappingJacksonValue filteringList() {
		List<FilterningBean> list = Arrays.asList(new FilterningBean("a","b","c"),new FilterningBean("a","b","c"));
		MappingJacksonValue jacksonValue = new MappingJacksonValue(list);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("filterBean", filter );
		jacksonValue.setFilters(filters);
		return jacksonValue;
	}
}
