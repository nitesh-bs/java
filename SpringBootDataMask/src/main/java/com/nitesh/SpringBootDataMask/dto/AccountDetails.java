package com.nitesh.SpringBootDataMask.dto;

import com.nitesh.SpringBootDataMask.encrypt.MaskData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDetails {
    private String accountHolderName;
    @MaskData
    private String accountNumber;
    private String accountType;
}
