package com.zjhcsoft.rpc.cfg;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.zjhcsoft.componet.xml.Nodelet;
import com.zjhcsoft.componet.xml.NodeletException;
import com.zjhcsoft.componet.xml.NodeletParser;
import com.zjhcsoft.componet.xml.NodeletUtils;
import com.zjhcsoft.rpc.cfg.domain.AllAppDefDomain;
import com.zjhcsoft.rpc.cfg.domain.AppDefDomain;
import com.zjhcsoft.rpc.cfg.domain.DeployDomain;

public class DeployXmlConfigure {
	private static Log LOG = LogFactory.getLog(DeployXmlConfigure.class);
	private static AllAppDefDomain allApp;
	
	private static ThreadLocal<AppDefDomain> appDefThreadLocal = new ThreadLocal<AppDefDomain>();
	private static ThreadLocal<DeployDomain> deployThreadLocal = new ThreadLocal<DeployDomain>();

	static {
		LOG.debug("apps_deploy.xml init start!");
		allApp = AllAppDefDomain.getInstance();
		NodeletParser parser = new NodeletParser();
		addAllAppListParser(parser);
		try {
			InputStream appList = NodeletParser.class.getClassLoader()
					.getResourceAsStream("apps_deploy.xml");
			if (appList != null) {
				parser.parse(appList);
				try {
					appList.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (NodeletException e) {
			e.printStackTrace();
		}
		LOG.debug("apps_deploy.xml init end!");
	}

	private static void addAllAppListParser(NodeletParser parser) {
		parser.addNodelet("/apps/app", new Nodelet() {
			public void process(Node node) throws Exception {
				NamedNodeMap map = node.getAttributes();
				String appName = NodeletUtils.getNodeValue(map, "name");
				String timeout = NodeletUtils.getNodeValue(map, "timeout");
				String description = NodeletUtils.getNodeValue(map, "desc");
				AppDefDomain appDef = new AppDefDomain();
				appDef.setAppName(appName);
				appDef.setSoTimeOut(Integer.parseInt(timeout));
				appDef.setDescription(description);
				allApp.addAppDefDomain(appDef);

				appDefThreadLocal.set(appDef);
			}
		});

		parser.addNodelet("/apps/app/server", new Nodelet() {
			public void process(Node node) throws Exception {
				NamedNodeMap map = node.getAttributes();
				String name = NodeletUtils.getNodeValue(map, "name");
				String host = NodeletUtils.getNodeValue(map, "host");
				if (host == null || "".equalsIgnoreCase(host.trim())) {
					return;
				}
				String port = NodeletUtils.getNodeValue(map, "port");
				if (port == null || "".equalsIgnoreCase(port.trim())) {
					return;
				}
				DeployDomain deployServer = new DeployDomain();
				deployServer.setName(name);
				deployServer.setHost(host);
				deployServer.setPort(Integer.parseInt(port));

				AppDefDomain appDefTemp = appDefThreadLocal.get();
				appDefTemp.addDeployServer(deployServer);
//				deployServer.setAppDefDomain(appDefTemp);//refers each other

				deployThreadLocal.set(deployServer);
			}
		});

		parser.addNodelet("/apps/app/server/param", new Nodelet() {
			public void process(Node node) throws Exception {
				NamedNodeMap map = node.getAttributes();
				String name = NodeletUtils.getNodeValue(map, "name");
				String value = NodeletUtils.getNodeValue(map, "value");

				DeployDomain deployServerTemp = (DeployDomain) deployThreadLocal.get();
				
				deployServerTemp.addDeployParamMap(name, value);
			}
		});
		parser.addNodelet("/apps/app/server/param/end()", new Nodelet() {
			public void process(Node node) throws Exception {
				// pass
			}
		});
	}

	public static AllAppDefDomain getAllApp() {
		return allApp;
	}

	 public static void main(String[] args) {
		 DeployXmlConfigure cif = new DeployXmlConfigure();
		for (AppDefDomain appDef : (List<AppDefDomain>) allApp.getInstance().getAppDefDomainList()) {
			 System.out.println("appDef_desc====:" + appDef.getDescription());
			for (DeployDomain deploy : appDef.getDeployList()) {
				System.out.println("deploy_name==:"+ deploy.getName()+" deploy_host==:"+ deploy.getHost()+" deploy_port==:"+ deploy.getPort());
				System.out.println("deploy transport_protocol==:" + deploy.getDeployParamMap().get("transport_protocol"));
			}
		}
	}
}
