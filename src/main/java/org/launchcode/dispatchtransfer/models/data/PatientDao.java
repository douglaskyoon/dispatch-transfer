package org.launchcode.dispatchtransfer.models.data;


import org.launchcode.dispatchtransfer.models.Patient;
import org.springframework.stereotype.Repository;


@Repository

public interface PatientDao extends CrudRepository<Patient, Integer> {


}
