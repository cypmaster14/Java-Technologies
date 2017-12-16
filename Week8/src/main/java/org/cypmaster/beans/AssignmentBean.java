package org.cypmaster.beans;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

/**
 * Created by Ciprian at 12/16/2017
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class AssignmentBean {

    @PostConstruct
    public void init() {

    }
}
