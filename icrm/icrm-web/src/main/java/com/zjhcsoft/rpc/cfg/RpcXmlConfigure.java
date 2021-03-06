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
import com.zjhcsoft.rpc.cfg.domain.ParamDomain;
import com.zjhcsoft.rpc.cfg.domain.ProtocolDomain;
import com.zjhcsoft.rpc.cfg.domain.RPCDomain;

public class RpcXmlConfigure {
	private static Log LOG = LogFactory.getLog(RpcXmlConfigure.class);
	private static RPCDomain clientCfgDomain;

//	private static RPCDomain serverCfgDomain;
	
//	private static ThreadLocal ServerDomainThreadLocal = new ThreadLocal();

	private static ThreadLocal ProtocolDomainThreadLocal = new ThreadLocal();

//	private static ThreadLocal InterceptorDomainThreadLocal = new ThreadLocal();

	static {
		LOG.debug("rpc_client.xml load start!");
		NodeletParser parser = new NodeletParser();
		addClientParser(parser);
		try {
			InputStream clientIs = NodeletParser.class.getClassLoader()
					.getResourceAsStream("rpc_client.xml");
			if (clientIs != null) {
				parser.parse(clientIs);
				try {
					clientIs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (NodeletException e) {
			e.printStackTrace();
		}
		LOG.debug("rpc_client.xml load end!");
//		parser = new NodeletParser();
//		addServerParser(parser);
//		try {
//			InputStream serverIs = NodeletParser.class.getClassLoader()
//					.getResourceAsStream("rpc_server.xml");
//			if (serverIs != null) {
//				parser.parse(serverIs);
//				try {
//					serverIs.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		} catch (NodeletException e) {
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args){
		RpcXmlConfigure cif=new RpcXmlConfigure();
//		System.out.println("====="+clientCfgDomain.getServerList().size());
		for(ProtocolDomain server:(List<ProtocolDomain>)clientCfgDomain.getProtocolList()){
			System.out.println("="+server.getData_protocol());
		}
	}

	public static RPCDomain getClientOpenCfgDomain() {
		return clientCfgDomain;
	}

//	public static RPCDomain getServerCfgDomain() {
//		return serverCfgDomain;
//	}

	private static void addClientParser(NodeletParser parser) {
		clientCfgDomain = new RPCDomain();
		parser.addNodelet("/rpc_client/protocols/protocol", new Nodelet() {
			public void process(Node node) throws Exception {
				NamedNodeMap map = node.getAttributes();
				String transport_protocol = NodeletUtils.getNodeValue(map,
						"transport_protocol");
				String data_protocol = NodeletUtils.getNodeValue(map,
						"data_protocol");
				String interfaceClassName = NodeletUtils.getNodeValue(map,
						"interfaceClassName");
				String className = NodeletUtils.getNodeValue(map, "className");
				if (className == null || "".equalsIgnoreCase(className.trim())) {
					return;
				}
				ProtocolDomain protocolDomain = new ProtocolDomain();
				protocolDomain.setData_protocol(data_protocol);
				protocolDomain.setTransport_protocol(transport_protocol);
				protocolDomain.setInterfaceClassName(interfaceClassName);
				protocolDomain.setClassName(className);
				clientCfgDomain.addProtocolDomain(protocolDomain);
				ProtocolDomainThreadLocal.set(protocolDomain);
			}
		});

		parser.addNodelet("/rpc_client/protocols/protocol/param",
				new Nodelet() {
					public void process(Node node) throws Exception {
						NamedNodeMap map = node.getAttributes();
						String name = NodeletUtils.getNodeValue(map, "name");
						String value = NodeletUtils.getNodeValue(map, "value");
						if (name == null || "".equalsIgnoreCase(name.trim())
								|| value == null
								|| "".equalsIgnoreCase(value.trim())) {
							return;
						}
						ParamDomain paramDomain = new ParamDomain();
						paramDomain.setName(name);
						paramDomain.setValue(value);
						ProtocolDomain threadLocalProtocolDomain = (ProtocolDomain) ProtocolDomainThreadLocal
								.get();
						threadLocalProtocolDomain.addParamDomain(paramDomain);
					}
				});

		parser.addNodelet("/rpc_client/protocols/protocol/param/end()",
				new Nodelet() {
					public void process(Node node) throws Exception {
						// pass
					}
				});

//		parser.addNodelet("/rpc_client/interceptors/interceptor",
//				new Nodelet() {
//					public void process(Node node) throws Exception {
//						NamedNodeMap map = node.getAttributes();
//						String name = NodeletUtils.getNodeValue(map, "name");
//						String className = NodeletUtils.getNodeValue(map,
//								"className");
//						InterceptorDomain interceptorDomain = new InterceptorDomain();
//						interceptorDomain.setName(name);
//						interceptorDomain.setClassName(className);
//						InterceptorDomainThreadLocal.set(interceptorDomain);
//						clientCfgDomain
//								.addInterceptorDomain(interceptorDomain);
//					}
//				});
//
//		parser.addNodelet("/rpc_client/interceptors/interceptor/param",
//				new Nodelet() {
//					public void process(Node node) throws Exception {
//						NamedNodeMap map = node.getAttributes();
//						String name = NodeletUtils.getNodeValue(map, "name");
//						String value = NodeletUtils.getNodeValue(map, "value");
//						if (name == null || "".equalsIgnoreCase(name.trim())
//								|| value == null
//								|| "".equalsIgnoreCase(value.trim())) {
//							return;
//						}
//						ParamDomain paramDomain = new ParamDomain();
//						paramDomain.setName(name);
//						paramDomain.setValue(value);
//						InterceptorDomain threadLocalInterceptorDomain = (InterceptorDomain) InterceptorDomainThreadLocal
//								.get();
//						threadLocalInterceptorDomain
//								.addParamDomain(paramDomain);
//					}
//				});
//
//		parser.addNodelet(
//				"/rpc_client/interceptors/interceptor/param/end()",
//				new Nodelet() {
//					public void process(Node node) throws Exception {
//						// pass
//					}
//				});
	}

//	private static void addServerParser(NodeletParser parser) {
//		serverCfgDomain = new RPCDomain();
//		parser.addNodelet("/rpc_server/servers/server", new Nodelet() {
//			public void process(Node node) throws Exception {
//				NamedNodeMap map = node.getAttributes();
//				String name = NodeletUtils.getNodeValue(map,
//						"name");
//				String className = NodeletUtils.getNodeValue(map,
//						"className");
//				if (className == null || "".equalsIgnoreCase(className.trim())) {
//					return;
//				}
//				ServerDomain serverDomain = new ServerDomain();
//				serverDomain.setName(name);
//				serverDomain.setClassName(className);
//				serverCfgDomain.addServerDomain(serverDomain);
//				ServerDomainThreadLocal.set(serverDomain);
// 			}
//		});
//
//		parser.addNodelet("/rpc_server/servers/server/param",
//				new Nodelet() {
//					public void process(Node node) throws Exception {
//						NamedNodeMap map = node.getAttributes();
//						String name = NodeletUtils.getNodeValue(map, "name");
//						String value = NodeletUtils.getNodeValue(map, "value");
//						if (name == null || "".equalsIgnoreCase(name.trim())
//								|| value == null
//								|| "".equalsIgnoreCase(value.trim())) {
//							return;
//						}
//						ParamDomain paramDomain = new ParamDomain();
//						paramDomain.setName(name);
//						paramDomain.setValue(value);
//						ServerDomain threadLocalServerDomain = (ServerDomain) ServerDomainThreadLocal
//								.get();
//						threadLocalServerDomain.addParamDomain(paramDomain);
//					}
//				});
//
//		parser.addNodelet("/rpc_server/servers/server/param/end()",
//				new Nodelet() {
//					public void process(Node node) throws Exception {
//						// pass
//					}
//				});
//		
//		
//		parser.addNodelet("/rpc_server/protocols/protocol", new Nodelet() {
//			public void process(Node node) throws Exception {
//				NamedNodeMap map = node.getAttributes();
//				String transport_protocol = NodeletUtils.getNodeValue(map,
//						"transport_protocol");
//				String data_protocol = NodeletUtils.getNodeValue(map,
//						"data_protocol");
//				String interfaceClassName = NodeletUtils.getNodeValue(map,
//						"interfaceClassName");
//				String className = NodeletUtils.getNodeValue(map, "className");
//				if (className == null || "".equalsIgnoreCase(className.trim())) {
//					return;
//				}
//				ProtocolDomain protocolDomain = new ProtocolDomain();
//				protocolDomain.setData_protocol(data_protocol);
//				protocolDomain.setTransport_protocol(transport_protocol);
//				protocolDomain.setInterfaceClassName(interfaceClassName);
//				protocolDomain.setClassName(className);
//				serverCfgDomain.addProtocolDomain(protocolDomain);
//				ProtocolDomainThreadLocal.set(protocolDomain);
//			}
//		});
//
//		parser.addNodelet("/rpc_server/protocols/protocol/param",
//				new Nodelet() {
//					public void process(Node node) throws Exception {
//						NamedNodeMap map = node.getAttributes();
//						String name = NodeletUtils.getNodeValue(map, "name");
//						String value = NodeletUtils.getNodeValue(map, "value");
//						if (name == null || "".equalsIgnoreCase(name.trim())
//								|| value == null
//								|| "".equalsIgnoreCase(value.trim())) {
//							return;
//						}
//						ParamDomain paramDomain = new ParamDomain();
//						paramDomain.setName(name);
//						paramDomain.setValue(value);
//						ProtocolDomain threadLocalProtocolDomain = (ProtocolDomain) ProtocolDomainThreadLocal
//								.get();
//						threadLocalProtocolDomain.addParamDomain(paramDomain);
//					}
//				});
//
//		parser.addNodelet("/rpc_server/protocols/protocol/param/end()",
//				new Nodelet() {
//					public void process(Node node) throws Exception {
//						// pass
//					}
//				});
//
//		parser.addNodelet("/rpc_server/interceptors/interceptor",
//				new Nodelet() {
//					public void process(Node node) throws Exception {
//						NamedNodeMap map = node.getAttributes();
//						String name = NodeletUtils.getNodeValue(map, "name");
//						String className = NodeletUtils.getNodeValue(map,
//								"className");
//						InterceptorDomain interceptorDomain = new InterceptorDomain();
//						interceptorDomain.setName(name);
//						interceptorDomain.setClassName(className);
//						InterceptorDomainThreadLocal.set(interceptorDomain);
//						serverCfgDomain
//								.addInterceptorDomain(interceptorDomain);
//					}
//				});
//
//		parser.addNodelet("/rpc_server/interceptors/interceptor/param",
//				new Nodelet() {
//					public void process(Node node) throws Exception {
//						NamedNodeMap map = node.getAttributes();
//						String name = NodeletUtils.getNodeValue(map, "name");
//						String value = NodeletUtils.getNodeValue(map, "value");
//						if (name == null || "".equalsIgnoreCase(name.trim())
//								|| value == null
//								|| "".equalsIgnoreCase(value.trim())) {
//							return;
//						}
//						ParamDomain paramDomain = new ParamDomain();
//						paramDomain.setName(name);
//						paramDomain.setValue(value);
//						InterceptorDomain threadLocalInterceptorDomain = (InterceptorDomain) InterceptorDomainThreadLocal
//								.get();
//						threadLocalInterceptorDomain
//								.addParamDomain(paramDomain);
//					}
//				});
//
//		parser.addNodelet(
//				"/rpc_server/interceptors/interceptor/param/end()",
//				new Nodelet() {
//					public void process(Node node) throws Exception {
//						// pass
//					}
//				});
//	}
}
