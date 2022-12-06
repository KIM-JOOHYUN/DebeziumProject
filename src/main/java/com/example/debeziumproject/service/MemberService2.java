package com.example.debeziumproject.service;

import com.example.debeziumproject.entity.Member;
import com.example.debeziumproject.repository2.MemberRepository2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import io.debezium.data.Envelope.Operation;

import java.util.Map;

@Service
public class MemberService2 {
    private final MemberRepository2 memberRepo;

    public MemberService2(MemberRepository2 memberRepo) {
        this.memberRepo = memberRepo;
    }

    public void replicateData(Map<String, Object> memberData, Operation operation){
        final ObjectMapper mapper = new ObjectMapper();
        final Member member = mapper.convertValue(memberData, Member.class);

        if(Operation.DELETE == operation){
            memberRepo.deleteById(member.getId());
        }else{
            memberRepo.save(member);
        }
    }
}
