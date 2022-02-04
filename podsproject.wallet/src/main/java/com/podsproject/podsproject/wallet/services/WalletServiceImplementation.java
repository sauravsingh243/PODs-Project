package com.podsproject.podsproject.wallet.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.podsproject.podsproject.wallet.entities.Wallet;


@Service
public class WalletServiceImplementation implements WalletServices {

	Map<Integer, Integer> balance;
	
		
	@Override
	public ResponseEntity<Wallet> getBalance(Integer custID) {
		
		Wallet result = new Wallet(0, 0);
		for (Map.Entry<Integer,Integer> entry : balance.entrySet())
		{
			int var = entry.getKey();
			if(var == custID)
			{
				result.setCustId(entry.getKey());
				result.setAmount(entry.getValue());
				break;
			}
		}
		return new ResponseEntity<Wallet>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> addBalance(Wallet w) {
		for (Map.Entry<Integer,Integer> entry : balance.entrySet())
		{
			if(entry.getKey() == w.getCustId())
			{
				balance.put(entry.getKey(), entry.getValue() + w.getAmount());
				break;
			}
		}
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> deductBalance(Wallet w) 
	{
		for (Map.Entry<Integer,Integer> entry : balance.entrySet())
		{
			if(entry.getKey() == w.getCustId())
			{
				if(entry.getValue() >= w.getAmount())
				{
					balance.put(entry.getKey(), entry.getValue() - w.getAmount());
					return new ResponseEntity<String>(HttpStatus.CREATED);
				}
				else
				{
					break;
				}
			}
		}
		return new ResponseEntity<String>(HttpStatus.GONE);
	}
	
	
	
	
	
//	@Override
	public ResponseEntity<String> reInitialize() throws IOException
	{		
	    FileInputStream f = null;
	    try 
	    {
	    	String path = new File("").getAbsolutePath();
	    	File file = new File(path + "/initialData.txt");
	    	f = new FileInputStream(file);
	    } 
	    catch (FileNotFoundException e) 
	    {
//	    	e.printStackTrace();
	    }
	    BufferedReader br = new BufferedReader(new InputStreamReader(f));
	    String data;
	    balance = new HashMap<>();
	    while ((data = br.readLine()) != null)   
	    {
	    	if (data.charAt(0) == '*')
	    	{
	    		break;
	        }
	    }
	    while ((data = br.readLine()) != null)   
	    {
	    	if (data.charAt(0) == '*')
	    	{
	    		break;
	    	}
	    }
	    while ((data = br.readLine()) != null)   
	    {
	    	if (data.charAt(0) == '*')
	    	{
	    		break;
	    	}
	    	balance.put(Integer.parseInt(data), 0);
	    }
	    data = br.readLine();
	    for (Map.Entry<Integer,Integer> entry : balance.entrySet())
	    	balance.put(entry.getKey(), Integer.parseInt(data));
	    f.close();

	    return new ResponseEntity<String>(HttpStatus.CREATED);
	}
}
