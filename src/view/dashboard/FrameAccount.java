/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.dashboard;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Haida
 */
public class FrameAccount extends FramePrimary {
    public FrameAccount() {
        super.panelContent = new JPanel();
        
        JLabel lJudul = new JLabel("Account", JLabel.CENTER);
        
        panelContent.add(lJudul);
    }
}
