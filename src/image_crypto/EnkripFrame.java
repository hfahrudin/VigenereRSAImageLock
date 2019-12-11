package image_crypto;


import java.awt.Canvas; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.Graphics;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.awt.image.BufferedImage; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.FileWriter;
import java.io.IOException; 
import java.math.BigInteger; 
import java.util.ArrayList;
import java.util.Random; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.imageio.ImageIO; 
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton; 
import javax.swing.WindowConstants; 
import javax.swing.border.BevelBorder; 
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class EnkripFrame extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String[] jum = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    JButton save = new JButton("Save"); 
    JButton open = new JButton("Open");
    JButton vigkeybutton = new JButton("Insert Key");
    JFileChooser chooser = new JFileChooser("."); 
    File f;
    DefaultListModel<String> l1 = new DefaultListModel<>();  
    public BufferedImage img,Aimg,Cimg,Rimg;
    FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
    int[][] matrixKey = new int[4096][4096]; 
    int jmlPrime=0,batasPrime=100;
    int[] prime = new int[batasPrime];
    Integer p,q,e,d,n,Qn;
    ArrayList vigenerekey = new ArrayList();
    Dimension dim = new Dimension(960,640);
    private MyCanvas canvasInput;
    JPanel panelIMG = new JPanel(); 
    JPanel panelKey = new JPanel();
    JButton random = new JButton("Random Key"); 
    JButton enk = new JButton("Encrypt!");
    JButton gets = new JButton("Confirm");
    JLabel vigenere = new JLabel("Vigenere and RSA key");
    Font font12 = new Font("Tahoma", 0, 12); 
    Font font14 = new Font("Tahoma", 0, 16); 
    Font font14b = new Font("Tahoma", 1, 16);
    JLabel sumKey = new JLabel("Vigenere Key");
    JLabel pKey = new JLabel("p = ");
    JLabel qKey = new JLabel("q = "); 
    JLabel eKey = new JLabel("e = "); 
    JLabel dKey = new JLabel("d = ");
    JLabel nKey =  new JLabel("n	= "); 
    JLabel QnKey = new JLabel("φ(n) = ");
    JToggleButton proses = new JToggleButton("OFF"); 
    Boolean isDetail =false;
    JLabel LDetail = new JLabel("Show Process"); 
    Long VaTime, RaTime, VbTime, RbTime;
    JComboBox jumlahText = new JComboBox(jum);
    JTextField pText = new JTextField(); 
    JTextField qText = new JTextField(); 
    JTextField eText = new JTextField(); 
    JTextField dText = new JTextField();
    JList vig = new JList(l1);
    Boolean gambar = true;
    int[][] inrgb;
    int[][] vrgb;
    int[][] rsargb;

    
    public EnkripFrame(Image_crypto m){ initUI();
        generatePrime();
        addWindowListener(new WindowAdapter() { @Override
            public void windowClosed(WindowEvent e) { m.setVisible(true);
            }
        });
    }

    private void initUI(){

        setTitle("Encrypt"); 
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
        enk.setBounds(600,5,100,30); 
        
        open.setFont(font12); 
        save.setFont(font12); 
        enk.setFont(font12);
        proses.setFont(font12);
        add(save);
        add(open);
        add(enk);
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
        panelKey.add(random);
        panelKey.add(vigenere);
        panelKey.add(sumKey); 
        panelKey.add(pKey); 
        panelKey.add(qKey);
        panelKey.add(eKey);
        panelKey.add(dKey); 
        panelKey.add(jumlahText);
        panelKey.add(pText);
        panelKey.add(qText);
        panelKey.add(eText); 
        panelKey.add(dText);
        panelKey.add(vigkeybutton);
        panelKey.add(gets);
        panelKey.add(vig);
        
        vig.setBounds(10, 90, 150, 175);
        vig.setFont(new java.awt.Font("Tahoma", 0, 12));
        vigenere.setBounds(10, 10, 300, 30);
        vigenere.setFont(new java.awt.Font("Tahoma", 0, 16));
        sumKey.setBounds(5, 60, 85, 20);
        sumKey.setFont(new java.awt.Font("Tahoma", 0, 12));
        jumlahText.setBounds(92, 60, 42, 20);
        jumlahText.setFont(new java.awt.Font("Tahoma", 0, 12));
        gets.setBounds(150, 290, 90, 30);
        gets.setFont(new java.awt.Font("Tahoma", 0, 12));
        vigkeybutton.setBounds(140, 60, 110, 20);
        vigkeybutton.setFont(new java.awt.Font("Tahoma", 0, 10));

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
        
        random.setBounds(10, 600, 120, 30);
        random.setFont(new java.awt.Font("Tahoma", 0, 12));
        random.addActionListener((java.awt.event.ActionEvent evt) -> {randomKey(evt);});
        enk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) { 
                VaTime = System.currentTimeMillis();
                gambar = false;
                eViginere();
                VbTime = System.currentTimeMillis(); 
                RaTime = System.currentTimeMillis(); 
                eRSA();
                RbTime = System.currentTimeMillis(); 
                save.setEnabled(true); 
                canvasInput.repaint(); 
                enk.setEnabled(false);
            }
        });


        
        vigkeybutton.addActionListener((java.awt.event.ActionEvent evt) -> { 
            int value = Integer.parseInt(jumlahText.getSelectedItem().toString());
            vigenerekey.clear();
            l1.removeAllElements();
            String inputan;
            int tempo = 0;
            boolean check = false;
            
            for(int i=0; i<value;i++){
                while(check == false){
                    inputan = JOptionPane.showInputDialog("Masukan Kunci "+String.valueOf(i+1));
                    check = true;
                    try {
                        tempo = Integer.parseInt(inputan);
                        if(tempo>255 || tempo<0){
                            throw new NumberFormatException();
                        }
                        
                    } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null," Nilai kunci tidak boleh kosong, nilai minus, dan berupa angka kurang dari 255  ","Error", JOptionPane.INFORMATION_MESSAGE);
                        check = false;
                    }
                }
                vigenerekey.add(tempo);
                l1.addElement("kunci " + String.valueOf(i+1) + " =" +String.valueOf(tempo));
                check = false;
            }
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
        
        gets.addActionListener((java.awt.event.ActionEvent evt) -> { 
            getvalue();
            
        });
        
        save.setEnabled(false); 
        save.addActionListener((java.awt.event.ActionEvent evt) -> {
            chooser.setFileFilter(imageFilter);
            int simpan = chooser.showSaveDialog(EnkripFrame.this); 
            if (simpan == JFileChooser.APPROVE_OPTION) {
                f = chooser.getSelectedFile(); 
                try {
                ImageIO.write(img, "png", f);
                } catch (IOException ex) { 
                    Logger.getLogger(Image_crypto.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    setMatrixKey(chooser.getName(f).toString()+".txt",img);
                } catch (IOException ex) { 
                    Logger.getLogger(Image_crypto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            save.setEnabled(false);
        });
        open.addActionListener((java.awt.event.ActionEvent evt) -> { chooser.setFileFilter(imageFilter);
            
            int buka_dialog = chooser.showOpenDialog(EnkripFrame.this); 
            if (buka_dialog == JFileChooser.APPROVE_OPTION) {
                f = chooser.getSelectedFile(); 
                try {
                    img = ImageIO.read(f);
                    
                    Aimg = ImageIO.read(f); 
                    
                    gambar = true;
                    
                    canvasInput.repaint();
                    if(e != null){
                        enk.setEnabled(true);
                    }
                } catch (IOException exc) { 
                    System.out.println(exc);
                }
            }
        });
        add(panelIMG);
        add(panelKey);
    }

    public void generatePrime(){ 
        for(int i=2; i<batasPrime; i++){
            boolean isPrime = true; for (int j=2; j<i; j++){
                if(i%j==0){ 
                    isPrime = false; break;
                }
            }

            if(isPrime){ 
                prime[jmlPrime]=i; 
                jmlPrime++;
            }
        }
    }

    public void getvalue(){
        try{
            p = Integer.parseInt(pText.getText());
            q = Integer.parseInt(qText.getText());
            n = p*q;
            e = Integer.parseInt(eText.getText());
            d = Integer.parseInt(dText.getText());
            enk.setEnabled(true);
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null," Key should not be empty and ranged from 0 - 255 ","Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void randomKey(ActionEvent k){ 
        panelKey.add(nKey);
        panelKey.add(QnKey); 
        vigenerekey.clear();
        l1.removeAllElements();
        Random a = new Random();
        int temp;
        int sum = Integer.parseInt(jumlahText.getSelectedItem().toString());
        for(int i = 0; i < sum; i++){
            temp = a.nextInt(256);
            vigenerekey.add(temp);
            l1.addElement("key " + String.valueOf(i+1) + " =" + String.valueOf(temp));
        }
        p = prime[a.nextInt(jmlPrime)]; 
        q = prime[a.nextInt(jmlPrime)]; 
        n = p*q;

        if(n<=256){
            randomKey(k);
        }

        Qn = (p-1)*(q-1);
        e = a.nextInt(Qn); 
        int[] tmp = gcd(Qn,e);


        while ( tmp[0]!= 1){
            e = a.nextInt(Qn); 
            tmp = gcd(Qn,e);
        }


        if(tmp[2]>0){
            d=tmp[2];
        }else{
            d=Qn+tmp[2];
        }

        nKey.setText("n	= "+n);
        QnKey.setText("φ(n) = "+Qn);
        pText.setText(String.valueOf(p.intValue()));
        pText.setEditable(false);
        qText.setText(String.valueOf(q.intValue()));
        qText.setEditable(false); 
        eText.setText(String.valueOf(e.intValue()));
        eText.setEditable(false); 
        dText.setText(String.valueOf(d.intValue()));
        dText.setEditable(false); if(img != null){
        enk.setEnabled(true);
        }
    }

    public int[] gcd(int p, int q) { 
        if (q == 0)
        return new int[] { p, 1, 0 };

        int[] vals = gcd(q, p % q);
        int d = vals[0];
        int a = vals[2];
        int b = vals[1] - (p / q) * vals[2]; 
        return new int[] { d, a, b };
    }

    public void getKey(){
        e = Integer.parseInt(eText.getText()); 
        d = Integer.parseInt(dText.getText());
    }

    public void setMatrixKey(String fileName, BufferedImage b) throws FileNotFoundException, IOException {
        try(BufferedWriter fw = new BufferedWriter(new FileWriter(chooser.getSelectedFile()+".txt"))) {
            int sum = vigenerekey.size();
            String vigi = "";
            for(int i = 0; i<sum;i++){
                vigi = vigi + vigenerekey.get(i).toString() + " ";
            }
            for ( int i = 0; i < b.getHeight(); i++){
                for ( int j = 0; j < b.getWidth()*3; j++){ 
                    fw.write(matrixKey[j][i]+" "); 
                    if(j==b.getWidth()*3-1 && i==b.getHeight()-1){
                        fw.newLine();
                        fw.write(sum+" "+vigi+p+" "+q+" "+e+" "+d);
                    }
                }
                fw.newLine();
                fw.flush();
            }
        }
    }

    public int[][] ambilRGB(BufferedImage img, int l, int t, int rgb[][]){
        Color argb = new Color(img.getRGB(l,t)); 
        rgb[l*3][t]=argb.getRed(); //red 
        rgb[l*3+1][t]=argb.getGreen(); //green 
        rgb[l*3+2][t]=argb.getBlue(); //blue 
        return rgb;
    }

    public void eViginere(){
        int w = img.getWidth(), h = img.getHeight(), sum = vigenerekey.size();
        Cimg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB); 
        int rgb[][] = new int[3*w][h];
        inrgb = new int[3*w][h];
        int counter= 0;
        int temp;
        for(int i=0;i<w;i++){ 
            for(int j=0;j<h;j++){
                temp = Integer.parseInt(vigenerekey.get(counter).toString());
                ambilRGB(img,i,j,rgb);
                int a=(rgb[i*3][j]+temp)%256, b=(rgb[i*3+1][j]+temp)%256, c=(rgb[i*3+2][j]+temp)%256; 
                Color tmp = new Color(a,b,c);
                img.setRGB(i,j, tmp.getRGB()); 
                Cimg.setRGB(i,j, tmp.getRGB());
                counter++;
                if (counter == sum ){
                    counter = 0;
                }
            }
        }
        for(int i=0; i<rgb.length; i++)
            System.arraycopy(rgb[i], 0, inrgb[i], 0, rgb[i].length);
    }

    public void eRSA(){
        int w = img.getWidth(), h = img.getHeight(); 
        int rgb[][] = new int[3*w][h];
        vrgb = new int[3*w][h];
        matrixKey = new int[3*w][h];
        Rimg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB); 
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){ 
                ambilRGB(img,i,j,rgb);
                int a=rgb[i*3][j],b=rgb[i*3+1][j],c=rgb[i*3+2][j]; 
                BigInteger a1 = BigInteger.valueOf(a);
                BigInteger b1 = BigInteger.valueOf(b); 
                BigInteger c1 = BigInteger.valueOf(c);
                a1=a1.modPow(BigInteger.valueOf(e), BigInteger.valueOf(n));
                b1=b1.modPow(BigInteger.valueOf(e), BigInteger.valueOf(n)); 
                c1=c1.modPow(BigInteger.valueOf(e), BigInteger.valueOf(n)); 
                a=a1.intValue();
                b=b1.intValue(); 
                c=c1.intValue(); 
                matrixKey[i*3][j]=a/256; 
                matrixKey[i*3+1][j]=b/256; 
                matrixKey[i*3+2][j]=c/256; 
                a=a%256;
                b=b%256;
                c=c%256;
                Color tmp = new Color(a,b,c); 
                img.setRGB(i,j, tmp.getRGB()); 
                Rimg.setRGB(i,j, tmp.getRGB());
            }
        }
        for(int i=0; i<rgb.length; i++)
            System.arraycopy(rgb[i], 0, vrgb[i], 0, rgb[i].length);
    }


    private class MyCanvas extends Canvas {

        private static final long serialVersionUID = 1L;

    @Override
        public void paint(Graphics g) { 
            if (img != null && !isDetail) {
                int imgWidth = img.getWidth(this); int imgHeight = img.getHeight(this);
                double imgAspect = (double) imgHeight / imgWidth; 
                int canvasWidth = canvasInput.getWidth();
                int canvasHeight = canvasInput.getHeight();
                double canvasAspect = (double) canvasHeight / canvasWidth; int x1 = 0;
                int y1 = 0; int x2 = 0; int y2 = 0;
                if (imgWidth < canvasWidth && imgHeight < canvasHeight) { 
                    x1 = (canvasWidth - imgWidth) / 2;
                    y1 = (canvasHeight - imgHeight) / 2; x2 = imgWidth + x1;
                    y2 = imgHeight + y1;
                } else {
                    if (canvasAspect > imgAspect) { y1 = canvasHeight;
                    canvasHeight = (int) (canvasWidth * imgAspect); y1 = (y1 - canvasHeight) / 2;
                    } else {
                    x1 = canvasWidth;
                    canvasWidth = (int) (canvasHeight / imgAspect); x1 = (x1 - canvasWidth) / 2;
                    }
                    x2 = canvasWidth + x1; y2 = canvasHeight + y1;
                }
                g.drawImage(img, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
            }
            if(isDetail && img != null){
                int[] xP = {310,350,350,390,350,350,310};
                int[] xP1 = {310+396,350+396,350+396,390+396,350+396,350+396,310+396}; int[] yP = {165,165,130,200,270,245,245};
                int imgWidth = img.getWidth(this); int imgHeight = img.getHeight(this); g.setFont(font14b);
                g.drawString("Encryption Time : Vigenere", 270, 450);
                g.drawString("Encryption Time : RSA  ", 270+396, 450);
                g.drawString("Total Elapsed", 460, 530);
                g.drawString("Dimension", 460, 600);
                g.setFont(font14);

                if(VbTime != null && RbTime != null){
                    double Ct = (double)(VbTime - VaTime)/1000; 
                    double Rt = (double)(RbTime - RaTime)/1000;
                    g.drawString(String.valueOf(Ct)+" s", 270, 480);
                    g.drawString(String.valueOf(Rt)+" s", 270+396, 480);
                    g.drawString(String.valueOf(Ct+Rt)+" s", 460, 560);
                }

                g.drawString(String.valueOf(imgWidth)+" x "+String.valueOf(imgHeight), 460, 630); 
                g.setColor(Color.black);
                g.fillPolygon(xP, yP, 7);
                g.fillPolygon(xP1, yP, 7);
                double imgAspect = (double) imgHeight / imgWidth; 
                int canvasWidth = 300;
                int canvasHeight = 400;
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
                        canvasWidth = (int) (canvasHeight / imgAspect); 
                        x1 = (x1 - canvasWidth) / 2;
                    }
                    x2 = canvasWidth + x1; 
                    y2 = canvasHeight + y1;
                }
                g.drawImage(Aimg, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null); 
                if(!gambar){
                    g.drawImage(Cimg, x1+396, y1, x2+396, y2, 0, 0, imgWidth, imgHeight, null); 
                    g.drawImage(Rimg, x1+396+396, y1, x2+396+396, y2, 0, 0, imgWidth, imgHeight, null);
                }

            }
        }
    }
}
