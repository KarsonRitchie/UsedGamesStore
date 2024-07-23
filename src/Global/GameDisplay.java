/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Global;

import Customer.StorePage;
import java.awt.Color;

/**
 *
 * @author Karson
 * 
 * A class only to host UI elements to display game information in thumbnail like fashion.
 */
public class GameDisplay extends javax.swing.JPanel {

    /**
     * Creates new form GameDisplay
     */
    public GameDisplay() {
        initComponents();
        
    }
    
    //Make sure to also create an integer that will hold the index
    //This is so we can show the chosen item
    public int indexHeld = 0;
    //0 as default value

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameInfo = new javax.swing.JPanel();
        gamePrice = new javax.swing.JLabel();
        quantityLabel = new javax.swing.JLabel();
        genreLabel = new javax.swing.JLabel();
        gameTitle = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        systemPanel = new javax.swing.JPanel();
        systemLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(226, 323));
        setMinimumSize(new java.awt.Dimension(226, 289));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        gameInfo.setBackground(java.awt.Color.white);
        gameInfo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        gameInfo.setPreferredSize(new java.awt.Dimension(226, 158));

        gamePrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        gamePrice.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        gamePrice.setText("$0.00");

        quantityLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        quantityLabel.setText("0 Left");

        genreLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        genreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        genreLabel.setText("jLabel1");

        gameTitle.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        gameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameTitle.setText("jLabel1");

        javax.swing.GroupLayout gameInfoLayout = new javax.swing.GroupLayout(gameInfo);
        gameInfo.setLayout(gameInfoLayout);
        gameInfoLayout.setHorizontalGroup(
            gameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(genreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(gameInfoLayout.createSequentialGroup()
                        .addComponent(quantityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gamePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gameInfoLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(gameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        gameInfoLayout.setVerticalGroup(
            gameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gameTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gamePrice, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(quantityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ThumbnailPlaceholder.png"))); // NOI18N
        image.setPreferredSize(new java.awt.Dimension(220, 126));

        systemPanel.setBackground(java.awt.Color.black);

        systemLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        systemLabel.setForeground(new java.awt.Color(255, 255, 255));
        systemLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        systemLabel.setText("jLabel1");

        javax.swing.GroupLayout systemPanelLayout = new javax.swing.GroupLayout(systemPanel);
        systemPanel.setLayout(systemPanelLayout);
        systemPanelLayout.setHorizontalGroup(
            systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(systemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(systemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        systemPanelLayout.setVerticalGroup(
            systemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(systemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(systemLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(systemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gameInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(systemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        //Whenever it is clicked bring up a context menu
        
        //To do that we will use the index of the item
        //We will call upon a global method  that will help us diaply the correct item and item information
        Methods.displayItem(indexHeld);
        
        
    }//GEN-LAST:event_formMouseClicked

    /**
     * highlights the specific game display object. Useful for keyboard exclusive controls
     */
    public void highlight(){
    
        gameInfo.setBackground(Color.cyan);
    
    }
    
    /**
     * Reverts the background color back to white. Useful for keyboard exclusive controls
     */
    public void revert(){
    
        gameInfo.setBackground(Color.white);
    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel gameInfo;
    public javax.swing.JLabel gamePrice;
    public javax.swing.JLabel gameTitle;
    public javax.swing.JLabel genreLabel;
    public javax.swing.JLabel image;
    public javax.swing.JLabel quantityLabel;
    public javax.swing.JLabel systemLabel;
    public javax.swing.JPanel systemPanel;
    // End of variables declaration//GEN-END:variables
}
