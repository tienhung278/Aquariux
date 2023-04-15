package com.aquariux.trading;

import com.aquariux.trading.dto.WriteWallet;
import com.aquariux.trading.service.WalletService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(WalletService walletService) {
		return runner -> {
			createWallet(walletService);
		};
	}

	private void createWallet(WalletService walletService) {
		WriteWallet wallet = new WriteWallet("hungpham", 50000D);
		walletService.createWallet(wallet);
	}
}
