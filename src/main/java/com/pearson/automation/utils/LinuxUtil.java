package com.pearson.automation.utils;

import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class LinuxUtil {

	/**
	 * 
	 * @param username
	 * @param password
	 * @param host
	 * @return Session
	 * @throws Exception
	 */
	public static Session connectSession(String username, String password,
			String host) throws Exception {
		Session session = null;
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(username, host, 22);
			session.setPassword(password);
			session.setTimeout(10000);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			System.out.println("Session Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}

	/**
	 * 
	 * @param session
	 * @param scriptDir
	 * @param scriptCommand
	 */
	public static void connectChannelExec(Session session, String scriptDir,
			String scriptCommand) {

		try {
			Channel channel = null;
			ChannelExec channelExec = null;
			channel = session.openChannel("exec");
			channelExec = ((ChannelExec) channel);
			channelExec.setCommand(scriptDir + "&&" + scriptCommand);
			InputStream in = channel.getInputStream();
			channel.connect();
			System.out.println("channel.isConnected()  "
					+ channel.isConnected());
			readInStream(channel, in);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.disconnect();
			System.out.println("Host Session disconnected.");
		}
	}

	/**
	 * 
	 * @param channel
	 * @param in
	 */
	public static void readInStream(Channel channel, InputStream in) {

		try {
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					System.out.println("exit-status: "
							+ channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					System.out.println("Exception is:" + e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			channel.disconnect();
			System.out.println("channel.isClosed()  " + channel.isClosed());
		}

	}

}
