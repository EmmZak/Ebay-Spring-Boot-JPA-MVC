package com.ebay.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ebay.model.Auction;
import com.ebay.model.AuctionManager;
import com.ebay.model.Item;
import com.ebay.model.Sale;
import com.ebay.model.User;
import com.ebay.serviceImpl.AuctionManagerServiceImpl;
import com.ebay.serviceImpl.AuctionServiceImpl;
import com.ebay.serviceImpl.UserServiceImpl;

@Controller
public class AuctionController {

	@Autowired
	AuctionServiceImpl service;
	@Autowired
	UserServiceImpl userService;
	@Autowired
	AuctionManagerServiceImpl managerService;
	
	/*
	 *  open page to create new auction
	 */
	@RequestMapping("/newAuction")
	public ModelAndView addAuction(Authentication auth, Model model) {
		System.out.println("AuctionController addAuction GET");
		
		User user = userService.findByUsername(auth.getName());
		System.out.println("AuctionController.addSale user " + user);
		
		Auction auction = new Auction();
		//auction.setSeller(user);
		//auction.setItem(new Item());
		
		var params = new HashMap<String, Object>();
		params.put("auction", auction);
		//params.put("user", user);
		params.put("item", new Item());
		
		return new ModelAndView("addAuction", params);
	}
	/*
	 *  save auction
	 */
	@RequestMapping("/saveAuction")
	public ModelAndView saveSale(Authentication auth,
								 @ModelAttribute("item")Item item,
								 @ModelAttribute("auction")Auction auction) 
	{
		
		System.out.println("----------------------SAVE AUCTION-------------------");
		System.out.println("SaleController saveSale");
		System.out.println("from UI item " + item);
		System.out.println("from UI auction " + auction);
		
		auction.setItem(item); 
		User user = userService.findByUsername(auth.getName());
		auction.setSeller(user); 
		
		if (auction.getPrice() == null  || auction.getPrice() < 0) {
			auction.setPrice(0.0);
		}
		if (auction.getTitle().equals("")) {
			auction.setTitle("No Title");
		}
		
		System.out.println("saveSale user before adding auction " + user);
		
		//userService.save(user);
		
		//user = userService.findByUsername(auth.getName());
		System.out.println("saveSale user after adding sale " + user);
		
		System.out.println("After modification sale " + auction);
		System.out.println("----------------------SAVED -------------------");		
		service.save(auction);
		
		
		return new ModelAndView("redirect:myAuctions");
	}
	/*
	 * update auction
	 */
	@RequestMapping("/updateAuction")
	public ModelAndView updateSale(Authentication auth,
								 @ModelAttribute("item")Item item,
								 @ModelAttribute("auction")Auction auction) 
	{
		
		System.out.println("----------------------UPDATE Auction-------------------");
		System.out.println("SaleController updateAuction");
		System.out.println("from UI item " + item);
		System.out.println("from UI auction " + auction);
		
		auction.setItem(item); 
		User user = userService.findByUsername(auth.getName());
		auction.setSeller(user);
		
		if (auction.getPrice() == null || auction.getPrice() < 0) {
			auction.setPrice(0.0);
		}
		if (auction.getTitle().equals("")) {
			auction.setTitle("No Title");
		}
		
		//user.getSales().add(sale);
		//userService.save(user);
		
		System.out.println("After modification auction " + auction);
		System.out.println("----------------------UPDATED -------------------");		
		//service.updateSale(sale);
		service.save(auction);
		
		return new ModelAndView("redirect:myAuctions");
	}
	/*
	 *  delete auction
	 */
	@RequestMapping("/deleteAuction/{id}")
	public ModelAndView deleteAuction(@PathVariable("id") int id) {
		System.out.println("Delete auction.id " + id);
		
		// we have to delete auctionManager references first
		List<AuctionManager> auction_bids = managerService.findByAuctionId(id);
		System.out.println("Bids for auction id-" + id);
		for(var am: auction_bids) {
			System.out.println("bid " + am);
			managerService.delete(am.getId());
		}
		
		service.delete(id);
		
		return new ModelAndView("redirect:/myAuctions");
	}
	
	/*
	 *  edit auction
	 */
	@RequestMapping("/editAuction/{id}")
	public ModelAndView editAuction(@PathVariable("id") int id) {
		System.out.println("Delete auction.id " + id);
		
		Auction auction = service.findById(id);
		System.out.println("AuctionController.editAuction auction " + auction);
		var params = new HashMap<String, Object>();
		params.put("auction", auction);
		params.put("item", auction.getItem());
		
		return new ModelAndView("editAuction", params);
	}
	/*
	***get all auctions to home page
	 */
	@RequestMapping("/myAuctions")
	public ModelAndView getAuctions(Authentication auth,
			 @RequestParam(value="mult", required=false)Double mult,
			 @RequestParam(value="currency", required=false) String money) {
		System.out.println("AuctionController getAuctions user " + auth.getName());
		
		//List<Auction> auctions = (List<Auction>) service.findAll();
		User user = userService.findByUsername(auth.getName());
		List<Auction> auctions = (List<Auction>) service.findBySeller(user.getId());
		System.out.println("auctions-" + auctions);
		
		var params = new HashMap<String, Object>();
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
		
		return new ModelAndView("myAuctions", params);
	}
	
