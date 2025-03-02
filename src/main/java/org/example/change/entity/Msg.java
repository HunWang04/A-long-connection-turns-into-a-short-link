package org.example.change.entity;

import lombok.*;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Enumeration;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Msg {
    private int effective = 0;//
    private int ineffective = 0;//
    private String code;//
    private Timestamp Date;//
    private String client;//
    private String Short;//
    private String Long;//
    private String UA;//
    private String browser;//
    private String os;//
    private int status;//
}
