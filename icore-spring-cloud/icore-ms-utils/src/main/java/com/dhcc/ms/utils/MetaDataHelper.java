package com.dhcc.ms.utils;

import org.springframework.core.NamedThreadLocal;

import com.dhcc.ms.utils.dto.MetaDataDto;

public class MetaDataHelper {
	private static final  NamedThreadLocal<MetaDataDto> metaDataThreadLocal = new NamedThreadLocal<MetaDataDto>("MetaData");

	public static MetaDataDto getMetaData() {
		return metaDataThreadLocal.get();
	}
	public static void setMetaData(MetaDataDto meta) {
		metaDataThreadLocal.set(meta);
	}
	public static void removeMetaData() {
		metaDataThreadLocal.remove();
	}
}
