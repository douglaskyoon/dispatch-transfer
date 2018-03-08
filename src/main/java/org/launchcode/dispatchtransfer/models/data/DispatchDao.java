package org.launchcode.dispatchtransfer.models.data;

import org.launchcode.dispatchtransfer.models.Dispatcher;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DispatchDao extends CrudRepository<Dispatcher, Integer> {


    public Dispatcher findByUsername(String username);


}
