package com.harri.modules.banking.services;

import com.harri.modules.banking.entities.jpa.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    EntityManager em;

    @Transactional
    public void save(Customer customer) {
        em.persist(customer);
    }

    @Transactional
    public void update(Customer customer) {
        em.merge(customer);
    }

    @Transactional
    public void remove(Customer customer) {
        em.remove(customer);
    }

    public List<Customer> findAll() {
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    @Transactional
    public boolean deleteById(Long id) {
        Customer customer = this.findById(id);

        if (customer == null) {
            return false;
        }
        em.remove(customer);
        return true;
    }

}
