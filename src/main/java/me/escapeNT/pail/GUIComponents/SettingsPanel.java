
package me.escapeNT.pail.GUIComponents;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import me.escapeNT.pail.Pail;
import me.escapeNT.pail.config.General;
import me.escapeNT.pail.config.PanelConfig;
import me.escapeNT.pail.config.ServerConfigHandler;
import me.escapeNT.pail.Util.UpdateHandler;
import me.escapeNT.pail.Util.Util;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;

/**
 * Panel for editing server settings.
 * @author escapeNT
 */
public class SettingsPanel extends javax.swing.JPanel {

    private WaypointEditPanel waypointEditor;

    /** Creates new form SettingsPanel */
    public SettingsPanel() {

        initComponents();
        waypointEditor = new WaypointEditPanel();

        craftVersion.setText("Craftbukkit version: " + parseCraftVersion());
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                parseCraftUpdate();
            }
        });
        pailVersion.setText("Pail version: " + Pail.PLUGIN_VERSION);
 
        autoUpdate.setSelected(General.isAutoUpdate());
        loadConfig();

        settingsTabs.add("Waypoints", waypointEditor);

        autoUpdate.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                General.setAutoUpdate(autoUpdate.isSelected());
                General.save();

                if(autoUpdate.isSelected() && UpdateHandler.isUpToDate() != null
                        && !UpdateHandler.isUpToDate()) {
                    new UpdateView().setVisible(true);
                }
            }
        });

        for(LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {   
            try {
                Class feel = Class.forName(laf.getClassName());
                if(((LookAndFeel)feel.newInstance()).isSupportedLookAndFeel()) {
                    themes.addItem(laf.getName());
                    if(laf.getClassName().equals(General.getLookAndFeel())) {
                        themes.setSelectedItem(laf.getName());
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String parseCraftVersion() {
        try {
            String v = Bukkit.getServer().getVersion();
            return v.substring(v.indexOf("jnks") - 4, v.indexOf("jnks"));
        } catch(Exception ex) {
            return "0000";
        }
    }

    private void parseCraftUpdate() {
        try {
            URL url = new URL("http://ci.bukkit.org/job/dev-CraftBukkit/Recommended/buildNumber");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String v = in.readLine();
            in.close();

            boolean upToDate = false;
            if(Integer.parseInt(parseCraftVersion()) >= Integer.parseInt(v)) {
                upToDate = true;
            }

            if(upToDate) {
                update.setText("Latest recommended build: " + v);
                update.setForeground(new Color(13, 190, 17));
            }
            else {
                update.setText("Latest recommended build: " + v + " - Update required!");
                update.setForeground(Color.red);
            }
        } catch (Exception ex) {
            update.setText("Latest recommended build: Unknown");
                update.setForeground(new Color(255, 200, 33));
        }
    }

    /**
     * Loads the stored values from the server configuration.
     */
    private void loadConfig() {
        Server s = Bukkit.getServer();
        World main = s.getWorlds().get(0);

        worldName.setText(main.getName());
        seed.setText(new Long(main.getSeed()).toString());
        ip.setText(s.getIp());

        nether.setSelected(s.getAllowNether());
        spawnMonsters.setSelected(main.getAllowMonsters());
        spawnAnimals.setSelected(main.getAllowAnimals());
        flight.setSelected(s.getAllowFlight());
        pvp.setSelected(main.getPVP());
        online.setSelected(s.getOnlineMode());
        whitelist.setSelected(s.hasWhitelist());

        viewDistance.setValue(s.getViewDistance());
        port.setValue(s.getPort());
        maxPlayers.setValue(s.getMaxPlayers());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        settingsTabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        worldName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ip = new javax.swing.JTextField();
        nether = new javax.swing.JCheckBox();
        spawnMonsters = new javax.swing.JCheckBox();
        spawnAnimals = new javax.swing.JCheckBox();
        online = new javax.swing.JCheckBox();
        pvp = new javax.swing.JCheckBox();
        whitelist = new javax.swing.JCheckBox();
        flight = new javax.swing.JCheckBox();
        viewDistance = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        port = new javax.swing.JSpinner();
        revert = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        maxPlayers = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        seed = new javax.swing.JTextField();
        craftVersion = new javax.swing.JLabel();
        pailVersion = new javax.swing.JLabel();
        update = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        tabActivationPanel = new me.escapeNT.pail.GUIComponents.TabActivationPanel();
        reload = new javax.swing.JButton();
        autoUpdate = new javax.swing.JCheckBox();
        themes = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();

        setLayout(null);

        settingsTabs.setFocusable(false);

        jPanel1.setFocusable(false);
        jPanel1.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Server  Settings"));
        jPanel2.setLayout(null);

        jLabel1.setText("World name");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(30, 30, 74, 16);

        worldName.setToolTipText("The name of the default world on the server");
        jPanel2.add(worldName);
        worldName.setBounds(110, 20, 224, 28);

        jLabel2.setText("World seed");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(30, 70, 69, 16);

        ip.setToolTipText("Set this if you want the server to bind to a particular IP.");
        jPanel2.add(ip);
        ip.setBounds(110, 100, 225, 28);

        nether.setText("Allow nether");
        nether.setToolTipText("Allow portal transport to the nether.");
        jPanel2.add(nether);
        nether.setBounds(30, 140, 111, 23);

        spawnMonsters.setText("Spawn monsters");
        spawnMonsters.setToolTipText("Spawn hostile monsters.");
        jPanel2.add(spawnMonsters);
        spawnMonsters.setBounds(30, 180, 135, 23);

        spawnAnimals.setText("Spawn animals");
        spawnAnimals.setToolTipText("Spawn non-hostile animals.");
        jPanel2.add(spawnAnimals);
        spawnAnimals.setBounds(30, 220, 125, 23);

        online.setText("Online mode");
        online.setToolTipText("Server checks connecting players against minecraft's account database.");
        jPanel2.add(online);
        online.setBounds(190, 180, 112, 23);

        pvp.setText("Enable PVP");
        pvp.setToolTipText("Enable player verses player damage.");
        jPanel2.add(pvp);
        pvp.setBounds(190, 140, 99, 23);

        whitelist.setText("Whitelist enabled");
        whitelist.setToolTipText("With a whitelist enabled, users not on the list will be unable to connect.");
        jPanel2.add(whitelist);
        whitelist.setBounds(190, 220, 140, 23);

        flight.setText("Allow flight");
        flight.setToolTipText("Will allow users to use flight/no-clip on the server.");
        jPanel2.add(flight);
        flight.setBounds(30, 260, 105, 23);

        viewDistance.setToolTipText("The number of chunks sent to the client. (3-15)");
        jPanel2.add(viewDistance);
        viewDistance.setBounds(130, 290, 55, 28);

        jLabel3.setText("View distance");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(30, 290, 86, 30);

        jLabel4.setText("Server port");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(30, 330, 68, 30);

        port.setToolTipText("Port on which the server is running.");
        jPanel2.add(port);
        port.setBounds(110, 330, 96, 28);

        revert.setText("Revert");
        revert.setFocusable(false);
        revert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revertActionPerformed(evt);
            }
        });
        jPanel2.add(revert);
        revert.setBounds(190, 370, 83, 29);

        save.setText("Save");
        save.setFocusable(false);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel2.add(save);
        save.setBounds(280, 370, 75, 29);

        jLabel5.setText("Max players");
        jLabel5.setToolTipText("The maximum number of players allowed to connect.");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(200, 290, 80, 30);
        jPanel2.add(maxPlayers);
        maxPlayers.setBounds(280, 290, 60, 28);

        jLabel6.setText("Server IP");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(40, 100, 70, 30);

        seed.setToolTipText("The seed used in generating new terrain.");
        jPanel2.add(seed);
        seed.setBounds(110, 60, 225, 28);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 10, 370, 410);

        craftVersion.setText("Craftbukkit version:");
        jPanel1.add(craftVersion);
        craftVersion.setBounds(390, 10, 450, 20);

        pailVersion.setText("Pail version:");
        pailVersion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pailVersionMouseClicked(evt);
            }
        });
        jPanel1.add(pailVersion);
        pailVersion.setBounds(390, 50, 170, 20);

        update.setText("Latest recommended build:");
        jPanel1.add(update);
        update.setBounds(390, 30, 450, 20);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Active Tabs"));

        tabActivationPanel.setLayout(new java.awt.GridLayout(0, 2));
        tabActivationPanel.setBounds(10, 20, 420, 240);
        jLayeredPane1.add(tabActivationPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        reload.setText("Save");
        reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadActionPerformed(evt);
            }
        });
        reload.setBounds(350, 260, 80, 30);
        jLayeredPane1.add(reload, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel1.add(jLayeredPane1);
        jLayeredPane1.setBounds(390, 120, 440, 300);

        autoUpdate.setText("Automatically check for updates");
        autoUpdate.setFocusable(false);
        jPanel1.add(autoUpdate);
        autoUpdate.setBounds(560, 50, 240, 23);

        themes.setFocusable(false);
        themes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                themesItemStateChanged(evt);
            }
        });
        jPanel1.add(themes);
        themes.setBounds(430, 90, 400, 20);

        jLabel7.setText("Skin");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(390, 90, 50, 20);

        settingsTabs.addTab("General", jPanel1);

        add(settingsTabs);
        settingsTabs.setBounds(-5, 2, 880, 470);
    }// </editor-fold>//GEN-END:initComponents

    private void reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadActionPerformed
        for(JCheckBox b : tabActivationPanel.getBoxes().values()) {
            PanelConfig.getPanelsActivated().put(b.getText(), b.isSelected());
        }
        PanelConfig.save();
        Util.getPlugin().getMainWindow().loadPanels();
}//GEN-LAST:event_reloadActionPerformed

    private void pailVersionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pailVersionMouseClicked
        new AboutView().setVisible(true);
}//GEN-LAST:event_pailVersionMouseClicked

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        HashMap<String, String> saveData = new HashMap<String, String>();

        saveData.put("server-ip", ip.getText());
        saveData.put("level-name", worldName.getText());
        saveData.put("level-seed", seed.getText());

        saveData.put("allow-nether", Boolean.toString(nether.isSelected()));
        saveData.put("spawn-monsters", Boolean.toString(spawnMonsters.isSelected()));
        saveData.put("spawn-animals", Boolean.toString(spawnAnimals.isSelected()));
        saveData.put("allow-flight", Boolean.toString(flight.isSelected()));
        saveData.put("pvp", Boolean.toString(pvp.isSelected()));
        saveData.put("online-mode", Boolean.toString(online.isSelected()));
        saveData.put("white-list", Boolean.toString(whitelist.isSelected()));

        saveData.put("view-distance", viewDistance.getValue().toString());
        saveData.put("server-port", port.getValue().toString());
        saveData.put("max-players", maxPlayers.getValue().toString());

        ServerConfigHandler.save(saveData);

        JOptionPane.showMessageDialog(Util.getPlugin().getMainWindow(),
                "Server config saved!\nRestart the server to apply.", "Config Saved", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_saveActionPerformed

    private void revertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revertActionPerformed
        loadConfig();
}//GEN-LAST:event_revertActionPerformed

    private void themesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_themesItemStateChanged
       if(evt.getStateChange() == ItemEvent.SELECTED && getThemes().getSelectedItem() != null) {
            for(final LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                if(laf.getName().equals((String)getThemes().getSelectedItem())) {
                    try {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                try {
                                    UIManager.setLookAndFeel((LookAndFeel) Class.forName(laf.getClassName()).newInstance());
                                } catch(Exception ex) {
                                    Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                UIManager.getLookAndFeelDefaults().put("ClassLoader", getClass().getClassLoader());
                                Util.getPlugin().getMainWindow().getRootPane().updateUI();
                                SwingUtilities.updateComponentTreeUI(Util.getPlugin().getMainWindow());
                            }
                        });
                    } catch (Exception ex) {
                        Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_themesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox autoUpdate;
    private javax.swing.JLabel craftVersion;
    private javax.swing.JCheckBox flight;
    private javax.swing.JTextField ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner maxPlayers;
    private javax.swing.JCheckBox nether;
    private javax.swing.JCheckBox online;
    private javax.swing.JLabel pailVersion;
    private javax.swing.JSpinner port;
    private javax.swing.JCheckBox pvp;
    private javax.swing.JButton reload;
    private javax.swing.JButton revert;
    private javax.swing.JButton save;
    private javax.swing.JTextField seed;
    private javax.swing.JTabbedPane settingsTabs;
    private javax.swing.JCheckBox spawnAnimals;
    private javax.swing.JCheckBox spawnMonsters;
    private me.escapeNT.pail.GUIComponents.TabActivationPanel tabActivationPanel;
    private javax.swing.JComboBox themes;
    private javax.swing.JLabel update;
    private javax.swing.JSpinner viewDistance;
    private javax.swing.JCheckBox whitelist;
    private javax.swing.JTextField worldName;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the waypointEditor
     */
    public WaypointEditPanel getWaypointEditor() {
        return waypointEditor;
    }

    /**
     * @return the theme
     */
    public javax.swing.JComboBox getThemes() {
        return themes;
    }
}