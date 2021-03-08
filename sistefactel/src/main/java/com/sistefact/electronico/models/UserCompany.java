package com.sistefact.electronico.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by veljko on 17.9.18.
 */
@Entity
@Table(name="user_company")
public class UserCompany extends Company{

    private String bankAccount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
