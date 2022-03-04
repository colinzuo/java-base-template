package com.example.basetemplate;

import com.example.basetemplate.mapper.UserMapper;
import com.example.basetemplate.model.po.UserPO;
import com.example.basetemplate.util.StrUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Slf4j
public class BasetemplateApplication implements CommandLineRunner {
	@Autowired
	UserMapper userMapper;

	public static void main(String[] args) {
		SpringApplication.run(BasetemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.debug("Application Run Enter");
	}
}
