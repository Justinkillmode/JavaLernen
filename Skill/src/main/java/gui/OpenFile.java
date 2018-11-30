package gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFile {

	public static String path;

	public static void thoseImage() {
		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filterPng = new FileNameExtensionFilter("png", "png");
		FileNameExtensionFilter filterJpg = new FileNameExtensionFilter("jpg", "jpg");
		fileChooser.addChoosableFileFilter(filterPng);
		fileChooser.addChoosableFileFilter(filterJpg);
		int result = fileChooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {

			File selectedFile = fileChooser.getSelectedFile();
			path = selectedFile.getAbsolutePath();

		}

	}

}