package com.manikesh.splitwise.splitwise.dto;

import lombok.Data;

@Data
public class SplitRequest {
    private Long userId;
    private Double amount;

}
