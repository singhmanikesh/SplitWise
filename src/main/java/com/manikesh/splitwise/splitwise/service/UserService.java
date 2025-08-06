package com.manikesh.splitwise.splitwise.service;


import com.manikesh.splitwise.splitwise.dto.*;
import com.manikesh.splitwise.splitwise.model.*;
import com.manikesh.splitwise.splitwise.repository.ExpenseRepository;
import com.manikesh.splitwise.splitwise.repository.GroupRepository;
import com.manikesh.splitwise.splitwise.repository.SplitRepository;
import com.manikesh.splitwise.splitwise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final SplitRepository splitRepository;


    public GroupResponse addGroup(GroupRequest request) {

        List<User> users = userRepository.findAllById(request.getUserIds());
        Group group = Group.builder()
                .name(request.getGroupName())
                .members(users)
                .createdBy(request.getCreatedBy())
                .build();
        Group savedResponse = groupRepository.save(group);
        return GroupResponse.builder()
                .groupId(savedResponse.getId())
                .groupName(savedResponse.getName())
                .createdBy(savedResponse.getCreatedBy())
                .members(savedResponse.getMembers())
                .build();
    }

    public String addUser(User user) {

        User savedUser = userRepository.save(user);
        if(savedUser != null){
            return String.format(
                            "User with name %s and id %d added successfully",
                    savedUser.getName(),
                    savedUser.getId()
            );
        }else{
            return "User could not be added";
        }


    }

    public String addExpense(ExpenseRequest request) {
        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(()-> new IllegalArgumentException("Group with id " + request.getGroupId() + " not found"));
        Expense expense = Expense.builder()
                .group(group)
                .description(request.getDescription())
                .amount(request.getAmount())
                .paidBy(request.getPaidBy())
                .type(request.getType())
                .build();

        expense = expenseRepository.save(expense);
    List<Split> splits = new ArrayList<>();
    for (SplitRequest splitReq: request.getSplits()){
        User user = userRepository.findById(splitReq.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("User with id " + splitReq.getUserId() + " not found"));
        Split split = Split.builder()
                .expense(expense)
                .user(user)
                .amount(splitReq.getAmount())
                .build();
        splits.add(split);
    }
        splits = splitRepository.saveAll(splits);

    expense.setSplits(splits);
    expenseRepository.save(expense);

    return "Expense added Successfully";


    }



    public List<ExpenseResponse> getExpensesByGroup(Long groupId) {
        List<Expense> expenses = expenseRepository.findByGroupId(groupId);
        List<ExpenseResponse> responses = new ArrayList<>();

        for(Expense expense: expenses){
            List<ExpenseResponse.SplitInfo> splitInfos = new ArrayList<>();
            if(expense.getSplits() != null){
                for (Split split : expense.getSplits()) {
                    splitInfos.add(ExpenseResponse.SplitInfo.builder()
                            .userId(split.getUser().getId())
                            .userName(split.getUser().getName())
                            .amount(split.getAmount())
                            .build());
                }
            }
            responses.add(ExpenseResponse.builder()
                    .id(expense.getId())
                    .description(expense.getDescription())
                    .amount(expense.getAmount())
                    .paidBy(expense.getPaidBy())
                    .groupName(expense.getGroup().getName())
                    .splits(splitInfos)
                    .build());
        }
        return responses;

    }



}



