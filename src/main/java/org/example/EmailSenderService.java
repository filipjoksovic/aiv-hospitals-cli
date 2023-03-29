package org.example;

import org.example.vao.Patient;

public interface EmailSenderService {

    void notifyDoctor(Patient patient) throws Exception;

    void notifyPatient(Patient patient) throws Exception;

    void send(String to, String from, String subject, String body) throws Exception;

}
