package org.example;

import com.hospital.hospital.dto.AddPatientReqDto;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainRest {
    static final URI DOCTORS_BASE = URI.create("http://localhost:8080/hospital-1.0-SNAPSHOT/api/v1/doctors/");
    static final URI DOCTORS_ALL = URI.create("http://localhost:8080/hospital-1.0-SNAPSHOT/api/v1/doctors/all/");
    static final URI DOCTORS_ADD = URI.create("http://localhost:8080/hospital-1.0-SNAPSHOT/api/v1/doctors/addPatient/");
    static final URI DOCTORS_REMOVE = URI.create("http://localhost:8080/hospital-1.0-SNAPSHOT/api/v1/doctors/removePatient/");

    HttpClient client = HttpClient.newBuilder().build();
    Jsonb jsonb = JsonbBuilder.create();

    public void allDoctors() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(DOCTORS_ALL).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void doctorPatients(int id) throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DOCTORS_BASE_URI + String.valueOf(id) + "/patients")).build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
    }

    public void addPatient(AddPatientReqDto reqDto) throws IOException, InterruptedException {
        Jsonb jsonb = JsonbBuilder.create();

        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(jsonb.toJson(reqDto))).header("Content-Type", "\tapplication/json").uri(DOCTORS_ADD).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }

    public void removePatient(AddPatientReqDto reqDto) throws IOException, InterruptedException {
        Jsonb jsonb = JsonbBuilder.create();

        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(jsonb.toJson(reqDto))).header("Content-Type", "\tapplication/json").uri(DOCTORS_REMOVE).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public static void main(String[] args) {
//        try {
//            System.out.println("All doctors:");
//            allDoctors();
//            System.out.println();
//            System.out.println("Doctor 1 patients:");
//            doctorPatients(1);
//            System.out.println();
//
//            System.out.println("Add patient 1 to doctor 1:");
//            addPatient(new AddPatientReqDto(1, 1));
//            System.out.println();
//
//            System.out.println("Doctor 1 patients:");
//            doctorPatients(1);
//            System.out.println();
//
//            System.out.println("Remove patient 1 from doctor 1:");
//            removePatient(new AddPatientReqDto(1, 1));
//            System.out.println();
//
//            System.out.println("Doctor 1 patients:");
//            doctorPatients(1);
//            System.out.println();
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }

        MainRest mainRest = new MainRest();

        System.out.println("Chose action:");
        System.out.println("1. All doctors");
        System.out.println("2. Doctor patients");
        System.out.println("3. Add patient");
        System.out.println("4. Remove patient");
        System.out.println("5. Exit");

        try {
            BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(System.in));
            int action = Integer.parseInt(bufferedInputStream.readLine());
            while (action != 5) {
                System.out.println(action);
                switch (action) {
                    case 1 -> mainRest.allDoctors();
                    case 2 -> mainRest.doctorPatients(1);
                    case 3 -> mainRest.addPatient(new AddPatientReqDto(1, 1));
                    case 4 -> mainRest.removePatient(new AddPatientReqDto(1, 1));
                    case 5 -> System.exit(0);
                }
                action = Integer.parseInt(bufferedInputStream.readLine());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
