package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawResult;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Graphical {@link DrawNumberView} implementation.
 */
public final class DrawNumberSwingView implements DrawNumberView {

    private static final String FRAME_NAME = "Draw Number App";
    private static final String QUIT = "Quit";
    private static final String RESET = "Reset";
    private static final String GO = "Go";
    private static final String NEW_GAME = ": a new game starts!";

    private DrawNumberController controller;
    private final JFrame frame = new JFrame(FRAME_NAME);

    /**
     * Builds a new Swing-based interactive view.
     */
    public DrawNumberSwingView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JPanel(new BorderLayout()));
        final JPanel pNorth = new JPanel(new FlowLayout());
        final JTextField tNumber = new JTextField(10);
        final JButton bGo = new JButton(GO);
        pNorth.add(tNumber);
        pNorth.add(bGo);
        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton bReset = new JButton(RESET);
        final JButton bQuit = new JButton(QUIT);
        pSouth.add(bReset);
        pSouth.add(bQuit);
        frame.getContentPane().add(pNorth, BorderLayout.NORTH);
        frame.getContentPane().add(pSouth, BorderLayout.SOUTH);
        bGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.newAttempt(Integer.parseInt(tNumber.getText()));
                } catch (final NumberFormatException exception) {
                    showMessageDialog(frame, "An integer please..");
                }
            }
        });
        bQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (confirmDialog("Confirm quitting?", "Quit")) {
                    controller.quit();
                }
            }
        });
        bReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (confirmDialog("Confirm resetting?", "Reset")) {
                    controller.resetGame();
                }
            }
        });

        frame.pack();
        frame.setLocationByPlatform(true);
    }

    @Override
    public void start() {
        this.frame.setVisible(true);
    }

    private boolean confirmDialog(final String question, final String name) {
        return showConfirmDialog(frame, question, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    @Override
    public void setController(final DrawNumberController observer) {
        this.controller = observer;
    }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW -> {
                plainMessage(res.getDescription());
                return;
            }
            case YOU_WON -> plainMessage(res.getDescription() + NEW_GAME);
            case YOU_LOST -> showMessageDialog(
                frame,
                res.getDescription() + NEW_GAME, "Lost",
                JOptionPane.WARNING_MESSAGE
            );
        }
        controller.resetGame();
    }

    private void plainMessage(final String msg) {
        showMessageDialog(frame, msg, "Result", JOptionPane.PLAIN_MESSAGE);
    }
}
