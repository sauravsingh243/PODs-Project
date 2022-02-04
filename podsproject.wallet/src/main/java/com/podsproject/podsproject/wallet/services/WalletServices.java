package com.podsproject.podsproject.wallet.services;

import java.io.IOException;


import org.springframework.http.ResponseEntity;

import com.podsproject.podsproject.wallet.entities.Wallet;

public interface WalletServices {
	
	public ResponseEntity<Wallet> getBalance(Integer custID);
//	
	public ResponseEntity<String> addBalance(Wallet w);
//	
	public ResponseEntity<String> deductBalance(Wallet w);
	
	public ResponseEntity<String> reInitialize()throws IOException;

}
