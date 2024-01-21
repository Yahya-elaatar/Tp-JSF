/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import entities.Employee;
import entities.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import sessions.EmployeeFacade;
import sessions.ServiceFacade;

/**
 *
 * @author yaxay
 */
@ManagedBean
@RequestScoped
public class ServiceBean {
    
    private Service service;
    private List<Service> services;
    private ServiceFacade  ss;
    private EmployeeFacade ef;
    private List<Employee> employees;

    /**
     * Creates a new instance of ServiceBean
     */
    public ServiceBean() {
        service = new Service();
        ss = new ServiceFacade();
        ef=new EmployeeFacade();
        employees=new ArrayList<>();
        
        
    }

    public ServiceFacade getSs() {
        return ss;
    }

    public void setSs(ServiceFacade ss) {
        this.ss = ss;
    }

    public EmployeeFacade getEf() {
        return ef;
    }

    public void setEf(EmployeeFacade ef) {
        this.ef = ef;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
    public void onCreateAction(){
        
        ss.create(service);
        service = new Service();
    }

    public List<Service> getServices() {
        if(services == null){
            services = ss.getAll();
            System.out.println("GetServices");
            ss.getAll().forEach(System.out::println);
        }
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
    
    public String onDeleteAction() {
        service.setEmployes(null);
        Service serviceToDelete=ss.getById(service.getId());
        serviceToDelete.setEmployes(null);
        ss.delete(serviceToDelete);
        return null;
    }
    
      public void onEdit(RowEditEvent event) {
        service = (Service) event.getObject();
        service.setEmployes(null);
        ss.update(service);
    }
    
    public List<Employee> load(){
        /*employees=ef.getAll().stream().filter(e -> e.getService().getId()==service.getId())
        .collect(Collectors.toList());*/
         for(Employee e:ef.getAll())
            if(e.getService().getId()==service.getId())
                employees.add(e);
        
        System.out.print("visited");
        System.out.print(employees);
        return employees;
    }
    
     public void onCancel(RowEditEvent event) {
        
    }
     
             public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries series = new ChartSeries();
        series.setLabel("Services");
        model.setAnimate(true);
        for (Service s : ss.getAll()) {
            series.set(s.getNom(), s.getEmployes().size());
        }
        model.addSeries(series);

        return model;
    }
}
