package com.manikesh.splitwise.splitwise.service;


import com.manikesh.splitwise.splitwise.dto.GroupRequest;
import com.manikesh.splitwise.splitwise.dto.GroupResponse;
import com.manikesh.splitwise.splitwise.model.Group;
import com.manikesh.splitwise.splitwise.model.User;
import com.manikesh.splitwise.splitwise.repository.GroupRepository;
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
}
