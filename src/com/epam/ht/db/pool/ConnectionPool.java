package com.epam.ht.db.pool;

import static com.epam.ht.resource.PropertyGetter.getProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
	// pool property keys
	public static final String DB_URL = "db.url";
	public static final String DB_USER = "db.user";
	public static final String DB_PASSWORD = "db.password";
	public static final String DB_DRIVER_NAME = "db.driver.name";

	private static final int POOL_SIZE = 20;

	private BlockingQueue<Connection> busyConnections;

	private BlockingQueue<Connection> availableConnections;

	private static ConnectionPool pool;

	private static final Lock lock = new ReentrantLock();

	public static ConnectionPool getInstance() throws Exception {
		if (pool == null) {
			lock.lock();
			try {
				pool = new ConnectionPool();
			} finally {
				lock.unlock();
			}
		}
		return pool;
	}

	private ConnectionPool() throws Exception {
		loadDatabaseDriver();
		// create queues for available and busy connections
		availableConnections = new ArrayBlockingQueue<Connection>(POOL_SIZE);
		busyConnections = new ArrayBlockingQueue<Connection>(POOL_SIZE);
		// fill these queues
		for (int i = 0; i < POOL_SIZE; i++) {
			availableConnections.add(makeNewConnection());
		}
	}

	private Connection makeNewConnection() throws Exception {
		return DriverManager.getConnection(getProperty(DB_URL),
				getProperty(DB_USER), getProperty(DB_PASSWORD));
	}

	private void loadDatabaseDriver() throws Exception {
		Class.forName(getProperty(DB_DRIVER_NAME));
	}

	public Connection getConnection() throws Exception {
		Connection connection = availableConnections.take();
		busyConnections.add(connection);
		return connection;
	}

	public void makeConnectionFree(Connection connection) {
		if (connection != null) {
			busyConnections.remove(connection);
			availableConnections.add(connection);
		}
	}

	public void closeAllConnections() throws Exception {
		closeConnections(busyConnections);
		busyConnections.clear();
		closeConnections(availableConnections);
		availableConnections.clear();
	}

	private void closeConnections(BlockingQueue<Connection> connections)
			throws Exception {
		try {
			Connection connection;
			for (int i = 0; i < connections.size(); i++) {
				connection = connections.poll();
				closeConnection(connection);
			}
		} catch (Exception e) {
			closeConnections(connections);
			throw e;
		}
	}

	private void closeConnection(Connection connection) throws Exception {
		if (!connection.isClosed()) {
			connection.close();
		}
	}
}
