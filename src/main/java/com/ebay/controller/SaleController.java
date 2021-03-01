package com.ebay.controller;

import java.util.HashMap;
import java.util.List;

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

import com.ebay.model.Item;
import com.ebay.model.OrderManager;
import com.ebay.model.Sale;
import com.ebay.model.User;
import com.ebay.serviceImpl.OrderManagerServiceImpl;
import com.ebay.serviceImpl.SaleServiceImpl;
import com.ebay.serviceImpl.UserServiceImpl;

@Controller
public class SaleController {

	@Autowired
	SaleServiceImpl service;
	@Autowired
	UserServiceImpl userService;
	@Autowired
	OrderManagerServiceImpl orderService;
	
	/*
	 *  open page to create new one
	 */
	@RequestMapping("/newSale")
	public ModelAndView addSale(Authentication auth, Model model) {
		System.out.println("SaleController addSale GET");
		
		User user = userService.findByUsername(auth.getName());
		System.out.println("SaleController.addSale user " + user);
		
		Sale sale = new Sale();
		//sale.setSeller(user);
		//sale.setItem(new Item());
		
		var params = new HashMap<String, Object>();
		params.put("sale", sale);
		//params.put("user", user);
		params.put("item", new Item());
		
		return new ModelAndView("addSale", params);
	}
	
	/*
	 *  save url
	 */
	@PostMapping("/saveSale")
	public ModelAndView saveSale(Authentication auth,
								 @ModelAttribute("item")Item item,
								 @ModelAttribute("sale")Sale sale) 
	{
		
		System.out.println("----------------------SAVE SALE-------------------");
		System.out.println("SaleController saveSale");
		System.out.println("from UI item " + item);
		System.out.println("from UI sale " + sale);
		
		sale.setItem(item); 
		User user = userService.findByUsername(auth.getName());
		sale.setSeller(user);
		
		if (sale.getPrice() == null || sale.getPrice() < 0) {
			sale.setPrice(0.0);
		}
		if (sale.getTitle().equals("")) {
			sale.setTitle("No Title");
		}
		
		System.out.println("saveSale user before adding sale " + user);
		
		//userService.save(user);
		
		//user = userService.findByUsername(auth.getName());
		System.out.println("saveSale user after adding sale " + user);
		
		System.out.println("After modification sale " + sale);
		System.out.println("----------------------SAVED -------------------");		
		service.save(sale);
		
		
		return new ModelAndView("redirect:mySales");
	}
	/*
	 *  update via url
	 */
	@RequestMapping("/updateSale")
	public ModelAndView updateSale(Authentication auth,
								 @ModelAttribute("item")Item item,
								 @ModelAttribute("sale")Sale sale) 
	{
		
		System.out.println("----------------------UPDATE SALE-------------------");
		System.out.println("SaleController updateSale");
		System.out.println("from UI item " + item);
		System.out.println("from UI sale " + sale);
		
		sale.setItem(item); 
		User user = userService.findByUsername(auth.getName());
		sale.setSeller(user);
		
		if (sale.getPrice() == null || sale.getPrice() < 0) {
			sale.setPrice(0.0);
		}
		if (sale.getTitle().equals("")) {
			sale.setTitle("No Title");
		}
		
		//user.getSales().add(sale);
		//userService.save(user);
		
		System.out.println("After modification sale " + sale);
		System.out.println("----------------------UPDATED -------------------");		
		//service.updateSale(sale);
		service.save(sale);
		
		return new ModelAndView("redirect:mySales");
	}
	/*
	 *  delete sale
	 */
	@RequestMapping("/deleteSale/{id}")
	public ModelAndView deleteSale(@PathVariable("id") int id) {
		System.out.println("Delete sale.id " + id);
		
		service.delete(id);
		
		return new ModelAndView("redirect:/mySales");
	}
	
	/*
	 *  edit sale
	 */
	@RequestMapping("/editSale/{id}")
	public ModelAndView editSale(@PathVariable("id") int id) {
		System.out.println("Delete sale.id " + id);
		
		Sale sale = service.find(id);
		System.out.println("SaleController.editSale sale " + sale);
		var params = new HashMap<String, Object>();
		params.put("sale", sale);
		params.put("item", sale.getItem());
		
		return new ModelAndView("editSale", params);
	}
	/*
	 *  get all sales where user.username = sale.seller.username
	 */
	@RequestMapping("/mySales")
	public ModelAndView getSales(Authentication auth,
			 @RequestParam(value="mult", required=false)Double mult,
			 @RequestParam(value="currency", required=false) String money) {
		
		System.out.println("SaleController getSales  user " + auth.getName());
		String username = auth.getName();
		
		List<Sale> sales = (List<Sale>) service.findAllBySeller(username);
		System.out.println("sales-" + sales);
		
		var params = new HashMap<String, Object>();
		params.put("sales", sales);
		
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
		
		User user = userService.findByUsername(auth.getName());
		System.out.println("user" + user);
		
		return new ModelAndView("mySales", params);
	}
	
	/*
	 * show orders (sales I bought)
	 */
	@RequestMapping("/myOrders")
	public ModelAndView getOrders(Authentication auth,
			 @RequestParam(value="mult", required=false)Double mult,
			 @RequestParam(value="currency", required=false) String money) {
		
		System.out.println("SaleController getOrders  user " + auth.getName());
		String username = auth.getName();
		
		//List<Sale> orders = (List<Sale>) service.findAllBySeller(username);
		List<OrderManager> orders = orderService.findBySaleBuyerUsername(username);

		System.out.println("orders-" + orders);
		
		var params = new HashMap<String, Object>();
		params.put("orders", orders);
		
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
		
		User user = userService.findByUsername(auth.getName());
		System.out.println("user" + user);
		
		return new ModelAndView("myOrders", params);
	}
	/*
	 * By action (sale)
	 */
	@RequestMapping("/buy/{id}")
	public ModelAndView buySale(Authentication auth,
			 @PathVariable(value="id") long id,
			 @RequestParam(value="mult", required=false)Double mult,
			 @RequestParam(value="currency", required=false) String money) {
		
		System.out.println("SaleController buySale  id " + id);
		
		// get user object
		User user = userService.findByUsername(auth.getName());
		
		// save sale as sold to be shown in user's myOrders page
		Sale sold_sale = service.find(id);
		System.out.println("Sold_sale is " + sold_sale);
		sold_sale.setSold(true);
		service.save(sold_sale);
		
		//create orderManager with Sale/User
		OrderManager order = new OrderManager();
		order.setBuyer(user);
		order.setSale(sold_sale);
		orderService.save(order);
		
		// find orders for user
		//List<OrderManager> orders = orderService.findBySaleBuyerUsername(user.getUsername());
		//System.out.println("orders-" + orders);
		/*
		var params = new HashMap<String, Object>();
		params.put("orders", orders);
		
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
		
		*/
		
		return new ModelAndView("redirect:/all");
	}

}
