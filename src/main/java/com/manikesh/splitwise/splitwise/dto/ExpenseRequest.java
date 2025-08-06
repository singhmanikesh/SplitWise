package com.manikesh.splitwise.splitwise.dto;

import com.manikesh.splitwise.splitwise.model.SplitType;
import com.manikesh.splitwise.splitwise.dto.SplitRequest;
import lombok.Data;

import java.util.List;

@Data
public class ExpenseRequest {
    private Long groupId;
    private String description;
    private Double amount;
    private Long paidBy; // ID of the user who paid for the expense
    private List<SplitRequest> splits; // List of splits for the expense
    private SplitType type; // Type of split (EQUAL, PERCENTAGE, etc.)
}
