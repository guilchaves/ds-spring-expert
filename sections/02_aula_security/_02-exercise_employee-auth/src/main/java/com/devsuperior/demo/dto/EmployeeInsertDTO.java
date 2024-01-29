package com.devsuperior.demo.dto;

import com.devsuperior.demo.services.validation.EmployeeInsertValid;

import java.io.Serial;
import java.io.Serializable;

@EmployeeInsertValid
public class EmployeeInsertDTO extends EmployeeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String password;

    public EmployeeInsertDTO() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
