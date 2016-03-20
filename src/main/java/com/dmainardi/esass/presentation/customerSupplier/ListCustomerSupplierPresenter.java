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
package com.dmainardi.esass.presentation.customerSupplier;

import com.dmainardi.esass.business.boundary.customerSupplier.CustomerSupplierService;
import com.dmainardi.esass.business.entity.customerSupplier.CustomerSupplier;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named
@ViewScoped
public class ListCustomerSupplierPresenter implements Serializable{
    @Inject
    CustomerSupplierService customerSupplierService;
    
    List<CustomerSupplier> customerSuppliers;
    
    @PostConstruct
    public void init() {
        customerSuppliers = customerSupplierService.listCustomerSuppliers();
    }

    public List<CustomerSupplier> getCustomerSuppliers() {
        return customerSuppliers;
    }

    public void setCustomerSuppliers(List<CustomerSupplier> customerSuppliers) {
        this.customerSuppliers = customerSuppliers;
    }
    
}
