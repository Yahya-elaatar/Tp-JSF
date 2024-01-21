/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Employee;
import java.util.List;
import org.hibernate.Session;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import utiles.HibernateUtils;

/**
 *
 * @author yaxay
 */
public class EmployeeFacade extends AbstractFacade<Employee>{

    @Override
    protected Class<Employee> getEntityClass() {
        return Employee.class;
    }
    public List<Object[]> nbEmployes(){
        List<Object[]> employes = null;
        Session session  = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        employes  = session.createQuery("select count(m), m.service.nom from Employee m group by m.service.nom").list();
         
        session.getTransaction().commit();
        
        
        return employes;
    }
    

 
}