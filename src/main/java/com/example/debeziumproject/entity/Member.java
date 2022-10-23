package com.example.debeziumproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
public class Member {
    @Id
    public Integer id;

    public Integer username;
    public Integer team;
    public Integer position;
}
