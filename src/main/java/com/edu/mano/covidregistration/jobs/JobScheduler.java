package com.edu.mano.covidregistration.jobs;

import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.service.TaskInstanceService;
import com.edu.mano.covidregistration.service.TaskService;
import com.edu.mano.covidregistration.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

@Configuration
@EnableScheduling
@Profile("!test")
public class JobScheduler {

    private final TaskService taskService;
    private final TaskInstanceService taskInstanceService;
    private final UserRequestService userRequestService;
    private final TaskScheduler scheduler;

    @Autowired
    public JobScheduler(TaskService taskService, TaskInstanceService taskInstanceService, UserRequestService userRequestService, TaskScheduler scheduler) {
        this.taskService = taskService;
        this.taskInstanceService = taskInstanceService;
        this.userRequestService = userRequestService;
        this.scheduler = scheduler;

        System.out.println("[SCH] scheduling tasks");

        rescheduleTasks(taskService, scheduler);
    }

    public void rescheduleTasks(TaskService taskService, TaskScheduler scheduler) {
        //todo implement removing already scheduled tasks
        taskService.findAll().forEach(task -> {
            scheduler.schedule(new ReschedulingTask(task), new CronTrigger(task.getSchedule()));
        });
    }

    private class ReschedulingTask implements Runnable {
        private final Task task;

        public ReschedulingTask(Task task) {
            this.task = task;
        }

        @Override
        public void run() {
            System.out.println("[SCH] run task for " + task);

            userRequestService.findActive().forEach(request -> taskInstanceService.add(
                        new TaskInstance(null, task, request, new Date(), null, true, null)
            ));
        }
    }

}
