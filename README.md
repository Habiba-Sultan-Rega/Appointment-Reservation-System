# AppointmentReservationSystem
Appointment-Reservation-System is a generic Reservation system. 

# Table of contents
* General info
* Technologies
* Setup Instructions 

# General info
This project is Generic back-end Spring application which helps people to make a reservation of an appointment of any type.
# Technologies  
Project is created with:

  * Backend technologies : Springboot
  * Database: Mysql
  * Security: JWT
  * Eureka Server
  * Eureka Client
  * email: Assynchronous JMS
  * Active Message Queue
  * Mailgun

# Backend you need to install intelliJ
  * $ cd ../Appointment-Reservation-System 
  * $ Open and run with intelliJ

  * Download apache-active mq

     * C:\Downloads\apache-activemq-5.16.2\bin\win64

  * Start the Active message queue service by navigating to bin directory/win64 n executing command : activemq start
    this opens up a jms tcp connection on the default port 61616, defined in email service application.yaml

  * Open http://127.0.0.1:8161/admin/queues.jsp
      
  * Registry-spring is the registry

  * Emailservice is the client

  * AppointmentReservation Systemis the server 



