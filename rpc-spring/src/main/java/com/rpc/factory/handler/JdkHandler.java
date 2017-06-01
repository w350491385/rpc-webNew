package com.rpc.factory.handler;

import java.lang.reflect.Method;

import com.rpc.factory.RpcData;
import com.rpc.factory.balance.Balance;
import com.rpc.factory.cluster.Cluster;
import com.rpc.factory.protocol.Protocol;

/**
 * hessian处理
 * @author Administrator
 */
public class JdkHandler implements Handler {

	private Cluster cluster;//集群
	private Protocol protocol;//协议
	private Balance balance;//负载均衡
	private Class<?> clazz;//代理对象字节
	private String url;//地址
	
	public JdkHandler(Cluster cluster, Protocol protocol, Class<?> clazz, String url,Balance balance) {
		super();
		this.cluster = cluster;
		this.protocol = protocol;
		this.clazz = clazz;
		this.url = url;
		this.balance = balance;
	}

	@Override
	public Object exec(Method method, Object[] args) throws Exception {
		return cluster.cluster(balance,protocol, new RpcData(method, args,clazz, url));
	}

}