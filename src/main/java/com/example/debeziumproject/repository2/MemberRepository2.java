package com.example.debeziumproject.repository2;

import com.example.debeziumproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository2 extends JpaRepository<Member, Integer> {
}
