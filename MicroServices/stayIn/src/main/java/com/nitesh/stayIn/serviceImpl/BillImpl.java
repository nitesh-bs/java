package com.nitesh.stayIn.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.stayIn.entity.Bill;
import com.nitesh.stayIn.repository.BillRepository;
import com.nitesh.stayIn.service.BillService;

@Service
public class BillImpl implements BillService {

	@Autowired
	private BillRepository billRepository;

	@Override
	public Bill saveOrUpdateBill(Bill bill) {
		Bill saveOrUpdateBill = billRepository.save(bill);
		return saveOrUpdateBill;
	}

}
