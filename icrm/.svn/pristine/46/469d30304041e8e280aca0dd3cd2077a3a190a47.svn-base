package com.zjhcsoft.rpc.cfg.domain;

import java.util.ArrayList;
import java.util.List;

public class AllAppDefDomain {

	private static AllAppDefDomain allAppDefDomain = null;

	private List<AppDefDomain> appDefDomainList;

	private AllAppDefDomain() {
	}

	public static AllAppDefDomain getInstance() {
		if (allAppDefDomain == null) {
			allAppDefDomain = new AllAppDefDomain();
		}
		return allAppDefDomain;
	}

	public void addAppDefDomain(AppDefDomain appDefDomain) {
		this.getAppDefDomainList().add(appDefDomain);
	}

	public List<AppDefDomain> getAppDefDomainList() {
		if (this.appDefDomainList == null) {
			this.appDefDomainList = new ArrayList<AppDefDomain>();
		}
		return appDefDomainList;
	}

}
