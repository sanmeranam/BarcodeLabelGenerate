/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.san;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;

/**
 *
 * @author M1028704
 */
public class BarcodeImage extends javax.swing.JPanel {

    private BufferedImage barcodeImage;
    private GenerateConfig config;

    /**
     * Creates new form Barcode
     */
    public BarcodeImage() {
        initComponents();
        this.setMaximumSize(new Dimension(270, 100));
        this.setMinimumSize(new Dimension(270, 100));
        this.setPreferredSize(new Dimension(270, 100));
        barcodeImage = new BufferedImage(270, 100, BufferedImage.TYPE_INT_RGB);
    }

    public void updateBarcode(GenerateConfig config) {
        this.config = config;
        try {
            Barcode barcode = getPreferedBarcode(config.type, config.barcodeText);
            if (!config.isAddText) {
                barcode.setDrawingText(false);
            }
            barcode.setBarHeight(config.barHeight);
            barcode.setFont(new Font("Arial Bold", Font.CENTER_BASELINE, config.fontSize));

            Rectangle bound = barcode.getBounds();
            barcodeImage = new BufferedImage(bound.width, bound.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) this.barcodeImage.getGraphics();
            barcode.draw(g, 0, 0);
            this.updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage scale(BufferedImage before) {
        float scale = (this.config != null) ? this.config.scale : 1.0f;
        int w = before.getWidth();
        int h = before.getHeight();
        BufferedImage after = new BufferedImage((int) (w * scale), (int) (h * scale), before.getType());
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        after = scaleOp.filter(before, after);

        return after;
    }

    public BufferedImage getRenderedImage() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        this.paint(g);
        return image;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.white);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());

        BufferedImage result = scale(barcodeImage);

        double imgLeft = this.getWidth() / 2 - result.getWidth() / 2;
        double imgTop = this.getHeight() / 2 - result.getHeight() / 2;
        if (barcodeImage != null) {
            g.drawImage(result, (int) imgLeft, (int) imgTop, this);
        }

        int borderLeft = 8;
        int borderTop = 8;
        int borderRight = getWidth() - 16;
        int borderBottom = getHeight() - 16;

        Graphics2D g2 = (Graphics2D) g;
        if (this.config != null) {
            g.setColor(Color.black);

            if (this.config.isAboveText) {
                Font f = new Font(config.aboveTextFont, Font.CENTER_BASELINE, config.aboveTextFontSize);
                Rectangle2D upperTextBound = f.getStringBounds(this.config.aboveText, g2.getFontRenderContext());
                g2.setFont(f);
                g2.drawString(this.config.aboveText, (float) (this.getWidth() / 2 - upperTextBound.getWidth() / 2), 20);
            }

            if (this.config.isBelowLeftText) {
                Font f = new Font(config.belowLeftTextFont, Font.LAYOUT_LEFT_TO_RIGHT, config.belowLeftTextFontSize);
                g2.setFont(f);
                Rectangle2D leftTextBound = f.getStringBounds(this.config.belowLeftText, g2.getFontRenderContext());
                g2.drawString(this.config.belowLeftText, 20, (getHeight() - (int) leftTextBound.getHeight()) - 10);
            }

            if (this.config.isBelowRightText) {
                Font f = new Font(config.belowRightTextFont, Font.LAYOUT_RIGHT_TO_LEFT, config.belowRightTextFontSize);
                g2.setFont(f);
                Rectangle2D rightTextBound = f.getStringBounds(this.config.belowRightText, g2.getFontRenderContext());
                g2.drawString(this.config.belowRightText, getWidth() - (int) rightTextBound.getWidth() - 10, (getHeight() - (int) rightTextBound.getHeight()) - 10);
            }

            if (this.config.isBorder) {
                g2.setStroke(new BasicStroke(this.config.borderWidth));
                g2.drawRect(borderLeft, borderTop, borderRight, borderBottom);
            }
        }
    }

    private Barcode getPreferedBarcode(BarcodeType.TYPES type, String text) throws BarcodeException {
        Barcode barcode;
        switch (type) {
            case Code128:
                barcode = BarcodeFactory.createCode128(text);
                break;
            case Code128A:
                barcode = BarcodeFactory.createCode128A(text);
                break;
            case Code128B:
                barcode = BarcodeFactory.createCode128B(text);
                break;
            case Code128C:
                barcode = BarcodeFactory.createCode128C(text);
                break;
            case UCC128:
                barcode = BarcodeFactory.createUCC128(text, "");
                break;
            case EAN128:
                barcode = BarcodeFactory.createEAN128(text);
                break;
            case USPS:
                barcode = BarcodeFactory.createUSPS(text);
                break;
            case ShipmentIdentificationNumber:
                barcode = BarcodeFactory.createShipmentIdentificationNumber(text);
                break;
            case SSCC18:
                barcode = BarcodeFactory.createSSCC18(text);
                break;
            case SCC14ShippingCode:
                barcode = BarcodeFactory.createSCC14ShippingCode(text);
                break;
            case GlobalTradeItemNumber:
                barcode = BarcodeFactory.createGlobalTradeItemNumber(text);
                break;
            case EAN13:
                barcode = BarcodeFactory.createEAN13(text);
                break;
            case Bookland:
                barcode = BarcodeFactory.createBookland(text);
                break;
            case UPCA:
                barcode = BarcodeFactory.createUPCA(text);
                break;
            case RandomWeightUPCA:
                barcode = BarcodeFactory.createRandomWeightUPCA(text);
                break;
            case Std2of5:
                barcode = BarcodeFactory.createStd2of5(text);
                break;
            case Int2of5:
                barcode = BarcodeFactory.createInt2of5(text);
                break;
            case PDF417:
                barcode = BarcodeFactory.createPDF417(text);
                break;
            case Code39:
                barcode = BarcodeFactory.createCode39(text, false);
                break;
            case USD3:
                barcode = BarcodeFactory.createUSD3(text, false);
                break;
            case Codabar:
                barcode = BarcodeFactory.createCodabar(text);
                break;
            case USD4:
                barcode = BarcodeFactory.createUSD4(text);
                break;
            case NW7:
                barcode = BarcodeFactory.createNW7(text);
                break;
            case Monarch:
                barcode = BarcodeFactory.createMonarch(text);
                break;
            case PostNet:
                barcode = BarcodeFactory.createPostNet(text);
                break;

            default:
                barcode = BarcodeFactory.createCode128(text);
        }
        return barcode;
    }

//    public void drawBarcode(String data) {
//        this.text = data;
//        try {
//            Barcode barcode = BarcodeFactory.createCode128(data);
//            BufferedImage image = new BufferedImage(300, 200, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g = (Graphics2D) image.getGraphics();
//            barcode.draw(g, 50, 50);
//            this.add(barcode);
//            this.repaint();
//        } catch (Exception e) {
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Dimension dimension = this.getSize();
        double compWidth = dimension.width;
        double compHeight = dimension.height;
        System.out.println("Comp width: " + compWidth);
        System.out.println("Comp height: " + compHeight);

        Paper card = pageFormat.getPaper();

        card.setImageableArea(0, 0, 153, 243);
        card.setSize(153, 243);

        pageFormat.setPaper(card);
        pageFormat.setOrientation(PageFormat.PORTRAIT);

        if (pageIndex > 0) {
            return (NO_SUCH_PAGE);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            System.out.println("Coords: " + pageFormat.getImageableX() + ", " + pageFormat.getImageableY());
            g2d.translate(10f, 10f);
            this.paint(g2d);
            return (PAGE_EXISTS);
        }

    }
}
