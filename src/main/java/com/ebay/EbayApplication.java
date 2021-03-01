package com.ebay;

import java.util.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ebay.model.Auction;
import com.ebay.model.AuctionManager;

//import com.ebay.model.Auction;

import com.ebay.model.Item;
import com.ebay.model.Sale;
import com.ebay.model.User;
import com.ebay.repository.AuctionManagerRepo;
import com.ebay.repository.AuctionRepo;
import com.ebay.repository.ItemRepo;
import com.ebay.repository.SaleRepo;
import com.ebay.repository.UserRepo;

//@EnableJpaRepositories("com.ebay.repository.*")
//@ComponentScan("com.ebay.model.*")
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class EbayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbayApplication.class, args);
		
	}
	
	
	@Bean
	public CommandLineRunner mappingDemo(AuctionManagerRepo managerRepo, AuctionRepo auctionRepo, SaleRepo saleRepo, UserRepo userRepo, ItemRepo itemRepo) {
		return args -> {
			
			Item item = new Item("form_main");
			itemRepo.save(item);
			
			User manu = new User("manu", "$2y$12$BO2yCZZ39l28lFkW2vXq2uzBYngEb2tNzGXI3K3SPXJfH1QC7Abga");
			userRepo.save(manu);
			User ler = new User("lucas", "$2y$12$CEDW6EFsqNBG8MZyNgdXfeZ4sYj03cI3p8E7RDmkP40qaih/GXdsa");
			userRepo.save(ler);
			User deph = new User("delphin", "$2y$12$PjZVs0DXoPNmIpjiNW6E5uu2iY7zdRTQvHbRFpSjOSYeE/3R2KCCq");
			userRepo.save(deph);
			User elyes = new User("elyes", "$2y$12$70TB21UC5mM4bVPkkJiDBOTxjvTIWoTu1OdsuObiF5u9.u3eMfn02");	
			userRepo.save(elyes);
			
			saleRepo.save(new Sale(new Item("fashion"), manu, "Shoes Nike 42", 20.0));
			saleRepo.save(new Sale(new Item("fashion"), ler, "Shoes Adidas 41", 10.0));
			saleRepo.save(new Sale(new Item("electronics"), manu, "Business laptop", 206.0));
			saleRepo.save(new Sale(new Item("electronics"), ler, "samsung galaxy", 40.0));
			saleRepo.save(new Sale(new Item("homegarden"), ler, "Garden Chair", 30.0));
			saleRepo.save(new Sale(new Item("gaming"), manu, "NVidia GPU", 90.0));
			
			var ac = new Auction(new Item("electronics"), manu, "Old Dell Laptop", 10.0);
			ac.setLast_bidder(ler);
			auctionRepo.save(ac);
			
			Auction a1 = new Auction(new Item("gaming"), manu, "NVidia laptop", 100.0);
			auctionRepo.save(a1);
			Auction a2 = new Auction(new Item("fashion"), ler, "Sweatshirt size L", 20.0);
			auctionRepo.save(a2);
			
			managerRepo.save(new AuctionManager(a1, ler, 10.0));
			managerRepo.save(new AuctionManager(a1, deph, 15.0));
			managerRepo.save(new AuctionManager(a1, ler, 30.0));
			managerRepo.save(new AuctionManager(a1, elyes, 44.0));
			managerRepo.save(new AuctionManager(a2, manu, 15.0));
			managerRepo.save(new AuctionManager(a2, ler, 30.0));
			managerRepo.save(new AuctionManager(a2, manu, 60.0));
			
			
		};
	}
	
	 
}
