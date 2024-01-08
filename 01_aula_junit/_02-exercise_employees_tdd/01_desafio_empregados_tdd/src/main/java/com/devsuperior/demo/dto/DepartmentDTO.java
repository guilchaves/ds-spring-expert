package com.devsuperior.demo.dto;

import com.devsuperior.demo.entities.Department;

import java.io.Serial;

public class DepartmentDTO implements Comparable<DepartmentDTO> {
    @Serial
    private static final long serialVersionUID = 1L;


    private Long id;
    private String name;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentDTO(Department entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(DepartmentDTO o) {
        return this.name.compareTo(o.name);
    }
}
