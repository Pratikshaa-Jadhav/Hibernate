package com.Anudip.hibernateonetomany1;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Configuration con1=new Configuration().configure().addAnnotatedClass(Doctor.class).addAnnotatedClass(Appointment.class);
        SessionFactory sessionFactory=con1.buildSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction t1=session.beginTransaction();
        Doctor d1=new Doctor();
        d1.setDname("Fatima Bangi");
        d1.setQualification("MD");
        d1.setSpeciality("Medicine");
        List<Appointment> appointments=new ArrayList<>();
        Appointment ap1=new Appointment();
        ap1.setFullName("Jabeen ");
        ap1.setLocation("Pune");
        ap1.setContactNumber("9359689920");
        ap1.setSpeciality("Medicine");
        ap1.setDoctor(d1);
        Appointment ap2=new Appointment();
        ap2.setFullName("Pratiksha ");
        ap2.setLocation("Pune");
        ap2.setContactNumber("2040605610");
        ap2.setSpeciality("Medicine");
        ap2.setDoctor(d1);
        appointments.add(ap1);
        appointments.add(ap2);
        d1.setAppointments(appointments);
        session.persist(d1);
        t1.commit();
        
    }
}