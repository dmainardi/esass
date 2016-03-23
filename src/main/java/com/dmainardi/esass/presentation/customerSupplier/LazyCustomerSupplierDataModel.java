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
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
public class LazyCustomerSupplierDataModel extends LazyDataModel<CustomerSupplier>{
    
    private final CustomerSupplierService service;
    private final Boolean isCustomer;
    private final Boolean isSupplier;

    public LazyCustomerSupplierDataModel(CustomerSupplierService service, Boolean isCustomer, Boolean isSupplier) {
        this.service = service;
        this.isCustomer = isCustomer;
        this.isSupplier = isSupplier;
    }

    @Override
    public Object getRowKey(CustomerSupplier object) {
        return object.getId();
    }

    @Override
    public CustomerSupplier getRowData(String rowKey) {
        try {
            return service.readCustomerSupplier(Long.parseLong(rowKey));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public List<CustomerSupplier> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<CustomerSupplier> result = service.listCustomerSuppliers(isCustomer, isSupplier, first, pageSize, filters);
        this.setRowCount(service.getCustomerSuppliersNumber(isCustomer, isSupplier, first, pageSize, filters).intValue());
        
        return result;
    }
    
}
