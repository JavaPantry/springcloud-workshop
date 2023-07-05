package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.domain.Store;
import com.springcloud.sbsuite.store.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreRepository storeRepository;

	@Override
	public List<Store> fetchStores(){
		return storeRepository.findAll();
	}
}
