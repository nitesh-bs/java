package com.nitesh.SpringWithMongoDb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.nitesh.SpringWithMongoDb.model.Task;

public interface TaskRepository extends MongoRepository<Task, String>{

	List<Task> findBySeverity(int serverity);

	@Query("{assignee: ?0 }")
	List<Task> findByAssigneeTask(String assignee);

}
