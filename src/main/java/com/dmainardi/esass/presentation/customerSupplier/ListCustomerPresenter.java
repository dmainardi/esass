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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named
@ViewScoped
public class ListCustomerPresenter implements Serializable{
    @Inject
    CustomerSupplierService customerSupplierService;
    
    private LazyCustomerSupplierDataModel lazyCustomers;
    private List<CustomerSupplier> selectedCustomers;
    
    @PostConstruct
    public void init() {
        lazyCustomers = new LazyCustomerSupplierDataModel(customerSupplierService, Boolean.TRUE, null);
    }
    
    public void deleteCustomers() {
        if (selectedCustomers != null && !selectedCustomers.isEmpty()) {
            for (CustomerSupplier userTemp : selectedCustomers)
                customerSupplierService.deleteCustomerSupplier(userTemp);
        }
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Missing selection", "Select a customer before deleting"));
    }

    public LazyCustomerSupplierDataModel getLazyCustomers() {
        return lazyCustomers;
    }

    public List<CustomerSupplier> getSelectedCustomers() {
        return selectedCustomers;
    }

    public void setSelectedCustomers(List<CustomerSupplier> selectedCustomers) {
        this.selectedCustomers = selectedCustomers;
    }
    
}
