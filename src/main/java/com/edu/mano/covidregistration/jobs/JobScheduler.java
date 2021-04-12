package com.edu.mano.covidregistration.jobs;

import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
@EnableScheduling
public class JobScheduler {

    private final TaskService taskService;
    private final TaskScheduler scheduler;

    @Autowired
    public JobScheduler(TaskService taskService, TaskScheduler scheduler) {
        this.taskService = taskService;
        this.scheduler = scheduler;

        System.out.println("[SCH] scheduling tasks");

        taskService.findAll().forEach(task -> {
            //will work every minute, later expression should be changed to task.getSchedule()
            scheduler.schedule(new ReschedulingTask(task), new CronTrigger("0 * * * * *"));
            task.getSchedule();
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

            //here instantiate new task instance for current task for each active User Request
        }
    }

}
