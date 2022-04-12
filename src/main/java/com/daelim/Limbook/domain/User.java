package com.daelim.Limbook.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {

    private String id;
    private String pw;
    private String name;
    private String phone;
    private String department;

}
