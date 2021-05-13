package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.enums.TreatmentState;
import com.edu.mano.covidregistration.repository.TaskInstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Repository
@Profile("test")
public class MockTaskInstanceRepository implements TaskInstanceRepository {

    private List<TaskInstance> taskInstances;

    private final UserRequest userRequest = new UserRequest(1L, null, null, TreatmentState.STARTED, "recommendations", null, null);

    MockTaskInstanceRepository() {
        Date createdDate, finishedDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("RUS"));
            createdDate = sdf.parse("2021-02-25 12:00:00");
            finishedDate = sdf.parse("2021-02-25 13:00:00");
        } catch (ParseException e) {
            createdDate = null;
            finishedDate = null;
        }

        Logger logger = LoggerFactory.getLogger(MockTaskInstanceRepository.class);

        logger.info(createdDate.toString());
        logger.info(finishedDate.toString());


        this.taskInstances = new ArrayList<>(Collections.singletonList(
                new TaskInstance(1L,
                        new Task(1L, "Some task", "* * * * *", "Some description", Collections.singletonList(
                                new Attribute(1L, "User age",
                                        new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?")))
                        ), userRequest, createdDate, finishedDate, false, null)));
    }

    @Override
    public List<TaskInstance> findAll() {
        return taskInstances;
    }

    @Override
    public Optional<TaskInstance> findById(Long id) {
        return taskInstances.stream()
                .filter(attr -> id.equals(attr.getId()))
                .findAny();
    }

    @Override
    public <S extends TaskInstance> S save(S s) {
        long id = taskInstances.get(taskInstances.size() - 1).getId() + 1;
        s.setId(id);
        taskInstances.add(s);
        return s;
    }

    @Override
    public <S extends TaskInstance> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<TaskInstance> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(TaskInstance taskInstance) {
        taskInstances.remove(taskInstance);
    }

    @Override
    public void deleteAll(Iterable<? extends TaskInstance> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<TaskInstance> findByRequestRequestId(Long id) {
        return null;
    }

    @Override
    public List<TaskInstance> findAllByActiveIsTrue() {
        return new ArrayList<>();
    }
}
