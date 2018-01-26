package org.launchcode.dispatchtransfer.models.data;


import org.launchcode.dispatchtransfer.models.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface PatientDao extends CrudRepository<Patient, Integer> {


}
