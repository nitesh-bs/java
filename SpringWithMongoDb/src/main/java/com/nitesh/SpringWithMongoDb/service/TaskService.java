package com.nitesh.SpringWithMongoDb.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.SpringWithMongoDb.model.Task;
import com.nitesh.SpringWithMongoDb.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;
	
	public Task addTask(Task task) {
		task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
		return repository.save(task);
	}
	
	public List<Task> findAllTask(){
		return repository.findAll();
	}
	
	public Task getTaskByTaskId(String taskId) {
		return repository.findById(taskId).get();
	}
	
	public List<Task> getTaskBySeverity(int serverity){
		return repository.findBySeverity(serverity);
	}
	
	public List<Task> getTaskByAssignee(String assignee){
		return repository.findByAssigneeTask(assignee);
	}
	
	public Task updateTask(Task task) {
		//get existing document from DB
		Task existingTask = repository.findById(task.getTaskId()).get();
		existingTask.setDescription(task.getDescription());
		existingTask.setSeverity(task.getSeverity());
		existingTask.setAssignee(task.getAssignee());
		existingTask.setStoryPoint(task.getStoryPoint());
		
		return repository.save(existingTask);
	}
	
	public String deleteTask(String taskId) {
		repository.deleteById(taskId);
		return "task deleted : "+taskId;
	}
	
}
