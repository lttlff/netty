package com.zjhcsoft.rpc.interceptor.route;

import java.util.ArrayList;
import java.util.List;

import com.zjhcsoft.rpc.cfg.RpcXmlConfigure_server;
import com.zjhcsoft.rpc.cfg.domain.InterceptorDomain;
import com.zjhcsoft.rpc.cfg.domain.InterceptorStackDomain;
import com.zjhcsoft.rpc.interceptor.Interceptor;
import com.zjhcsoft.rpc.interceptor.filtercfg.ServiceFilter;
import com.zjhcsoft.rpc.interceptor.interceptorcfg.ServiceInterceptor;
import org.sevenstar.util.BeanHelper;

public class InterceptorChainBuild {
	private static final String FILTER = "filter";
	private static final String INTERCEPTOR = "interceptor";

	private static final String BEFORE = "before";
	private static final String AFTER = "after";
	private static final String AROUND = "around";

	public static void initFilterInterceptor() {
		List<Interceptor> filterbefore = new ArrayList<Interceptor>();
		List<Interceptor> filterafter = new ArrayList<Interceptor>();
		List<Interceptor> filteraroundTemp = new ArrayList<Interceptor>();

		List<InterceptorStackDomain> interceptorStackDomainList = RpcXmlConfigure_server
				.getServerCfgDomain().getInterceptorStackList();
		for (InterceptorStackDomain interceptorStackDomain : interceptorStackDomainList) {
			if (InterceptorChainBuild.FILTER.trim().equalsIgnoreCase(
					interceptorStackDomain.getType().trim())) {

				for (InterceptorDomain interceptorDomain : interceptorStackDomain
						.getInterceptorList()) {
					if (InterceptorChainBuild.BEFORE
							.equalsIgnoreCase(interceptorStackDomain.getMode())) {
						filterbefore.add((Interceptor) BeanHelper
								.newInstance(interceptorDomain.getClassName()));
					}

					else if (InterceptorChainBuild.AROUND
							.equalsIgnoreCase(interceptorStackDomain.getMode())) {
						filterbefore.add((Interceptor) BeanHelper
								.newInstance(interceptorDomain.getClassName()));
						filteraroundTemp.add((Interceptor) BeanHelper
								.newInstance(interceptorDomain.getClassName()));

					}
					else if (InterceptorChainBuild.AFTER
							.equalsIgnoreCase(interceptorStackDomain.getMode())) {
						filterafter.add((Interceptor) BeanHelper
								.newInstance(interceptorDomain.getClassName()));
					}
				}

			}

			if (InterceptorChainBuild.INTERCEPTOR.trim().equalsIgnoreCase(
					interceptorStackDomain.getType().trim())) {
				List<Interceptor> interceptorbefore = new ArrayList<Interceptor>();
				List<Interceptor> interceptoraroundTemp = new ArrayList<Interceptor>();
				List<Interceptor> interceptorafterTemp = new ArrayList<Interceptor>();

				for (InterceptorDomain interceptorDomain : interceptorStackDomain
						.getInterceptorList()) {
					if (InterceptorChainBuild.BEFORE
							.equalsIgnoreCase(interceptorStackDomain.getMode())) {
						interceptorbefore.add((Interceptor) BeanHelper
								.newInstance(interceptorDomain.getClassName()));
					}
					else if (InterceptorChainBuild.AFTER
							.equalsIgnoreCase(interceptorStackDomain.getMode())) {
						interceptorafterTemp.add((Interceptor) BeanHelper
								.newInstance(interceptorDomain.getClassName()));
					}
					else if (InterceptorChainBuild.AROUND
							.equalsIgnoreCase(interceptorStackDomain.getMode())) {
						interceptorbefore.add((Interceptor) BeanHelper
								.newInstance(interceptorDomain.getClassName()));
						interceptoraroundTemp.add((Interceptor) BeanHelper
								.newInstance(interceptorDomain.getClassName()));

					}
				}

				if (InterceptorChainBuild.BEFORE
						.equalsIgnoreCase(interceptorStackDomain.getMode())) {
					ServiceInterceptor.addInterceptorBeforeList(
							interceptorStackDomain.getPattern(),
							interceptorbefore);
				}

				else if (InterceptorChainBuild.AROUND
						.equalsIgnoreCase(interceptorStackDomain.getMode())) {
					ServiceInterceptor.addInterceptorBeforeList(
							interceptorStackDomain.getPattern(),
							interceptorbefore);
					ServiceInterceptor.addInterceptorAroundAfterTempList(
							interceptorStackDomain.getPattern(),
							interceptoraroundTemp);
				}

				else if (InterceptorChainBuild.AFTER
						.equalsIgnoreCase(interceptorStackDomain.getMode())) {
					ServiceInterceptor.addInterceptorAfterTempList(
							interceptorStackDomain.getPattern(),
							interceptorafterTemp);
				}
			}
		}

		// 环绕拦截器中后执行的那部分拦截器，在后置拦截器之前执行
		for (int i = ServiceInterceptor
				.getInterceptorAroundAfterTempListOrder().size() - 1; i >= 0; i--) {
			// InterceptorAroundAfterTempList其实是个map
			List<Interceptor> list = ServiceInterceptor
					.getInterceptorAroundAfterTempList().get(
							ServiceInterceptor
									.getInterceptorAroundAfterTempListOrder()
									.get(i));
			List<Interceptor> list2 = new ArrayList<Interceptor>();
			for (int t = list.size() - 1; t >= 0; t--) {
				list2.add(list.get(t));

			}
			ServiceInterceptor.addInterceptorAfterList(ServiceInterceptor
					.getInterceptorAroundAfterTempListOrder().get(i), list2);
		}

		// 真实的interceptorafter执行
		for (int i = ServiceInterceptor.getInterceptorAfterTempListOrder()
				.size() - 1; i >= 0; i--) {
			// InterceptorAfterTempList其实是个map
			List<Interceptor> list = ServiceInterceptor
					.getInterceptorAfterTempList().get(
							ServiceInterceptor
									.getInterceptorAfterTempListOrder().get(i));
			ServiceInterceptor.addInterceptorAfterList(ServiceInterceptor
					.getInterceptorAfterTempListOrder().get(i), list);
		}

		//前置过滤器
		ServiceFilter.addFilterInterceptorBeforeAllList(filterbefore);

		//环绕过滤器中后执行的那部分过滤器，在后置过滤器之前执行
		for (int i = filteraroundTemp.size() - 1; i >= 0; i--) {
			ServiceFilter
					.addFilterInterceptorAfterList(filteraroundTemp.get(i));
		}

		//后置过滤器
		ServiceFilter.addFilterInterceptorAfterAllList(filterafter);
	}

}
