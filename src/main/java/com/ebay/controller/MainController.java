package com.ebay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ebay.model.Auction;
import com.ebay.model.AuctionManager;
import com.ebay.model.OrderManager;
import com.ebay.model.Sale;
import com.ebay.serviceImpl.AuctionManagerServiceImpl;
import com.ebay.serviceImpl.AuctionServiceImpl;
import com.ebay.serviceImpl.OrderManagerServiceImpl;
import com.ebay.serviceImpl.SaleServiceImpl;
import com.ebay.serviceImpl.UserServiceImpl;

import java.util.*;

//@RestController
@Controller
public class MainController {

	@Autowired
	SaleServiceImpl saleService;
	@Autowired
	AuctionServiceImpl auctionService;
	@Autowired
	UserServiceImpl userService;
	@Autowired
	AuctionManagerServiceImpl managerService;
	@Autowired
	OrderManagerServiceImpl orderService;

	public Double getMaxBidPrice(List<AuctionManager> bids) {
		double max = 0.0;

		for(var bid: bids) {
			if (bid.getBid_price() > max) {
				max = bid.getBid_price();
			}
		}
		return max;
	}

	public Double getMaxAuctionPrice(Auction a) {
		//System.out.println("---------------------------------------------");
		//System.out.println("Looking for max bid price for auction a " + a);

		List<AuctionManager> bids = managerService.findByAuctionId(a.getId());

		return getMaxBidPrice(bids);
	}
	/*************************************************************
	 ********************** home page
	 ************************************************************/
	@RequestMapping(value="/{categ}", method=RequestMethod.GET)
	public ModelAndView index(Authentication auth,
							  @PathVariable(value="categ", required=false)String categ,
							  @RequestParam(value="filter", required=false)String change,
							  @RequestParam(value="mult", required=false)Double mult,
							  @RequestParam(value="currency", required=false) String money
	) {
		System.out.println("principal name " + auth.getName());
		System.out.println("MainController.index()");
		System.out.println("Currency " + money);
		System.out.println("Filter " + change);

		List<Sale> sales = (List<Sale>) saleService.findAll();
		List<Auction> auctions = (List<Auction>) auctionService.findAll();

		for (Auction a: auctions) {
			a.setLast_bid_price(getMaxAuctionPrice(a));
		}

		System.out.println("HOME page sales and auctions " + sales);

		if (money == null || money.equals("€")) {
			money = "€";
			mult = 1.0;
		}

		if (money.equals("$")){
			mult = 1.2;
		}
		if (money.equals("£")){
			mult = 0.8;
		}

		if (categ == null) {
			categ = "all";
		}

		if (categ.equals("all")) {
			System.out.println("ALL CHECKED");
			sales = (List<Sale>) saleService.findAll();
			auctions = (List<Auction>) auctionService.findAll();
		}
		else {
			System.out.println("------- Categ " + categ);
			sales = (List<Sale>) saleService.findAllByCategory(categ);
			auctions = auctionService.findAllByCategory(categ);
		}

		for (Auction a: auctions) {
			a.setLast_bid_price(getMaxAuctionPrice(a));
		}

		// do not show sold sales
		List<OrderManager> sold_sales = orderService.findAll();

		for(Sale s: sales) {
			for(OrderManager sold: sold_sales) {
				//System.out.println(" ******* s " + s);
				//System.out.println(" ******* sold " + sold);
				if(sold.getSale().getId() == s.getId()) {
					System.out.println("Sale is to remove " + s);
					System.out.println("Sale sale.sold " + s.isSold());

				}
			}
		}

		sales.removeIf(s -> (s.isSold() == true));

		// sort sales and auctions
		if (change != null) {
			if (change.equals("low")) {
				sales.sort(Comparator.comparing(Sale::getPrice));
				auctions.sort(Comparator.comparing(Auction::getPrice));
			}
			if(change.equals("high")) {
				sales.sort(Comparator.comparing(Sale::getPrice).reversed());
				auctions.sort(Comparator.comparing(Auction::getPrice).reversed());
			}
		}

		var params = new HashMap<String, Object>();
		params.put("sales", sales);
		params.put("auctions", auctions);

		params.put("currency", money);
		params.put("mult", mult);
		params.put("categ", categ);
		params.put("filter", change);

		return new ModelAndView("home", params);
	}


	/*************************************************************
	 ********************** Render by category form button
	 ************************************************************/
    /*
    @RequestMapping("/changeCurrency")
    public ModelAndView change(@RequestParam(value="currency") String money) {

    	System.out.println("change currency to " + money);



    	return new ModelAndView("redirect:/");
    }
    */
    /*
    @RequestMapping("/buy/{id}")
	public ModelAndView renderCategory(@PathVariable("id") long id) {
    	System.out.println("Main.buy id " + id);

    	saleService.delete(id);

    	return new ModelAndView("redirect:/");
    }
    */
	@RequestMapping("/render/{categ}")
	public ModelAndView renderCategory(@PathVariable("categ") String categ,
									   @RequestParam(value="mult", required=false)Double mult,
									   @RequestParam(value="currency", required=false) String money) {
		System.out.println("render categ " + categ);

		List<Sale> sales = null;
		List<Auction> auctions = null;

		if (categ.equals("all")) {
			System.out.println("ALL CHECKED");
			sales = (List<Sale>) saleService.findAll();
			auctions = (List<Auction>) auctionService.findAll();
		}
		else {
			sales = (List<Sale>) saleService.findAllByCategory(categ);
			auctions = auctionService.findAllByCategory(categ);
		}

		for (Auction a: auctions) {
			a.setLast_bid_price(getMaxAuctionPrice(a));
		}

		System.out.println("Found sales for " + categ + sales);
		System.out.println("Found auctions for " + categ + auctions);

		var params = new HashMap<String, Object>();
		params.put("sales", sales);
		params.put("auctions", auctions);

		if (money == null || money.equals("€")) {
			money = "€";
			mult = 1.0;
		}

		if (money.equals("$")){
			mult = 1.2;
		}
		if (money.equals("£")){
			mult = 0.8;
		}

		params.put("currency", money);
		params.put("mult", mult);
		params.put("filter", "low");

		return new ModelAndView("home", params);
	}

	/*************************************************************
	 ********************** Login stuff
	 ************************************************************/
	/*
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView loginPage(Model model) {
    	System.out.println("MainContr.loginPage()");
    	model.addAttribute("user", new User());
    	return new ModelAndView("login");
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.GET)
    public ModelAndView registrationPageGET(Model model) {
    	System.out.println("MainContr.registrnPage GET()");
    	model.addAttribute("user", new User());
    	
    	return new ModelAndView("registration");
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public ModelAndView registrationPagePOST(User user) {
    	System.out.println("MainContr.registrnPage POST() model " + user);
    	userService.save(user);
    	System.out.println("registrationPOST user saved " + user);
    		
    	return new ModelAndView("redirect:login");
    }
    
    @RequestMapping("/login-error")
    public ModelAndView loginError(Model model) {
    	
	  var params = new HashMap<String, Object>();
	  params.put("user", new User());
	  params.put("loginError", true);
    
      return new ModelAndView("login", params);
    }
    
    @RequestMapping("/logout-success")
    public String logoutPage() {
    	
    	return "redirect:login";
    }
    
    */


}
