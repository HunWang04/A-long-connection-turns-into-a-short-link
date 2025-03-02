package org.example.change;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
@SpringBootApplication
@MapperScan("org.example.change.DAO")
@ServletComponentScan(basePackages="org.example.change.filter")
//@MapperScan("org.example.change.mapper")
public class  ChangeApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ChangeApplication.class, args);
    }
}