    /*************************************************************
     ********************** Auction page for bidding ...
     ************************************************************/
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
		System.out.println("---------------------------------------------");
		System.out.println("Looking for max bid price for auction a " + a);
		
		List<AuctionManager> bids = managerService.findByAuctionId(a.getId());
		
		return getMaxBidPrice(bids);
	}
	@RequestMapping("/auction/{id}")
	public ModelAndView openAuction(Authentication auth, 
			@PathVariable("id") int id,
			@RequestParam(value="mult", required=false)Double mult,
			@RequestParam(value="currency", required=false) String money) {
		System.out.println("----------- AuctionCOntroller openAuction");
		System.out.println("AuctionController openAuction id=" + id);
		
		Auction a = service.findById(id);
		System.out.println("Auction to open is " + a);
		
		var params = new HashMap<String, Object>();
		// Get bids for the auction
		List<AuctionManager> bids = managerService.findByAuctionId(id);
		System.out.println("Bids : ");
		for (Object b: bids) {
			System.out.println("bid " + b);
		}
		
		// add last_bid_price to auction
		double last_bid_price = getMaxAuctionPrice(a);
		a.setLast_bid_price(last_bid_price);
		
		service.save(a);
		
		System.out.println("adding auction to the page " + a);
		params.put("auction", a);
		params.put("bids", bids);

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
		
		AuctionManager my_bid = new AuctionManager();
		//my_bid.setBid_price(0.0);
		//System.out.println("opening auction with my_bid " + my_bid);
		
		User user = userService.findByUsername(auth.getName());
		//my_bid.setBidder(user);
		System.out.println("adding my_bid to the page " + my_bid);
		params.put("my_bid", my_bid);

		return new ModelAndView("auction", params);
	}
	
    /*
	@PostMapping("/test")
	public ModelAndView testBid(@ModelAttribute("my_bid") AuctionManager my_bid) {
		
		System.out.println("post test request my_bid " + my_bid);
		return new ModelAndView("/");
	}
	*/
	@RequestMapping("/addBid")
	public ModelAndView addBid(Authentication auth,
									  /*
									  @PathVariable(value="mult") Double page_mult,
									  @PathVariable(value="currency") Double page_money,
									  */
									  @RequestParam(value="mult")Double mult,
									  @RequestParam(value="currency") String money,
									  @ModelAttribute("auction")Auction a, 
									  @ModelAttribute("my_bid")AuctionManager my_bid) {
		//System.out.println("AuctionController POST add bid to auction by id=" + a);
		//System.out.println("AuctionController POST amanager=" + my_bid);
		System.out.println("----- ADD BID ------");
		Auction auction = service.findById(a.getId());
		System.out.println("Auction  is " + auction);
		System.out.println("Bid  is " + my_bid);
		
		User user = userService.findByUsername(auth.getName());
		System.out.println("User  is " + user);
		
		if (my_bid.getBid_price() == null || my_bid.getBid_price() < 0) {
			my_bid.setBid_price(0.0);
		}
		
		// on stocke en euros, on va donc ajuster si besoin
		if (money.equals("€") == false) {
			double bid_price = my_bid.getBid_price();
			my_bid.setBid_price(bid_price/mult);
			/*
			if (money.equals("$")) {
				my_bid.setBid_price(bid_price/mult);
			}
			else {
				
			}
			*/
		}
		
		// try to do this with EntityManager
		my_bid.setAuction(auction);
		my_bid.setBidder(user);
		System.out.println("Final Bid  is " + my_bid);
		managerService.save(my_bid);
		//managerService.updateBid(my_bid.getId(), user, my_bid.getBid_price());
		
		// try to do this via ENtityManager
		//auction.setLast_bid_price(my_bid.getBid_price());
		//service.save(auction);
		service.updateLastBidPrice(auction.getId(), my_bid.getBid_price());
		
		var params = new HashMap<String, Object>();
		
		System.out.println("BID  In adding bid mult " + mult);
		System.out.println("BID  In adding bid money " + money);
		
    	params.put("currency", money);
    	params.put("mult", mult);
    	

		return new ModelAndView("redirect:auction/" + a.getId().toString(), params);
	}
}
