package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.domain.Store;

import java.util.List;

public interface StoreService {
	public List<Store> fetchStores();
}
