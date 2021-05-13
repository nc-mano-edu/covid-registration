package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.domain.TaskInstanceData;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.enums.TreatmentState;
import com.edu.mano.covidregistration.repository.TaskInstanceDataRepository;
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
public class MockTaskInstanceDataRepository implements TaskInstanceDataRepository {

    private List<TaskInstanceData> taskInstanceData;

    private final UserRequest userRequest = new UserRequest(1L, null, null, TreatmentState.STARTED, "recommendations", null, null);

    MockTaskInstanceDataRepository() {
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

        TaskInstance taskInstance = new TaskInstance(1L,
                new Task(1L, "Some task", "* * * * *", "Some description", Collections.singletonList(
                        new Attribute(1L, "User age",
                                new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?")))
                ), userRequest, createdDate, finishedDate, false, null);
        Attribute attribute = new Attribute(1L, "User age",
                new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?"));

        this.taskInstanceData = new ArrayList<>(Collections.singletonList(
                new TaskInstanceData(1L, taskInstance, attribute, null, null, null, 21)
        ));
    }

    @Override
    public List<TaskInstanceData> findAll() {
        return taskInstanceData;
    }

    @Override
    public Optional<TaskInstanceData> findById(Long id) {
        return taskInstanceData.stream()
                .filter(attr -> id.equals(attr.getId()))
                .findAny();
    }

    @Override
    public <S extends TaskInstanceData> S save(S s) {
        long id = taskInstanceData.get(taskInstanceData.size() - 1).getId() + 1;
        s.setId(id);
        taskInstanceData.add(s);
        return s;
    }

    @Override
    public <S extends TaskInstanceData> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<TaskInstanceData> findAllById(Iterable<Long> iterable) {
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
    public void delete(TaskInstanceData taskInstanceD) {
        taskInstanceData.remove(taskInstanceD);
    }

    @Override
    public void deleteAll(Iterable<? extends TaskInstanceData> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
