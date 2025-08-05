package com.manikesh.splitwise.splitwise.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupRequest {

    private String groupName;
    private List<Long> userIds;
    private String createdBy; // Email of the user creating the group
}
