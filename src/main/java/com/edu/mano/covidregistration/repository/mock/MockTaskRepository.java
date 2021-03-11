package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.repository.TaskRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class MockTaskRepository implements TaskRepository {

    private List<Task> tasks;

    MockTaskRepository() {
        this.tasks = new ArrayList<>(Collections.singletonList(
                new Task(1L, "Some task", "* * * * *", "Some description", Collections.singletonList(
                        new Attribute(1L, "User age",
                                new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?")))
                )));
    }


    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(attr -> id.equals(attr.getId()))
                .findAny();
    }

    @Override
    public <S extends Task> S save(S s) {
        long id = tasks.get(tasks.size() - 1).getId() + 1;
        s.setId(id);
        tasks.add(s);
        return s;
    }

    @Override
    public <S extends Task> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Task> findAllById(Iterable<Long> iterable) {
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
    public void delete(Task task) {
        tasks.remove(task);
    }

    @Override
    public void deleteAll(Iterable<? extends Task> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
