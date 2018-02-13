package org.launchcode.dispatchtransfer.models.data;


import org.launchcode.dispatchtransfer.models.SocialWorker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SocialWorkerDao extends CrudRepository<SocialWorker, Integer> {

    public SocialWorker findByUsername(String username);


}