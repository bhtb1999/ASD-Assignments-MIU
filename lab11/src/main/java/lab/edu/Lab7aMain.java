package lab.edu;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lab.edu.model.Address;
import lab.edu.model.Appointment;
import lab.edu.model.Dentist;
import lab.edu.model.Patient;
import lab.edu.model.Role;
import lab.edu.model.Surgery;
import lab.edu.model.User;
import lab.edu.repository.AddressRepository;
import lab.edu.repository.AppointmentRepository;
import lab.edu.repository.DentistRepository;
import lab.edu.repository.PatientRepository;
import lab.edu.repository.RoleRepository;
import lab.edu.repository.SurgeryRepository;
import lab.edu.repository.UserRepository;

@SpringBootApplication
public class Lab7aMain {

    public static void main(String[] args) {
        SpringApplication.run(Lab7aMain.class, args);
    }

    @Bean
    CommandLineRunner initData(AppointmentRepository appointmentRepo,
            PatientRepository patientRepo,
            DentistRepository dentistRepo,
            SurgeryRepository surgeryRepo,
            AddressRepository addressRepo,
            UserRepository userRepo,
            RoleRepository roleRepo) {
        return args -> {
            if (patientRepo.count() > 0 || addressRepo.count() > 0) {
                return;
            }

            // Sample data based on your domain model
            // Addresses
            Address addr1 = new Address(null, "Glasgow", "Sauchiehall St", 10);
            Address addr2 = new Address(null, "Glasgow", "Buchanan St", 20);
            Address addr3 = new Address(null, "Glasgow", "Argyle St", 30);
            addressRepo.save(addr1);
            addressRepo.save(addr2);
            addressRepo.save(addr3);

            // Patients
            Patient p1 = new Patient(null, "Gillian", "White", 30, addr1);
            Patient p2 = new Patient(null, "Jill", "Bell", 25, addr2);
            Patient p3 = new Patient(null, "Ian", "MacKay", 35, addr3);
            Patient p4 = new Patient(null, "John", "Walker", 40, addr1);
            patientRepo.save(p1);
            patientRepo.save(p2);
            patientRepo.save(p3);
            patientRepo.save(p4);

            // Dentists
            Dentist d1 = new Dentist(null, "Tony", "Smith");
            Dentist d2 = new Dentist(null, "Helen", "Pearson");
            Dentist d3 = new Dentist(null, "Robin", "Plevin");
            dentistRepo.save(d1);
            dentistRepo.save(d2);
            dentistRepo.save(d3);

            // Surgeries
            Surgery s1 = new Surgery(null, "S15", addr1);
            Surgery s2 = new Surgery(null, "S10", addr2);
            Surgery s3 = new Surgery(null, "S13", addr3);
            surgeryRepo.save(s1);
            surgeryRepo.save(s2);
            surgeryRepo.save(s3);

            // Appointments
            Appointment a1 = new Appointment(null, LocalDate.of(2013, 9, 12), LocalTime.of(10, 0), p1, d1, s1);
            Appointment a2 = new Appointment(null, LocalDate.of(2013, 9, 12), LocalTime.of(12, 0), p2, d1, s1);
            Appointment a3 = new Appointment(null, LocalDate.of(2013, 9, 12), LocalTime.of(14, 0), p3, d2, s2);
            Appointment a4 = new Appointment(null, LocalDate.of(2013, 9, 14), LocalTime.of(14, 0), p3, d2, s2);
            Appointment a5 = new Appointment(null, LocalDate.of(2013, 9, 14), LocalTime.of(16, 30), p2, d3, s1);
            Appointment a6 = new Appointment(null, LocalDate.of(2013, 9, 15), LocalTime.of(18, 0), p4, d3, s3);
            appointmentRepo.save(a1);
            appointmentRepo.save(a2);
            appointmentRepo.save(a3);
            appointmentRepo.save(a4);
            appointmentRepo.save(a5);
            appointmentRepo.save(a6);

            // Roles
            Role adminRole = new Role(null, "ROLE_ADMIN");
            Role userRole = new Role(null, "ROLE_USER");
            roleRepo.save(adminRole);
            roleRepo.save(userRole);

            // Users
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            adminRoles.add(userRole);
            User admin = new User(null, "admin", "password", adminRoles);

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            User user = new User(null, "user", "password", userRoles);

            userRepo.save(admin);
            userRepo.save(user);
        };
    }
}
