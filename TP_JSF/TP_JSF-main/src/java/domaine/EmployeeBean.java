/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import entities.Employee;
import entities.Service;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import sessions.EmployeeFacade;
import sessions.ServiceFacade;

/**
 *
 * @author yaxay
 */
@ManagedBean
@RequestScoped
public class EmployeeBean {
    
    
    private Employee employee;
    private Service service;
    private Employee manager;
    private EmployeeFacade employeeFacade;
    private List<Service> services;
    private List<Employee> employees;
    private List<Employee> subordinates;
    private List<Employee> managers;
    private ServiceFacade serviceFacade;
   // private  BarChartModel barModel;
    
     

    public EmployeeBean() {
        
        employee = new Employee();
        employeeFacade = new EmployeeFacade();
        service=new Service();
        serviceFacade= new ServiceFacade();
        manager=new Employee();
        services = new ArrayList<>();
        subordinates = new ArrayList<>();
        

        employees = employeeFacade.getAll();
        managers = employeeFacade.getAll();  
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public EmployeeFacade getEmployeService() {
        return employeeFacade;
    }

    public void setEmployeService(EmployeeFacade employeService) {
        this.employeeFacade = employeService;
    }

    public EmployeeFacade getEmployeeFacade() {
        return employeeFacade;
    }

    public void setEmployeeFacade(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }
    

    public List<Service> getServices() {
        if(services==null)
            services=serviceFacade.getAll();
          else {
        services.clear(); // Clear the list before adding elements
        services.addAll(serviceFacade.getAll());
    }
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Employee> getEmployees() {
         if(employees==null)
            employees=employeeFacade.getAll();
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    public List<Employee> getManagers() {
          if(managers==null){
            managers=employeeFacade.getAll();
        }
        return managers;
    }

    public void setManagers(List<Employee> managers) {
        this.managers = managers;
    }

    public ServiceFacade getServiceFacade() {
        return serviceFacade;
    }

    public void setServiceFacade(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

   
    
    
    
    
    public String onCreateAction() {
        System.out.println("onCreateAction invoked");
        employee.setManager(manager);
        employee.setService(service);
        employee.setPhoto("https://i.stack.imgur.com/l60Hf.png");
        employeeFacade.create(employee);
        employee  = new Employee();
        return null;
    }  
    
    public void onDeleteAction() {
        System.out.println("onDeleteAction invoked");
        employee.setService(null);
        employee.setManager(null);
        employeeFacade.delete(employee);

    }  
    
    public void onEdit(RowEditEvent event) {
        employee=(Employee)event.getObject();
        service = serviceFacade.getById(service.getId());
        employee.setService(service);
        if(manager.getId()!=employee.getId())
        employee.setManager(manager);
        employeeFacade.update(employee);
    }
    
    
    
    public List<Employee> loadSubordinates(){
    subordinates=new ArrayList<Employee>();
    if(manager!=null){
        System.out.print("chef info");
        System.out.print(manager.getId());
        
        
        /* subordinates = employeeFacade.getAll().stream()
        .filter(e -> e.getManager() != null && !e.getManager().equals(manager))
        .filter(e -> 1 == e.getManager().getId())
        .collect(Collectors.toList());
         */
        employeeFacade.getAll().stream().map((e) -> {
            System.out.print(e.getManager());
            return e;
        }).filter((e) -> (e.getManager()!=null && e.getManager()!= manager)).filter((e) -> (manager.getId()==e.getManager().getId())).forEach((e) -> {
            subordinates.add(e);
        });
    System.out.print("visited load");
    System.out.print(subordinates);
    return subordinates;
    }
    else
    {
        System.out.print("erruer");
        
        return null;
    }
}
    
    
        public PieChartModel initPieModel() {
     PieChartModel pieChartModel = new PieChartModel();
pieChartModel.setDataFormat("value");

  for (Object[] m : employeeFacade.nbEmployes()) {
      System.err.println(m);
           pieChartModel.set(m[1].toString(), Integer.parseInt(m[0].toString()));
        }
      pieChartModel.setShowDataLabels(true);
      
pieChartModel.setSeriesColors(Color.BLACK.toString());
// Check the contents of the ChartSeries
    return pieChartModel;
}
        
   
/*public BarChartModel initBarChar() {
        BarChartModel barChar = new BarChartModel();

        ChartSeries employeesSeries = new ChartSeries();
        employeesSeries.setLabel("Nombre d'employes par service");

        List<Object[]> employeeCountsByService = employeeFacade.nbEmployes();
        for (Object[] entry : employeeCountsByService) {
            employeesSeries.set((String) entry[1], (Number) entry[0]);
            System.err.println("initBarChar "+entry.toString());
        }

        barChar.addSeries(employeesSeries);

        return barChar;
    }*/
    
    
    
    
}
