package com.sistefact.electronico.service;

import java.util.List;


public interface ICRUD<T> {

	T save(T t);
	T update(T t);
	void delete(Long id);
	T getById(Long id);
	List<T> getAll();

}
