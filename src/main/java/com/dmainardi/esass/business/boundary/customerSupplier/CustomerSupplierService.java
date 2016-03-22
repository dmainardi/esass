/*
 * Copyright (C) 2016 Davide Mainardi <ingmainardi at live.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dmainardi.esass.business.boundary.customerSupplier;

import com.dmainardi.esass.business.entity.customerSupplier.CustomerSupplier;
import com.dmainardi.esass.business.entity.customerSupplier.CustomerSupplier_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Stateless
public class CustomerSupplierService {
    
    @PersistenceContext
    EntityManager em;
    
    public CustomerSupplier saveCustomerSupplier(CustomerSupplier customerSupplier) {
        if (customerSupplier.getId() == null)
            em.persist(customerSupplier);
        else
            return em.merge(customerSupplier);
        
        return null;
    }
    
    public CustomerSupplier readCustomerSupplier(Long id) {
        return em.find(CustomerSupplier.class, id);
    }
    
    public void deleteCustomerSupplier(CustomerSupplier customerSupplier) {
        em.remove(em.merge(customerSupplier));
    }

    public List<CustomerSupplier> listCustomerSuppliers(Boolean isCustomer, Boolean isSupplier) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CustomerSupplier> query = cb.createQuery(CustomerSupplier.class);
        Root<CustomerSupplier> root = query.from(CustomerSupplier.class);
        CriteriaQuery<CustomerSupplier> select = query.select(root).distinct(true);
        
        List<Predicate> conditions = new ArrayList<>();
        //customer
        if (isCustomer != null) {
            conditions.add(cb.equal(root.get(CustomerSupplier_.isCustomer), isCustomer));
        }
        //supplier
        if (isSupplier != null) {
            conditions.add(cb.equal(root.get(CustomerSupplier_.isSupplier), isSupplier));
        }
        if (!conditions.isEmpty()) {
            query.where(conditions.toArray(new Predicate[conditions.size()]));
        }
        
        return em.createQuery(select).getResultList();
    }
    
}
