package com.example.taskmanager.controller;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final DatabaseReference dbRef =
            FirebaseDatabase.getInstance().getReference("tasks");

    // ➕ Add Task
    @PostMapping
public String addTask(@RequestBody Map<String, Object> task) {

    String projectId = (String) task.get("projectId");

    String id = UUID.randomUUID().toString();
    task.put("id", id);

    FirebaseDatabase.getInstance()
        .getReference("projects")
        .child(projectId)
        .child("tasks")
        .child(id)
        .setValueAsync(task);

    return "Task added";
}


    // ❌ Delete Task
    @DeleteMapping("/{id}")
public String deleteTask(@PathVariable String id) {

    String projectId = "project1";

    FirebaseDatabase.getInstance()
        .getReference("projects")
        .child(projectId)
        .child("tasks")
        .child(id)
        .removeValueAsync();

    return "Deleted";
}

    // ✏️ Update Task Status
   @PutMapping("/{id}")
public String updateTask(@PathVariable String id, @RequestBody Map<String, Object> task) {

    String projectId = "project1";

    FirebaseDatabase.getInstance()
        .getReference("projects")
        .child(projectId)
        .child("tasks")
        .child(id)
        .setValueAsync(task);

    return "Updated";
}
}