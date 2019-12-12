package image_crypto;

import java.awt.Canvas; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 
import java.awt.image.BufferedImage;
 
import java.io.BufferedReader; 
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.FileReader;
import java.io.IOException; 
import java.math.BigInteger; 
import java.util.ArrayList;
import java.util.StringTokenizer; 
import java.util.logging.Level; 
import java.util.logging.Logger;
import javax.imageio.ImageIO; 
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JList;
import javax.swing.JOptionPane; 
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants; 
import javax.swing.border.BevelBorder; 
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DekripFrame extends JFrame{
    String[] jum = {"1","2", "3", "4", "5", "6", "7", "8", "9", "10"};
    JButton save = new JButton("Save"); 
    JButton open = new JButton("Open"); 
    DefaultListModel<String> l1 = new DefaultListModel<>();  
    Boolean gambar = true;
    JFileChooser chooser = new JFileChooser("."); 
    int height;
    ArrayList vigenerekey = new ArrayList();
    File f;
    public BufferedImage img,Aimg,Cimg,Rimg;
    FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
    int[][] matrixKey = new int[4096][4096];;
    int sum;
    Integer p,q,e,d,n,Qn,x,y,z;
    Dimension dim = new Dimension(960,640);
    private MyCanvas canvasInput;
    JPanel panelIMG = new JPanel(); 
    JPanel panelKey = new JPanel();
    Boolean isTampil = false;
    JButton dek = new JButton("Decrypt!"); 
    JLabel vigenere = new JLabel("Vigenere and RSA key"); 

    Font font12 = new Font("Tahoma", 0, 12);
    Font font14 = new Font("Tahoma", 0, 16); 
    Font font14b = new Font("Tahoma", 1, 16);
    JLabel jumlahKey = new JLabel("Vigenere key");
    Long CaTime, RaTime, CbTime, RbTime;
    JLabel pKey = new JLabel("p = "); 
    JLabel qKey = new JLabel("q = "); 
    JLabel eKey = new JLabel("e = "); 
    JLabel dKey = new JLabel("d = ");
    JLabel nKey = new JLabel("n = "); 
    JLabel QnKey = new JLabel("φ(n) = ");
    JToggleButton proses = new JToggleButton("OFF"); 
    Boolean isDetail =false;
    JLabel LDetail = new JLabel("Show Process");
    JList vig = new JList(l1);

    JTextField pText = new JTextField();
    JTextField qText = new JTextField(); 
    JTextField eText = new JTextField(); 
    JTextField dText = new JTextField();
    
    int[][] inrgb;
    int[][] vrgb;
    int[][] rsargb;
   
    public DekripFrame(Image_crypto m){
        initUI();
        addWindowListener(new WindowAdapter() { @Override
            public void windowClosed(WindowEvent e) { 
                m.setVisible(true);
            }
        });
    }

    private void initUI(){ 
        
        setTitle("Enkripsi"); 
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
        setLayout(null);
        setSize(dim); 
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLocationRelativeTo(null); 
        LDetail.setBounds(250,5,150,30); 
        LDetail.setFont(font12);
        add(LDetail); 
        open.setBounds(5,5,100,30); 
        save.setBounds(115,5,100,30); 
        proses.setBounds(350,5,60,30); 
        dek.setBounds(600,5,100,30); 
        dek.setEnabled(false); 
        open.setFont(font12); 
        save.setFont(font12); 
        dek.setFont(font12);
        proses.setFont(font12);
        add(save);
        add(open);
        add(dek);
        add(proses); 
        panelKey.setLayout(null);
        panelIMG.setLayout(null);
        panelIMG.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null)); 
        panelIMG.setBounds(10, 40, 1100, 650);
        panelKey.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelKey.setBounds(1120, 40, 240, 650);
        canvasInput = new MyCanvas(); 
        canvasInput.setVisible(true); 
        canvasInput.setBounds(2,2,1096,646); 
        panelIMG.add(canvasInput);
        panelKey.add(vigenere);
        panelKey.add(pKey); 
        panelKey.add(qKey);
        panelKey.add(eKey);
        panelKey.add(dKey); 
        panelKey.add(pText);
        panelKey.add(qText);
        panelKey.add(eText); 
        panelKey.add(dText);
        panelKey.add(jumlahKey);
        panelKey.add(vig);
        vigenere.setBounds(10, 10, 300, 30);
        vigenere.setFont(new java.awt.Font("Tahoma", 0, 16));
        vigenere.setBounds(10, 10, 300, 30);
        vigenere.setFont(new java.awt.Font("Tahoma", 0, 16));
        jumlahKey.setBounds(10, 60, 85, 20);
        jumlahKey.setFont(new java.awt.Font("Tahoma", 0, 12));
        vig.setBounds(10, 90, 150, 175);
        vig.setFont(new java.awt.Font("Tahoma", 0, 12));

        pKey.setBounds(10, 290, 50, 30);
        pKey.setFont(new java.awt.Font("Tahoma", 0, 18));
        qKey.setBounds(10, 340, 50, 30);
        qKey.setFont(new java.awt.Font("Tahoma", 0, 18));
        nKey.setBounds(10, 390, 200, 30);
        nKey.setFont(new java.awt.Font("Tahoma", 0, 18));
        QnKey.setBounds(10, 440, 200, 30);
        QnKey.setFont(new java.awt.Font("Tahoma", 0, 18));
        
        pText.setBounds(50, 290, 50, 30);
        pText.setFont(new java.awt.Font("Tahoma", 0, 18));
        qText.setBounds(50, 340, 50, 30);
        qText.setFont(new java.awt.Font("Tahoma", 0, 18));
        eKey.setBounds(10, 490, 50, 30);
        eKey.setFont(new java.awt.Font("Tahoma", 0, 18));
        dKey.setBounds(10, 540, 50, 30);
        dKey.setFont(new java.awt.Font("Tahoma", 0, 18));
        eText.setBounds(50, 490, 100, 30);
        eText.setFont(new java.awt.Font("Tahoma", 0, 18));
        dText.setBounds(50, 540, 100, 30);
        dText.setFont(new java.awt.Font("Tahoma", 0, 18));
        
        
        dek.addActionListener((java.awt.event.ActionEvent evt) -> { 
            gambar = false;
            RaTime = System.currentTimeMillis();
            dRSA();
            RbTime = System.currentTimeMillis(); CaTime = System.currentTimeMillis(); 
            dVigenere();
            CbTime = System.currentTimeMillis(); save.setEnabled(true);
            dek.setEnabled(false);
        });
        
        proses.addActionListener((java.awt.event.ActionEvent evt) -> { 
            isDetail = !isDetail;
            if(isDetail){ 
                proses.setText("ON");
            }else{
                proses.setText("OFF");
            }
            canvasInput.repaint();
        });
        
        save.setEnabled(false); 
        
        save.addActionListener((java.awt.event.ActionEvent evt) -> {
            chooser.setFileFilter(imageFilter);
            int simpan = chooser.showSaveDialog(DekripFrame.this); if (simpan == JFileChooser.APPROVE_OPTION) {
                f = chooser.getSelectedFile(); 
                try {
                    ImageIO.write(img, "png", f);
                } catch (IOException ex) { 
                    Logger.getLogger(Image_crypto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            save.setEnabled(false);
        });
        
        open.addActionListener((java.awt.event.ActionEvent evt) -> {
            gambar = true;
            openImage();
        });
        
        add(panelIMG);add(panelKey);
    }

    public void openImage(){ 
        vigenerekey.clear();
        chooser.setFileFilter(imageFilter);
        int buka_dialog = chooser.showOpenDialog(DekripFrame.this); 
        if (buka_dialog == JFileChooser.APPROVE_OPTION) {
            f = chooser.getSelectedFile(); 
            try {
                img = ImageIO.read(f); 
                Aimg = ImageIO.read(f); 
                canvasInput.repaint(); 
                dek.setEnabled(true); 
            } catch (IOException exc) { 
                System.out.println(exc);
            }
        }
        try {
            if (img != null){
                matrixKey = new int[img.getWidth()*3][img.getHeight()+1];
                matrixKey = getMatrixKey(img);
            }
        } catch (IOException ex) { 
            Logger.getLogger(DekripFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (img != null){
            tampilKey();
        }
    }
 


    public void tampilKey(){

        for(int i=0; i<sum;i++){
            l1.addElement("kunci " + String.valueOf(i+1) + " =" +matrixKey[i+1][height]); 
        }
        pText.setText(String.valueOf(p));
        qText.setText(String.valueOf(q)); 
        eText.setText(String.valueOf(e)); 
        dText.setText(String.valueOf(d)); 
        panelKey.add(nKey);
        panelKey.add(QnKey); 
        nKey.setText("n	= "+(p*q)); 
        QnKey.setText("φ(n) = "+((p-1)*(q-1)));
    }

    public int[][] getMatrixKey(BufferedImage b) throws IOException{ 
        FileReader inputDokumen = null;
        try {
            ArrayList<String> listData = new ArrayList<String>(); 
            inputDokumen = new FileReader(chooser.getSelectedFile()+".txt");
            BufferedReader bufferBaca = new BufferedReader(inputDokumen); 
            String barisData;
            while ((barisData = bufferBaca.readLine()) != null) { 
                listData.add(barisData);
            }
            for (int i = 0; i < b.getHeight(); i++) { 
                ArrayList<String> item = new ArrayList<String>();
                StringTokenizer token = new StringTokenizer(listData.get(i), " "); 
                while (token.hasMoreTokens()) {
                    item.add(token.nextToken());
                }
                for (int j = 0; j < b.getWidth()*3; j++) { 
                    matrixKey[j][i] = Integer.parseInt(item.get(j));
                }
            } 
            ArrayList<String> item = new ArrayList<String>();
            StringTokenizer token = new StringTokenizer(listData.get(b.getHeight()), " "); 
            height = b.getHeight();
            while (token.hasMoreTokens()) {
                item.add(token.nextToken());
            } 
            matrixKey[0][b.getHeight()] = Integer.parseInt(item.get(0));
            sum = matrixKey[0][b.getHeight()]; 
            for (int j = 1; j <= sum+4; j++) {
                matrixKey[j][b.getHeight()] = Integer.parseInt(item.get(j));
            }
            
            for(int i=0; i<sum;i++){
                vigenerekey.add(matrixKey[i+1][b.getHeight()]);
            }
            p=matrixKey[sum+1][b.getHeight()]; 
            q=matrixKey[sum+2][b.getHeight()]; 
            e=matrixKey[sum+3][b.getHeight()]; 
            d=matrixKey[sum+4][b.getHeight()]; 
            n=p*q;

        } catch (FileNotFoundException ex) {
            int reply = JOptionPane.showConfirmDialog(null, "Cannot find key. Do you want to open another image?", "Error !!!", JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.YES_OPTION){ 
                openImage();
            }else{
                dispose();
            }
        }
        return matrixKey;
    }

    public int[][] ambilRGB(BufferedImage img, int l, int t, int rgb[][]){ 
        Color argb = new Color(img.getRGB(l,t));
        rgb[l*3][t]=argb.getRed(); //red
        rgb[l*3+1][t]=argb.getGreen(); //green 
        rgb[l*3+2][t]=argb.getBlue(); //blue 
        return rgb;
    }

    public void dVigenere(){
        int w = img.getWidth(), h = img.getHeight();
        Rimg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB); 
        int rgb[][] = new int[3*w][h];
        vrgb = new int[3*w][h];
        for(int i=0;i<w;i++){ 
            for(int j=0;j<h;j++){
                ambilRGB(img,i,j,rgb);
            }
        }
        int counter = 0;
        int temp;
        for(int i=0;i<w;i++){ 
            for(int j=0;j<h;j++){
                temp = Integer.parseInt(vigenerekey.get(counter).toString());
                int a=(rgb[i*3][j]-temp+256)%256,  b=(rgb[i*3+1][j]-temp+256)%256,    c=(rgb[i*3+2][j]- temp+256)%256;
                Color tmp = new Color(a,b,c); img.setRGB(i,j, tmp.getRGB()); Rimg.setRGB(i,j, tmp.getRGB());
                counter++;
                if (counter == sum ){
                    counter = 0;
                }
            }
        }
        for(int i=0; i<rgb.length; i++)
            System.arraycopy(rgb[i], 0, vrgb[i], 0, rgb[i].length);
        canvasInput.repaint();
    }

    public void dRSA(){
        int w = img.getWidth(), h = img.getHeight();
        rsargb = new int[3*w][h];
        Cimg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB); 
        int rgb[][] = new int[3*w][h];
        for(int i=0;i<w;i++){ 
            for(int j=0;j<h;j++){
                ambilRGB(img,i,j,rgb);
            }
        }
        for(int i=0;i<w;i++){ 
            for(int j=0;j<h;j++){
                int a=rgb[i*3][j]; 
                int b=rgb[i*3+1][j]; 
                int c=rgb[i*3+2][j];
                a=matrixKey[i*3][j]*256+a; 
                b=matrixKey[i*3+1][j]*256+b; 
                c=matrixKey[i*3+2][j]*256+c; 
                BigInteger a1 = BigInteger.valueOf(a); 
                BigInteger b1 = BigInteger.valueOf(b); 
                BigInteger c1 = BigInteger.valueOf(c);
                a1 = a1.modPow(BigInteger.valueOf(d),BigInteger.valueOf(n)); 
                b1 = b1.modPow(BigInteger.valueOf(d),BigInteger.valueOf(n)); 
                c1 = c1.modPow(BigInteger.valueOf(d),BigInteger.valueOf(n)); 
                a = a1.intValue();
                b = b1.intValue(); 
                c = c1.intValue();
                Color tmp = new Color(a,b,c); 
                img.setRGB(i,j, tmp.getRGB()); 
                Cimg.setRGB(i,j, tmp.getRGB());
            }
        }
        for(int i=0; i<rgb.length; i++)
            System.arraycopy(rgb[i], 0, rsargb[i], 0, rgb[i].length);
        canvasInput.repaint();
    }

    private class MyCanvas extends Canvas {
        
        private static final long serialVersionUID = 1L; 
        
        @Override
        public void paint(Graphics g) {
            if (img != null && !isDetail) {
                
                int imgWidth = img.getWidth(this); 
                int imgHeight = img.getHeight(this);
                double imgAspect = (double) imgHeight / imgWidth; 
                int canvasWidth = canvasInput.getWidth();
                int canvasHeight = canvasInput.getHeight();
                double canvasAspect = (double) canvasHeight / canvasWidth; 
                int x1 = 0;
                int y1 = 0; 
                int x2 = 0; 
                int y2 = 0;
                
                if (imgWidth < canvasWidth && imgHeight < canvasHeight) {
                    x1 = (canvasWidth - imgWidth) / 2;
                    y1 = (canvasHeight - imgHeight) / 2; x2 = imgWidth + x1;
                    y2 = imgHeight + y1;
                } else {
                    if (canvasAspect > imgAspect) { 
                        y1 = canvasHeight;
                        canvasHeight = (int) (canvasWidth * imgAspect); y1 = (y1 - canvasHeight) / 2;
                    } else {
                        x1 = canvasWidth;
                        canvasWidth = (int) (canvasHeight / imgAspect); x1 = (x1 - canvasWidth) / 2;
                    }
                    x2 = canvasWidth + x1; 
                    y2 = canvasHeight + y1;
                }
                g.drawImage(img, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
            }
            
            if(isDetail && img != null){
                int[] xP = {310,350,350,390,350,350,310};
                int[] xP1 = {310+396,350+396,350+396,390+396,350+396,350+396,310+396}; 
                int[] yP = {165,165,130,200,270,245,245};
                int imgWidth = img.getWidth(this); 
                int imgHeight = img.getHeight(this); 
                g.setFont(font14b);
                g.drawString("Decryption Time : Vigenere", 270, 450);
                g.drawString("Decryption Time : RSA  ", 270+396, 450);
                g.drawString("Elapsed time", 460, 530);
                g.drawString("Dimension", 460, 600); g.setFont(font14);
                if(CbTime != null && RbTime != null){
                    double Ct = (double)(CbTime - CaTime)/1000; double Rt = (double)(RbTime - RaTime)/1000;
                    g.drawString("waktu "+String.valueOf(Ct)+" s", 270, 480);
                    g.drawString("waktu "+String.valueOf(Rt)+" s", 270+396, 480);
                    g.drawString(String.valueOf(Ct+Rt)+" s", 460, 560);
                }
                g.drawString(String.valueOf(imgWidth)+" x "+String.valueOf(imgHeight), 460, 630);
                g.setColor(Color.black);
                g.fillPolygon(xP, yP, 7);
                g.fillPolygon(xP1, yP, 7);
                
                double imgAspect = (double) imgHeight / imgWidth; 
                int canvasWidth = 300;
                int canvasHeight = 400;
                double canvasAspect = (double) canvasHeight / canvasWidth; int x1 = 0;
                int y1 = 0; 
                int x2 = 0;
                int y2 = 0;
                if (imgWidth < canvasWidth && imgHeight < canvasHeight) { 
                    x1 = (canvasWidth - imgWidth) / 2;
                    y1 = (canvasHeight - imgHeight) / 2; x2 = imgWidth + x1;
                    y2 = imgHeight + y1;
                } else {
                if (canvasAspect > imgAspect) { 
                    y1 = canvasHeight;
                    canvasHeight = (int) (canvasWidth * imgAspect); y1 = (y1 - canvasHeight) / 2;
                } else {
                    x1 = canvasWidth;
                    canvasWidth = (int) (canvasHeight / imgAspect); 
                    x1 = (x1 - canvasWidth) / 2;
                }
                x2 = canvasWidth + x1; y2 = canvasHeight + y1;
                }
                g.drawImage(Aimg, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
                if (!gambar){
                    g.drawImage(Cimg, x1+396, y1, x2+396, y2, 0, 0, imgWidth, imgHeight, null);
                    g.drawImage(Rimg, x1+396+396, y1, x2+396+396, y2, 0, 0, imgWidth, imgHeight, null);                    
                }

            }
        }
    }
}
