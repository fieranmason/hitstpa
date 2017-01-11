package com.leverage.util;

import java.util.List;

import com.leverage.util.NotFoundException;
import com.leverage.util.ReferentialIntegrityException;

public interface IResource<T>{
	
	public T get(Integer id) throws NotFoundException, ReferentialIntegrityException, InternalServerException;
	public List<T> list() throws InternalServerException;
}
