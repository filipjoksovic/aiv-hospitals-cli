package com.hospital.hospital.interfaces;

import com.hospital.hospital.vao.Patient;

import java.util.List;

public interface IPatientServiceRemote {
    List<Patient> getAll();

    Patient find(int patientId);

    boolean addDoctorToPatient(int patientId, int doctorId) throws Exception;
}
