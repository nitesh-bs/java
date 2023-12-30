package com.nitesh.restwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("nitesh");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("nitesh","lastName"));
	}
	
	@GetMapping(path = "/person",params = "version=1")
	public PersonV1 getFirstWithReqParamVersionOfPerson() {
		return new PersonV1("nitesh");
	}
	
	@GetMapping(path = "/person",params = "version=2")
	public PersonV2 getSecondWithReqParamVersionOfPerson() {
		return new PersonV2(new Name("nitesh","lastName"));
	}
	
	@GetMapping(path = "/person",headers =  "X-API-VERSION=1")
	public PersonV1 getFirstWithHeaderVersionOfPerson() {
		return new PersonV1("nitesh");
	}
	
	@GetMapping(path = "/person",headers = "X-API-VERSION=2")
	public PersonV2 getSecondWithHeaderVersionOfPerson() {
		return new PersonV2(new Name("nitesh","lastName"));
	}
	
	@GetMapping(path = "/person/accept",produces =  "application/vnd.company.app-v1+json")
	public PersonV1 getFirstWithAcceptHeaderVersionOfPerson() {
		return new PersonV1("nitesh");
	}
	
	@GetMapping(path = "/person/accept",produces =  "application/vnd.company.app-v2+json")
	public PersonV2 getSecondWithAcceptHeaderVersionOfPerson() {
		return new PersonV2(new Name("nitesh","lastName"));
	}
}
