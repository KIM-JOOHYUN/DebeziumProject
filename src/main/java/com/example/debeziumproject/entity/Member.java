package com.example.debeziumproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "Member", indexes = {
        @Index(name = "idx_member_id", columnList = "id")
})
@Getter
@Setter
public class Member {
    @Id
    public Integer id;

    public String username;
    public String team;
    public Integer backnumber;
    public String position;
}
