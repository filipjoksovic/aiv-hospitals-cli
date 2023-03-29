package org.example;

import com.hospital.hospital.interfaces.IDoctorServiceRemote;
import com.hospital.hospital.interfaces.IPatientServiceRemote;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws NamingException, IOException {
        System.out.println("Hello world!");
        Properties props = new Properties();
        props.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
        props.put("java.naming.provider.url", "http-remoting://127.0.0.1:8080");
        props.put("jboss.naming.client.ejb.context", "true");
        props.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
        InitialContext ctx = new InitialContext(props);

        IDoctorServiceRemote doctorBean = (IDoctorServiceRemote) ctx.lookup("ejb:/hospital-1.0-SNAPSHOT/DoctorService!com.hospital.hospital.interfaces.IDoctorServiceRemote");
        IPatientServiceRemote patientBean = (IPatientServiceRemote) ctx.lookup("ejb:/hospital-1.0-SNAPSHOT/PatientService!com.hospital.hospital.interfaces.IPatientServiceRemote");
        List<Doctor> doctors = doctorBean.getAll();
        List<Patient> patients = patientBean.getAll();

        System.out.println("Available doctors:");
        for (Doctor doctor : doctors) {
            System.out.println(doctor.getId() + ")");
            System.out.println("Name: " + doctor.getFname() + " " + doctor.getLname());
            System.out.println("Email: " + doctor.getEmail());
            System.out.println("Phone: " + doctor.getPhone());
            System.out.println("Date of birth: " + doctor.getDob());
            System.out.println("Patient quota: " + doctor.getMaxPatients());
            System.out.println("Current patients: " + doctor.getPatients().size());

            System.out.println();
        }

        System.out.println("Available patients:");
        for (Patient patient : patients) {
            System.out.println(patient.getId() + ")");
            System.out.println("Name: " + patient.getFname() + " " + patient.getLname());
            System.out.println("Email: " + patient.getEmail());
            System.out.println("Phone: " + patient.getPhone());
            System.out.println("Date of birth: " + patient.getDob());
            System.out.println("Doctor: " + (patient.getDoctor() != null ? patient.getDoctor().getFname() + " " + patient.getDoctor().getLname() : "No doctor"));
            System.out.println();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Reading data using readLine
        String choice = "";
        System.out.println("Input doctor id, followed by patient id, to add a patient to the doctor (Ex: 1,1)");

        while (true) {
            choice = reader.readLine();
            if (choice.equals("EXIT")) {
                return;
            }
            String[] parsed = choice.split(",");
            if (parsed.length != 2) {
                System.out.println("Wrong input");
                continue;
            }

            try {
                int doctorId = Integer.parseInt(parsed[0]);
                int patientId = Integer.parseInt(parsed[1]);

                boolean result = doctorBean.addPatient(doctorId, patientId);
                if (result) {
                    System.out.println("Patient added");
                } else {
                    System.out.println("Failed to add patient");
                }
            } catch (Exception e) {
                System.out.println("Wrong input. Error while parsing");
                continue;
            }
        }
    }
}