package javaricci.com.br;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class QRCodeReaderApp extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextArea textArea;
    private JButton selectFileButton;
    private JButton saveToFileButton;
    private JLabel imageLabel;

    public QRCodeReaderApp() {
        setTitle("Leitura de QR Code");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        selectFileButton = new JButton("Selecionar Arquivos de Imagens QR Code");
        saveToFileButton = new JButton("Salvar Dados Texto");
        imageLabel = new JLabel();

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        String qrContent = readQRCode(selectedFile);
                        textArea.setText(qrContent);
                        displayQRCode(selectedFile);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao ler QR Code: " + ex.getMessage());
                    }
                }
            }
        });

        saveToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedDirectory = fileChooser.getSelectedFile();
                    try (FileWriter writer = new FileWriter(new File(selectedDirectory, "QRCodeContent.txt"))) {
                        writer.write(textArea.getText());
                        JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao salvar arquivo: " + ex.getMessage());
                    }
                }
            }
        });

        setLayout(new BorderLayout());
        add(selectFileButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(saveToFileButton, BorderLayout.SOUTH);
        add(imageLabel, BorderLayout.EAST);
    }

    private String readQRCode(File qrCodeImage) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(qrCodeImage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText();
    }

    private void displayQRCode(File qrCodeImage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeImage);
        ImageIcon icon = new ImageIcon(bufferedImage);
        imageLabel.setIcon(icon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QRCodeReaderApp().setVisible(true);
            }
        });
    }
}
