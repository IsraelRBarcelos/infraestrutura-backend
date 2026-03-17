package com.agenciacapsula.infraestrutura;

import org.springframework.boot.SpringApplication;

public class TestInfraestruturaApplication {

	public static void main(String[] args) {
		SpringApplication.from(InfraestruturaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
