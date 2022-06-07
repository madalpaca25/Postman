package com.example.proj.rest;

import java.util.Map;
import com.example.proj.model.Account;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import java.lang.Object;



public class AccountsController implements ModelDriven<Object> {
    private static final long serialVersionUID = 1L; 
    private Object model;
    private String id;
    Account account=new Account();
    private AccountsRepository accountRepository = new AccountsRepository();
    private static Map<String, Account> map;
    {
        try {
            map = accountRepository.findAllAccounts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HttpHeaders index() {
    model = map;
    return new DefaultHttpHeaders("index").disableCaching();
    }
    
    @Override
	public Object getModel() {
		return (model != null ? model : account);
	}

    public String getId() {
		return id;
	}
	public void setId(String id) {
		model = accountRepository.getAccountById(id);
		this.id = id;
	}
	public HttpHeaders show() {
		model = accountRepository.getAccountById(getId());
		return new DefaultHttpHeaders("show");
	}

    public HttpHeaders create() throws Exception {
        model = accountRepository.save(account); 
        return new DefaultHttpHeaders("create");
    }

}
    
