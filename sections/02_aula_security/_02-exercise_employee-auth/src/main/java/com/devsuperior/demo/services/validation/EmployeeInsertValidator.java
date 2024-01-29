package com.devsuperior.demo.services.validation;

import com.devsuperior.demo.dto.EmployeeInsertDTO;
import com.devsuperior.demo.dto.FieldMessage;
import com.devsuperior.demo.entities.Employee;
import com.devsuperior.demo.repositories.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EmployeeInsertValidator implements ConstraintValidator<EmployeeInsertValid, EmployeeInsertDTO> {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public void initialize(EmployeeInsertValid ann) {
    }

    @Override
    public boolean isValid(EmployeeInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        Employee employee = repository.findByEmail(dto.getEmail());

        if (employee != null) {
            list.add(new FieldMessage("email", "That username is taken. Try another"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}