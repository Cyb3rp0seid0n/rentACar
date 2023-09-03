package com.vmohan.rentACarProject.core.adapters.concretes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vmohan.rentACarProject.core.adapters.abstracts.BankAdapterService;
import com.vmohan.rentACarProject.core.exceptions.BusinessException;
import com.vmohan.rentACarProject.core.externalServices.banks.hdfcBank;
import com.vmohan.rentACarProject.core.utilities.results.Result;
import com.vmohan.rentACarProject.core.utilities.results.SuccessResult;

@Service
@Qualifier("hdfcBank")
public class hdfcBankAdapterManager implements BankAdapterService{

	@Override
	public Result checkIfLimitIsEnough(String cardNo, String year, String mounth, String cVV, double amount) {
		
		hdfcBank hdfcBank = new hdfcBank();
		if(hdfcBank.isLimitExists(cardNo, year, mounth, cVV, amount))	{
			return new SuccessResult("Transaction Completed.");
		}
		else {
			throw new BusinessException("Limit insufficient");
		}
	}

}
