package com.vmohan.rentACarProject.core.utilities.business;

import com.vmohan.rentACarProject.core.utilities.results.Result;

public class BusinessRules {

    public static Result run(Result... logics) {
       
    	for (Result logic : logics) {
            if (!logic.isSuccess()) {
                return logic;
            }
        }
        return null;
    }
}