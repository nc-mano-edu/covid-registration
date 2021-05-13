package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.domain.TaskInstanceData;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
import com.edu.mano.covidregistration.repository.TaskInstanceRepository;
import com.edu.mano.covidregistration.tools.AppUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskInstanceService {

    private final TaskInstanceRepository taskInstanceRepository;

    private final TaskService taskService;

    private final TaskInstanceDataService taskInstanceDataService;

    @Autowired
    public TaskInstanceService(TaskInstanceRepository taskInstanceRepository,
                               TaskService taskService,
                               TaskInstanceDataService taskInstanceDataService) {
        this.taskInstanceRepository = taskInstanceRepository;
        this.taskService = taskService;
        this.taskInstanceDataService = taskInstanceDataService;
    }

    private void finishTask(TaskInstance taskInstance) {
        List<TaskInstanceData> data = taskInstance.getData();
        if (data != null && data.stream().allMatch(d -> {
            if (d.getStringValue() != null)
                return true;
            if (d.getImageValue() != null)
                return true;
            if (d.getDateValue() != null)
                return true;
            return d.getNumericValue() != null;
        })) {
            taskInstance.setFinishedTime(AppUtility.getCurrentDate());
            taskInstance.setActive(false);
        }
    }

    public List<TaskInstance> findAll() {
        return taskInstanceRepository.findAll();
    }

    public TaskInstance find(Long id) {
        try {
            return taskInstanceRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(TaskInstance.class, id);
        }
    }

    public List<TaskInstance> findByRequestId(Long id) {
        return taskInstanceRepository.findByRequestRequestId(id);
    }

    @Transactional
    public Long add(TaskInstance taskInstance) {
        Task task = taskInstance.getTask();
        taskService.find(task.getId());

        List<Attribute> attributes = task.getAttributes();
        Long id = taskInstanceRepository.save(taskInstance).getId();

        attributes.forEach(attribute -> {
            TaskInstanceData taskInstanceData = new TaskInstanceData();
            taskInstanceData.setTaskInstance(taskInstance);
            taskInstanceData.setAttribute(attribute);
            taskInstanceDataService.add(taskInstanceData);
        });

        taskInstance.setActive(true);
        finishTask(taskInstance);
        return id;
    }

    @Transactional
    public void delete(Long id) {
        TaskInstance taskInstance = find(id);
        List<TaskInstanceData> data = taskInstance.getData();
        if (data != null) {
            data.forEach(d -> {
                taskInstanceDataService.delete(d.getId());
            });
        }

        taskInstanceRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, TaskInstance taskInstance) {
        find(id);
        taskInstance.setId(id);
        taskService.find(taskInstance.getTask().getId());

        List<TaskInstanceData> data = taskInstance.getData();
        if (data != null) {
            data.forEach(d -> {
                taskInstanceDataService.update(d.getId(), d);
            });
        }

        finishTask(taskInstance);
        taskInstanceRepository.save(taskInstance);
    }

    public List<TaskInstance> findActive() {
        return taskInstanceRepository.findAllByActiveIsTrue();
    }

    @Transactional
    public void terminate(Long id) {
        TaskInstance taskInstance = find(id);
        if (taskInstance.getFinishedTime() == null) {
            taskInstance.setFinishedTime(AppUtility.getCurrentDate());
            taskInstance.setActive(false);
            taskInstanceRepository.save(taskInstance);
        }
    }
}
