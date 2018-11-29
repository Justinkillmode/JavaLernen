package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NeuerEintragAction implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_F3) {

			NeuerEintrag.neuerEintrag();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}