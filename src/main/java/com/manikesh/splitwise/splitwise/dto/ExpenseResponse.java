package com.manikesh.splitwise.splitwise.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ExpenseResponse {
    private Long id;
    private String description;
    private Double amount;
    private Long paidBy;
    private String groupName;
    private List<SplitInfo> splits;

    @Data
    @Builder
    public static class SplitInfo {
        private Long userId;
        private String userName;
        private Double amount;
    }
}