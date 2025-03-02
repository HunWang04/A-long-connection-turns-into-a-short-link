package org.example.change.entity;

import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LS {
    private String Long; // 网址全名
    private String Short;// localhost:8080/+uu
    //private String code;
    private String domain; // 域名
    private String uu; // 压缩码
}
