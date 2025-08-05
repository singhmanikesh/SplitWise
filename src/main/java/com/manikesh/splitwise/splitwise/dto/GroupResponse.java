package com.manikesh.splitwise.splitwise.dto;


import com.manikesh.splitwise.splitwise.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupResponse {
    private Long groupId;
    private String groupName;
    private List<User> members;
    private String createdBy; // Email of the user who created the group
}
