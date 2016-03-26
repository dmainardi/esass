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
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named
@FlowScoped("supplier")
public class SupplierPresenter implements Serializable {
    @Inject
    CustomerSupplierService customerSupplierService;

    private CustomerSupplier customerSupplier;
    
    private Long idCustomerSupplier;

    public String saveCustomerSupplier() {
        customerSupplierService.saveCustomerSupplier(customerSupplier);

        return "exitFlow";
    }

    public void detailCustomerSupplier() {
        if (idCustomerSupplier == null || idCustomerSupplier == 0)
            customerSupplier = new CustomerSupplier();
        else
            customerSupplier = customerSupplierService.readCustomerSupplier(idCustomerSupplier);
    }

    public CustomerSupplier getCustomerSupplier() {
        return customerSupplier;
    }

    public void setCustomerSupplier(CustomerSupplier customerSupplier) {
        this.customerSupplier = customerSupplier;
    }

    public Long getIdCustomerSupplier() {
        return idCustomerSupplier;
    }

    public void setIdCustomerSupplier(Long idCustomerSupplier) {
        this.idCustomerSupplier = idCustomerSupplier;
    }
    
}
