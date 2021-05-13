package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.enums.TreatmentState;
import com.edu.mano.covidregistration.repository.UserRequestRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Profile("test")
public class MockUserRequestRepository implements UserRequestRepository {

    private List<UserRequest> userRequests;

    public MockUserRequestRepository() {
        this.userRequests = new ArrayList<>(Collections.singletonList(new UserRequest(1l, null, null, TreatmentState.STARTED, "recommendations", null, null)));
    }

    @Override
    public List<UserRequest> findAll() {
        return this.userRequests;
    }

    @Override
    public UserRequest findUserRequestByRequestId(Long id) {
        String str = String.valueOf(id);
        int newId = Integer.parseInt(str);

        UserRequest neededUserRequest = this.userRequests.get(newId - 1);
        return neededUserRequest;
    }

    @Override
    public List<UserRequest> findUserRequestsByUserId(Long userId) {
        return userRequests;
    }

    @Override
    public List<UserRequest> findAllByEndDateIsNull() {
        return userRequests;
    }

    @Override
    public Iterable<UserRequest> findAllById(Iterable<Long> iterable) {
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
    public void delete(UserRequest userRequest) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserRequest> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends UserRequest> S save(S s) {
        return null;
    }

    @Override
    public <S extends UserRequest> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<UserRequest> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
