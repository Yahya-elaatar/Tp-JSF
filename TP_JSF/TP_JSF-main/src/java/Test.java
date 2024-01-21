
import entities.Employee;
import entities.Service;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import sessions.AbstractFacade;
import sessions.EmployeeFacade;
import sessions.ServiceFacade;
import utiles.HibernateUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yaxay
 */
public class Test {
    
     public static void main(String[] args) {
        HibernateUtils.getSessionFactory();
        
        
        
         ServiceFacade ss=new ServiceFacade();
         EmployeeFacade ef=new EmployeeFacade();
       /*  ef.create(new Employee("Otmane", "Elkastali", new Date(), "photo"));
         ef.create(new Employee("Otmane2", "Elkastali2", new Date(), "photo2"));
         ef.create(new Employee("Otmane3", "Elkastali3", new Date(), "photo3"));
         ef.create(new Employee("Otmane4", "Elkastali4", new Date(), "photo4"));
         
         ef.getAll().forEach(System.out::println);*/
        /* Employee employeeToUpdate=ef.getById(4);
         employeeToUpdate.setService(ss.getById(3));
         employeeToUpdate.setManager(ef.getById(1));
         ef.update(employeeToUpdate);
         
         ef.getAll().forEach(System.out::println);*/
List<Employee> collaborateurs = ef.getAll().stream()
        .filter(e -> e.getManager() != null && !e.getManager().equals(ef.getById(1)))
        .filter(e -> 1 == e.getManager().getId())
        .collect(Collectors.toList());


collaborateurs.forEach(c -> System.out.println(c));
  /*   Service s=new Service("Hr");
     ss.create(s);
     ss.create(new Service("R&S"));
     ss.create(new Service("GD "));
     ss.create(new Service("EP"));
     ss.create(new Service("F&D"));*/
     
     //ss.getAll().forEach( so -> System.out.println(so));
     /*
         System.err.println("FTER DELETE");
     ss.delete(ss.getById(2));
     ss.getAll().forEach( System.out::println);
     */
     
     
     
      /*  System.err.println("FTER UPDATE");
        Service serviceToUpdate =ss.getById(2);
        serviceToUpdate.setNom("bokoharam");
        ss.update(serviceToUpdate);
       ss.getAll().forEach( System.out::println);
              */
     
     
    }
     
    
     
     
    
}
