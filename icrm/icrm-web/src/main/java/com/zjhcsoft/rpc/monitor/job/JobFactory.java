/**
 *  Copyright 2010 Wallace Wadge
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/*

 Copyright 2009 Wallace Wadge

 This file is part of BoneCP.

 BoneCP is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 BoneCP is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with BoneCP.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.zjhcsoft.rpc.monitor.job;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JobFactory implements ThreadFactory, UncaughtExceptionHandler {

	private boolean daemon;
	private String threadName;
	private static Log LOG = LogFactory.getLog(JobFactory.class);

	public JobFactory(String threadName, boolean daemon) {
		this.threadName = threadName;
		this.daemon = daemon;
		System.out.println("hello world");
	}

	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, this.threadName);
		t.setDaemon(this.daemon);
		t.setUncaughtExceptionHandler(this);
		return t;
	}

	public void uncaughtException(Thread thread, Throwable throwable) {
		LOG.error("Uncaught Exception in thread " + thread.getName(),throwable);
	}

}
