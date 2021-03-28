package com.edu.mano.covidregistration.repository.mock;

import com.edu.mano.covidregistration.domain.Roles;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.domain.UserRoles;
import com.edu.mano.covidregistration.repository.UserRolesRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class MockUserRolesRepository implements UserRolesRepository {

    private UserRoles newUserRole;

    private List<UserRoles> userRoles;

    public MockUserRolesRepository() throws ParseException {

        this.newUserRole = new UserRoles(1L, (new User(1L, "John", "Jo", "Jameson", null, new SimpleDateFormat("dd.MM.yyyy").parse("28.04.2001"), null, null)),Collections.singletonList(new Roles(30L, "Liquid snake", "Not a Solid Ocelot")));

        this.userRoles = new ArrayList<>(Collections.singletonList(newUserRole));
    }

    @Override
    public <S extends UserRoles> S save(S s) {

        userRoles.add(s);
        return s;
    }

    @Override
    public <S extends UserRoles> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<UserRoles> findById(Long id) {

        if (id.equals(newUserRole.getUserRoleId())) {

            return Optional.of(newUserRole);

        } else {

            return Optional.empty();
        }

    }

    @Override
    public boolean existsById(Long id) {

        return id.equals(newUserRole.getUserRoleId());
    }

    @Override
    public List<UserRoles> findAll() {

        return userRoles;
    }

    @Override
    public Iterable<UserRoles> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {

        for (UserRoles userRole : userRoles){

            if (id.equals(userRole.getUserRoleId())){

                userRoles.remove(userRole);
            }
        }
    }

    @Override
    public void delete(UserRoles userRoles) {

        if (this.userRoles.contains(userRoles)){

            this.userRoles.remove(userRoles);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends UserRoles> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public UserRoles findById(long id) {

        for (UserRoles userRole : userRoles){

            if (userRole.getUserRoleId() == id){

                return userRole;
            }
        }
        return null;
    }
}
