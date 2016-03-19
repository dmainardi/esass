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
package com.dmainardi.esass.presentation.user;

import com.dmainardi.esass.business.boundary.UserService;
import com.dmainardi.esass.business.entity.GroupApp;
import com.dmainardi.esass.business.entity.UserApp;
import com.dmainardi.esass.presentation.Authenticator;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
public class UserPresenter implements Serializable {
    @Inject
    UserService userService;
    
    @Inject
    Authenticator authenticator;
    
    private UserApp user;
    
    @PostConstruct
    public void init() {
        System.out.println("Entered user flow");
    }
    
    @PreDestroy
    public void exit() {
        System.out.println("Exited user flow");
    }
        
    public void deleteUserApp(UserApp user) {
        if (user.getUserName().equals(authenticator.getLoggedUser().getUserName()))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You cannot delete yourself."));
        else
            userService.deleteUserApp(user);
    }
    
    public String saveUserApp() {
        userService.saveUserApp(user);
        
        return "/secured/manageUser/users?faces-redirect=true";
    }
    
    public String detailUserApp(String userName) {
        if (userName == null)
            user = new UserApp();
        else {
            if (userName.equals(authenticator.getLoggedUser().getUserName())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You cannot change yourself."));
                return null;
            }
            else
                user = userService.readUserApp(userName);
        }
        
        return "/secured/manageUser/user?faces-redirect=true";
    }

    public UserApp getUserApp() {
        return user;
    }

    public void setUserApp(UserApp userApp) {
        this.user = userApp;
    }
    
    public String addGroup(GroupApp group) {
        this.user.getGroups().add(group);
        return "/secured/manageUser/user?faces-redirect=true";
    }
    
    public void removeGroup(GroupApp group) {
        this.user.getGroups().remove(group);
    }
    
    public List<GroupApp> avaibleGroups(UserApp user) {
        return userService.avaibleGroups(user);
    }
}