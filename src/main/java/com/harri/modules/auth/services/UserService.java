package com.harri.modules.auth.services;

import com.harri.modules.auth.entities.jpa.User;
import com.harri.modules.auth.utils.TokenGenerator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;


@ApplicationScoped
public class UserService {

    @Inject
    TokenGenerator tokenGenerator;

    @Inject
    EntityManager em;

    @Transactional
    public void save(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(BCrypt.hashpw(password, BCrypt.gensalt()));
        em.persist(user);
    }

    public String authenticate(String username, String password) {
        User user = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();

        if (user != null && BCrypt.checkpw(password, user.getPasswordHash())) {
            return tokenGenerator.generateToken(username);
        } else {
            throw new SecurityException("Incorrect user or password.");
        }
    }


}

