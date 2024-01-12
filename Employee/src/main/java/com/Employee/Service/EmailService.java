package com.Employee.Service;

import com.Employee.Model.Email;
import com.Employee.Model.Employee;
import jakarta.mail.MessagingException;

public interface EmailService {

  public void sendEmail(Email email) throws MessagingException;

  public void sendEmailAttachments(Email email) throws MessagingException;

  public void sendCustomEmailTemp(Employee emp);
}
