package com.ebay.service;

import com.ebay.model.*;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ItemService {

	List<Item> findAll();
}
