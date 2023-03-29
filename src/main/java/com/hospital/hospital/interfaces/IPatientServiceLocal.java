package com.hospital.hospital.interfaces;

import com.hospital.hospital.vao.Patient;
import jakarta.ejb.Local;

public interface IPatientServiceLocal {
    Patient save(Patient patient);

    Patient update(Patient patient);

    int delete(int patientId);
}
