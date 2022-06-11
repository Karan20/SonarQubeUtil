package src.main.java.com.theparanoid.sonarqube.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import src.main.java.com.theparanoid.sonarqube.AppContext;
import src.main.java.com.theparanoid.sonarqube.constant.Constant;
import src.main.java.com.theparanoid.sonarqube.util.Util;

public class Windows extends Canvas {

	private static final long serialVersionUID = 1L;

	private AppContext context;

	public Windows(int width, int height, String title, App main) {
		context = AppContext.getContext();
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setBackground(Color.WHITE);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.add(createDockerPanel());
		frame.add(createSonarPanel());
		frame.setVisible(true);
	}

	private JPanel createDockerPanel() {
		JPanel parentPanel = new JPanel();
		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 10, 0);
		parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.PAGE_AXIS));
		parentPanel.setName("dockerPanel");
		JPanel titlePanel = new JPanel();
		JPanel statusPanel = new JPanel();
		JPanel clientPanel = new JPanel(flowLayout);
		JPanel serverPanel = new JPanel(flowLayout);
		statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		JPanel refreshButtonPanel = new JPanel();
		refreshButtonPanel.setLayout(flowLayout);
		JLabel label = new JLabel();
		label.setText("Docker Status");
		label.setName("label1");
		JLabel clientLabel = new JLabel();
		JLabel serverLabel = new JLabel();
		JLabel clientStatusLabel = new JLabel();
		JLabel serverStatusLabel = new JLabel();
		clientLabel.setText("Docker client:");
		clientStatusLabel.setText("not running");
		clientStatusLabel.setForeground(Color.red);
		serverLabel.setText("Docker server:");
		serverStatusLabel.setText("not running");
		serverStatusLabel.setForeground(Color.red);
		Util util = new Util();
		Map<String, Boolean> dockerStatus = util.checkDockerStatus();
		if (dockerStatus != null) {
			if (dockerStatus.get(Constant.IS_DOCKER_CLIENT_UP)) {
				clientLabel.setText("Docker client:");
				clientStatusLabel.setText("running");
				clientStatusLabel.setForeground(Color.blue);
			}

			if (dockerStatus.get(Constant.IS_DOCKER_SERVER_UP)) {
				serverLabel.setText("Docker server:");
				serverStatusLabel.setText("running");
				serverStatusLabel.setForeground(Color.blue);
			}

			context.setDockerStatus(
					dockerStatus.get(Constant.IS_DOCKER_SERVER_UP) && dockerStatus.get(Constant.IS_DOCKER_CLIENT_UP));
		}
		
		JButton refresh = new JButton();
		refresh.setText("Refresh Status");
		refresh.addActionListener(ae -> {
			Map<String, Boolean> status = util.checkDockerStatus();
			if (status != null) {
				if (status.get(Constant.IS_DOCKER_CLIENT_UP)) {
					clientLabel.setText("Docker client:");
					clientStatusLabel.setText("running");
					clientStatusLabel.setForeground(Color.blue);
				}

				if (status.get(Constant.IS_DOCKER_SERVER_UP)) {
					serverLabel.setText("Docker server:");
					serverStatusLabel.setText("running");
					serverStatusLabel.setForeground(Color.blue);
				}

				context.setDockerStatus(
						status.get(Constant.IS_DOCKER_SERVER_UP) && status.get(Constant.IS_DOCKER_CLIENT_UP));
			}
		});
		refresh.setBackground(Color.white);
		refresh.setFocusPainted(false);
		refreshButtonPanel.add(refresh);
		
		titlePanel.add(label);
		clientPanel.add(clientLabel);
		clientPanel.add(clientStatusLabel);
		serverPanel.add(serverLabel);
		serverPanel.add(serverStatusLabel);
		statusPanel.add(clientPanel);
		statusPanel.add(serverPanel);
		parentPanel.add(titlePanel);
		parentPanel.add(statusPanel);
		parentPanel.add(refreshButtonPanel);
		parentPanel.setPreferredSize(new Dimension(Constant.WINDOW_WIDTH, 100));
		parentPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		parentPanel.setBackground(Color.gray);
		return parentPanel;
	}

	private JPanel createSonarPanel() {
		JPanel parentPanel = new JPanel();
		parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.PAGE_AXIS));
		parentPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.white));
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JLabel title = new JLabel();
		title.setText("SonarQube");
		
		titlePanel.add(title);
		parentPanel.add(titlePanel);
		parentPanel.setPreferredSize(new Dimension(Constant.WINDOW_WIDTH, 200));
		parentPanel.setBackground(Color.gray);
		
		return parentPanel;
	}
	
}
