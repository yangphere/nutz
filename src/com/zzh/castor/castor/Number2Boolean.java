package com.zzh.castor.castor;

import com.zzh.castor.Castor;

public class Number2Boolean extends Castor<Number, Boolean> {

	@Override
	protected Boolean cast(Number src, Class<?> toType, String... args) {
		return src.toString().charAt(0) == '0' ? false : true;
	}

}