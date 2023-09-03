package com.vmohan.rentACarProject.core.adapters.abstracts;

import com.vmohan.rentACarProject.core.utilities.results.Result;

public interface BankAdapterService {

	Result checkIfLimitIsEnough(String cardNo, String year, String month, String cVV, double amount);

}
