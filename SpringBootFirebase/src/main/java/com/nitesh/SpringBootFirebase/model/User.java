package com.nitesh.SpringBootFirebase.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String document_id;
    private String name;
}
