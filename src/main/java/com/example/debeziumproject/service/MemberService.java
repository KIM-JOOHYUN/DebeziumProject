package com.example.debeziumproject.service;

import com.example.debeziumproject.entity.Member;
import com.example.debeziumproject.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import io.debezium.data.Envelope.Operation;

import java.util.Map;

@Service
public class MemberService {
    private final MemberRepository memberRepo;

    public MemberService(MemberRepository memberRepo) {
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
