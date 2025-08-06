package com.manikesh.splitwise.splitwise.controller;


import com.manikesh.splitwise.splitwise.dto.ExpenseRequest;
import com.manikesh.splitwise.splitwise.dto.ExpenseResponse;
import com.manikesh.splitwise.splitwise.dto.GroupRequest;
import com.manikesh.splitwise.splitwise.dto.GroupResponse;
import com.manikesh.splitwise.splitwise.model.Expense;
import com.manikesh.splitwise.splitwise.model.User;
import com.manikesh.splitwise.splitwise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userservice;


    @GetMapping("/expense/group")
    public List<ExpenseResponse> getExpensesByGroup(@RequestParam Long groupId) {
        return userservice.getExpensesByGroup(groupId);
    }


    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        return userservice.addUser(user);
    }


    /**
     * Endpoint to add a group with a list of users.
     *
     * @param request GroupRequest containing group details and user IDs.
     * @return GroupResponse containing the created group details.
     */
    @PostMapping("/group/add")
    public GroupResponse addGroup(@RequestBody GroupRequest request) {

        return userservice.addGroup(request);

    }


    @PostMapping("/expense/add")
    public String addExpense(@RequestBody ExpenseRequest request) {
        return userservice.addExpense(request);
    }



}
