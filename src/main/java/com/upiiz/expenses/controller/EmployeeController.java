package com.upiiz.expenses.controller;

import com.upiiz.expenses.entities.Employees;
import com.upiiz.expenses.responses.CustomResponse;
import com.upiiz.expenses.services.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/**", "https://wcbdf-adl-examen-2.onrender.vercel.app"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/v1/employees")
@Tag(
        name = "Employees"
)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    //@PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CustomResponse<List<Employees>>> getEmployee() {
        List<Employees> expens = new ArrayList<>();
        Link allEmployeesLink = linkTo(EmployeeController.class).withSelfRel();
        List<Link> links = List.of(allEmployeesLink);
        try {
            expens = employeeService.getAllEmployees();
            if (!expens.isEmpty()) {
                CustomResponse<List<Employees>> response = new CustomResponse<>(1, "empleado encontrados", expens, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "employees no encontrados", expens, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Employees>> response = new CustomResponse<>(500, "Error interno de servidor", expens, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CustomResponse<Employees>> getEmployeeById(@PathVariable Long id) {
        Optional<Employees> employee = null;
        CustomResponse<Employees> response = null;
        Link allEmployeesLink = linkTo(EmployeeController.class).withSelfRel();
        List<Link> links = List.of(allEmployeesLink);
        try {
            employee = employeeService.getEmployeeById(id);
            if (employee.isPresent()) {
                response = new CustomResponse<>(1, "Empleado encontrado", employee.get(), links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new CustomResponse<>(0, "Empleado no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response = new CustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<CustomResponse<Employees>> crearEmployee(@RequestBody Employees employees) {
        Link allEmployeesLink = linkTo(EmployeeController.class).withSelfRel();
        List<Link> links = List.of(allEmployeesLink);
        try {
            Employees employees1 = employeeService.createEmployee(employees);
            if (employees1 != null) {
                CustomResponse<Employees> response = new CustomResponse<>(1, "employee creado", employees1, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "Gasto no encontrado", employees1, links));
            }
        } catch (Exception e) {
            CustomResponse<Employees> response = new CustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<CustomResponse<Employees>> updateEmloyee(@RequestBody Employees employees, @PathVariable Long id) {
        Link allExpensesLink = linkTo(EmployeeController.class).withSelfRel();
        List<Link> links = List.of(allExpensesLink);
        try {
            employees.setId(id);
            if (!employeeService.getEmployeeById(id).equals("")) {
                Employees employeesEntity = employeeService.updateEmployee(employees);
                CustomResponse<Employees> response = new CustomResponse<>(1, "employee actualizado", employeesEntity, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                CustomResponse<Employees> response = new CustomResponse<>(0, "employee no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            CustomResponse<Employees> response = new CustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<CustomResponse<Employees>> deleteEmployeeById(@PathVariable Long id) {
        Optional<Employees> employeeEntity = null;
        CustomResponse<Employees> response = null;
        Link allExpensesLink = linkTo(EmployeeController.class).withSelfRel();
        List<Link> links = List.of(allExpensesLink);

        try {
            employeeEntity = employeeService.getEmployeeById(id);
            if (employeeEntity.isPresent()) {
                employeeService.deleteEmployee(id);
                response = new CustomResponse<>(1, "employee eliminado", null, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new CustomResponse<>(0, "employee no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response = new CustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}