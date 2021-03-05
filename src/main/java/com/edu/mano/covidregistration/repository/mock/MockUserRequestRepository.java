package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.Symptom;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.repository.UserRequestRepository;
import org.hibernate.mapping.Collection;
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
    private List<Symptom> symptoms;


    @Override
    public List<UserRequest> findAll() {
        this.symptoms = new ArrayList<>(Collections.singletonList(
                new Symptom(1L, "cough", "red throat",null )));

        this.userRequests = new ArrayList<>(Collections.singletonList(
                new UserRequest(1l, null,null, "in hospital",
                        new User(1l,"name","middleName","lastName",null,"A111","7777"),
                        symptoms )
        ));
        return userRequests;
    }

    @Override
    public Optional<UserRequest> findById(Long userRequestId) {
        return userRequests.stream()
                .filter(attr -> userRequestId.equals(attr.getRequestId()))
                .findAny();
    }

        @Override
    public <S extends UserRequest> S save(S s) {
        Long symptomId = userRequests.get(userRequests.size()-1).getRequestId() + 1;
        s.setRequestId(1l);
        return s;
    }

    @Override
    public <S extends UserRequest> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }


    @Override
    public boolean existsById(Long aLong) {
        return false;
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
    public void deleteById(Long id) {
        this.userRequests.remove(id);
    }

    @Override
    public void delete(UserRequest userRequest) {
        this.userRequests.remove(userRequest.getRequestId());
    }

    @Override
    public void deleteAll(Iterable<? extends UserRequest> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public UserRequest findUserRequestByRequestId(Long id) {
        return null;
    }
}
