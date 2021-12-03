package com.emog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emog.mapper.CityMapper;

@SpringBootApplication
public class SampleMybatisApplication implements CommandLineRunner {
	
	
	private CityMapper cityMapper;
	
	public SampleMybatisApplication(CityMapper cityMapper) {
	    this.cityMapper = cityMapper;
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.cityMapper.findByState("NICE"));
	}
	
	public static void main(String[] args) {
	    SpringApplication.run(SampleMybatisApplication.class, args);
	}
}
