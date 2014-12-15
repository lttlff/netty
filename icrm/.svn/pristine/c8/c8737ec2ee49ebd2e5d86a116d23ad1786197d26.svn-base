package com.zjhcsoft.rpc.cfg;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.zjhcsoft.component.xml2.Nodelet;
import com.zjhcsoft.component.xml2.NodeletException;
import com.zjhcsoft.component.xml2.NodeletParser;
import com.zjhcsoft.component.xml2.NodeletUtils;
import com.zjhcsoft.rpc.cfg.domain.InterceptorDomain;
import com.zjhcsoft.rpc.cfg.domain.InterceptorStackDomain;
import com.zjhcsoft.rpc.cfg.domain.ParamDomain;
import com.zjhcsoft.rpc.cfg.domain.ProtocolDomain;
import com.zjhcsoft.rpc.cfg.domain.RPCDomainServer;
import com.zjhcsoft.rpc.cfg.domain.ServerDomain;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class RpcXmlConfigure_server {
	private static final String NAME="name";
	private static final String PATTERN="pattern";
	private static final String TYPE="type";
	private static final String MODE="mode";

//	private static final String DEFAULT_PATTERN="*";
	private static final String DEFAULT_NAME="default_name";

//	private static RPCDomain clientCfgDomain;

	private static RPCDomainServer serverCfgDomain;
	
//	private static InterceptorStackGroup interceptorStackGroup=InterceptorStackGroup.getInterceptorStackGroupInstance();
	
	private static ThreadLocal ServerDomainThreadLocal = new ThreadLocal();

	private static ThreadLocal ProtocolDomainThreadLocal = new ThreadLocal();

	private static ThreadLocal InterceptorDomainThreadLocal = new ThreadLocal();

	static {
		NodeletParser parser = new NodeletParser();
//		addClientParser(parser);
//		try {
//			InputStream clientIs = NodeletParser.class.getClassLoader()
//					.getResourceAsStream("rpc_client.xml");
//			if (clientIs != null) {
//				parser.parse(clientIs);
//				try {
//					clientIs.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		} catch (NodeletException e) {
//			e.printStackTrace();
//		}
//
//		parser = new NodeletParser();
		addServerParser(parser);
		try {
			InputStream serverIs = NodeletParser.class.getClassLoader()
					.getResourceAsStream("rpc_server.xml");
			if (serverIs != null) {
				parser.parse(serverIs);
				try {
					serverIs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (NodeletException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		RpcXmlConfigure_server cif=new RpcXmlConfigure_server();
//		System.out.println("====="+clientCfgDomain.getServerList().size());
		for(InterceptorStackDomain interceptor:(List<InterceptorStackDomain>)cif.getServerCfgDomain().getInterceptorStackList()){
			System.out.println("="+interceptor.getMode());
		}
	}

//	public static RPCDomain getClientOpenCfgDomain() {
//		return clientCfgDomain;
//	}

	public static RPCDomainServer getServerCfgDomain() {
		return serverCfgDomain;
	}

//	private static void addClientParser(NodeletParser parser) {
//		clientCfgDomain = new RPCDomain();
//		parser.addNodelet("/rpc_client/protocols/protocol", new Nodelet() {
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
//				clientCfgDomain.addProtocolDomain(protocolDomain);
//				ProtocolDomainThreadLocal.set(protocolDomain);
//			}
//		});
//
//		parser.addNodelet("/rpc_client/protocols/protocol/param",
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
//		parser.addNodelet("/rpc_client/protocols/protocol/param/end()",
//				new Nodelet() {
//					public void process(Node node) throws Exception {
//						// pass
//					}
//				});
//
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
//	}

	private static void addServerParser(NodeletParser parser) {
		serverCfgDomain = new RPCDomainServer();
		parser.addNodelet("/rpc_server/servers/server", new Nodelet() {
			public void process(Node node) throws Exception {
				NamedNodeMap map = node.getAttributes();
				String name = NodeletUtils.getNodeValue(map,
						"name");
				String className = NodeletUtils.getNodeValue(map,
						"className");
				if (className == null || "".equalsIgnoreCase(className.trim())) {
					return;
				}
				ServerDomain serverDomain = new ServerDomain();
				serverDomain.setName(name);
				serverDomain.setClassName(className);
				serverCfgDomain.addServerDomain(serverDomain);
				ServerDomainThreadLocal.set(serverDomain);
 			}
		});

		parser.addNodelet("/rpc_server/servers/server/param",
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
						ServerDomain threadLocalServerDomain = (ServerDomain) ServerDomainThreadLocal
								.get();
						threadLocalServerDomain.addParamDomain(paramDomain);
					}
				});

		parser.addNodelet("/rpc_server/servers/server/param/end()",
				new Nodelet() {
					public void process(Node node) throws Exception {
						// pass
					}
				});
		
		
		parser.addNodelet("/rpc_server/protocols/protocol", new Nodelet() {
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
				serverCfgDomain.addProtocolDomain(protocolDomain);
				ProtocolDomainThreadLocal.set(protocolDomain);
			}
		});

		parser.addNodelet("/rpc_server/protocols/protocol/param",
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

		parser.addNodelet("/rpc_server/protocols/protocol/param/end()",
				new Nodelet() {
					public void process(Node node) throws Exception {
						// pass
					}
				});
		
		parser.addNodelet("/rpc_server/interceptors",
				new Nodelet() {
					public void process(Node node) throws Exception {
						NamedNodeMap map = node.getAttributes();
						
						String name = NodeletUtils.getNodeValue(map, RpcXmlConfigure_server.NAME);
						if (name == null || "".equalsIgnoreCase(name.trim())) {
							name=RpcXmlConfigure_server.DEFAULT_NAME;
						}
						String pattern = NodeletUtils.getNodeValue(map, RpcXmlConfigure_server.PATTERN);
						if (pattern == null || "".equalsIgnoreCase(pattern.trim())) {
							pattern=RpcXmlConfigure_server.PATTERN;
						}
						String type = NodeletUtils.getNodeValue(map, RpcXmlConfigure_server.TYPE);
						if (type == null || "".equalsIgnoreCase(type.trim())) {
							type=RpcXmlConfigure_server.TYPE;
						}
						String mode = NodeletUtils.getNodeValue(map, RpcXmlConfigure_server.MODE);
						if (mode == null || "".equalsIgnoreCase(mode.trim())) {
							mode=RpcXmlConfigure_server.MODE;
						}
						InterceptorStackDomain interceptorStackDomain = new InterceptorStackDomain();
						interceptorStackDomain.setName(name);
						interceptorStackDomain.setPattern(pattern);
						interceptorStackDomain.setType(type);
						interceptorStackDomain.setMode(mode);
						
						serverCfgDomain.addInterceptorStackDomain(interceptorStackDomain);
						ServerDomainThreadLocal.set(interceptorStackDomain);
					}
				});

		parser.addNodelet("/rpc_server/interceptors/interceptor",
				new Nodelet() {
					public void process(Node node) throws Exception {
						NamedNodeMap map = node.getAttributes();
						String name = NodeletUtils.getNodeValue(map, RpcXmlConfigure_server.NAME);
						String className = NodeletUtils.getNodeValue(map,
								"className");
						InterceptorDomain interceptorDomain = new InterceptorDomain();
						interceptorDomain.setName(name);
						interceptorDomain.setClassName(className);
						InterceptorDomainThreadLocal.set(interceptorDomain);
						InterceptorStackDomain interceptorStackDomain = (InterceptorStackDomain)ServerDomainThreadLocal.get();
						interceptorStackDomain.addInterceptorDomain(interceptorDomain);
//								.addInterceptorDomain(interceptorDomain);
						InterceptorDomainThreadLocal.set(interceptorDomain);
					}
				});

		parser.addNodelet("/rpc_server/interceptors/interceptor/param",
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
						InterceptorDomain threadLocalInterceptorDomain = (InterceptorDomain) InterceptorDomainThreadLocal
								.get();
						threadLocalInterceptorDomain
								.addParamDomain(paramDomain);
					}
				});

		parser.addNodelet(
				"/rpc_server/interceptors/interceptor/param/end()",
				new Nodelet() {
					public void process(Node node) throws Exception {
						// pass
					}
				});
	}
}
