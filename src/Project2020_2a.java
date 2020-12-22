import java.awt.*;
import java.awt.event.*;
import java.applet.AudioClip;
import javax.swing.JApplet;

public class Project2020_2a extends Frame {
    Graphics g;
    Frame myFrame;
    Image img;
    AudioClip ac[];
    Button bt1, bt2, ef1, ef2, ef3;
    // ページ識別用
    int page = 1;
    // 音声再生用 ファイル番号
    int sound_flag = 0;
    // 1ページ目 説明欄の状態(true: 表示中, false: 待機中)
    boolean desc_flag = false;
    // エフェクターの状態(true: ON, false: OFF)
    boolean ef1_state = false, ef2_state = false, ef3_state = false;
    //ボタン配置用パネル
    Panel togglePanel, pagePanel;
  
    public static void main(String[] args) {
        Project2020_2a pj = new Project2020_2a();
    }

    public Project2020_2a() {
        //④　ウインドウタイトルの設定
        super("Project2020 ProKisoB");

        myFrame = this;
        g = myFrame.getGraphics();
        
        //画像読み込み
        Toolkit tk = getToolkit();
        img = tk.getImage("images/guitar.jpg");

        //音声読み込み
        int numSounds = 8;
        ac = new AudioClip[numSounds];
        for (int i = 0; i < numSounds; i++) {
            ac[i] = JApplet.newAudioClip(getClass().getClassLoader().getResource("sounds/sound"+i+".wav"));
        }
        
        this.setLayout(new BorderLayout());
        
        //ページ切り替え用共通ボタン
        bt1 = new Button("ギターの説明");
        bt2 = new Button("エフェクトの試聴");
        pagePanel = new Panel();
        this.add(pagePanel, BorderLayout.NORTH);
        pagePanel.add(bt1);
        pagePanel.add(bt2);
        pagePanel.setBackground(new Color(235, 235, 235));
        
        //エフェクト切り替え用ボタン
        ef1 = new Button("切り替え");
        ef2 = new Button("切り替え");
        ef3 = new Button("切り替え");
        togglePanel = new Panel();
        togglePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 0));
        this.add(togglePanel, BorderLayout.SOUTH);
        togglePanel.add(ef1);
        togglePanel.add(ef2);
        togglePanel.add(ef3);

        // effect toggle
        ef1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ef1_state = !ef1_state;
                repaint();
            }
        });
        
        ef2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ef2_state = !ef2_state;
                repaint();
            }
        });
        
        ef3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ef3_state = !ef3_state;
                repaint();
            }
        });
        
        // page change
        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                page = 1;
                desc_flag = false;
                repaint();
            }
        });
        
        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                page = 2;
                repaint();
            }
        });

        this.addMouseListener(
                new MouseAdapter() {
            // ①マウスがクリックされたときの処理
            public void mouseClicked(MouseEvent e) {
                g = myFrame.getGraphics();
                if(page == 1) {
                    //すでに説明が表示されていたら消す
                    if(desc_flag) {
                        desc_flag = false;
                        repaint();
                        return;
                    }
                    
                    // head
                    if((e.getX() >= 71 && e.getX() <= 131) && (e.getY() >= 81 && e.getY() <= 173)) {
                        g.setColor(Color.WHITE);
                        g.fillRect(201, 401, 369, 149);
                        g.setColor(Color.BLACK);
                        g.drawLine(114, 160, 200, 422);
                        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
                        g.drawString("ヘッド", 210, 430);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                        g.drawString("(任意の場所をクリックで戻る)", 360, 430);
                        g.drawString("弦を巻き取るパーツ(ペグ)がついています。", 210, 470);
                        g.drawString("一般的にヘッド部分にはメーカーのロゴが書かれています。", 210, 490);
                        g.drawString("左の画像のように6つのペグが片側に連続しているタイプや、", 210, 510);
                        g.drawString("両側に3つずつ付いているタイプなど様々な種類があります。", 210, 530);
                        desc_flag = true;
                    }
                    
                    // neck
                    if((e.getX() >= 85 && e.getX() <= 114) && (e.getY() >= 177 && e.getY() <= 396)) {
                        g.setColor(Color.WHITE);
                        g.fillRect(201, 401, 369, 149);
                        g.setColor(Color.BLACK);
                        g.drawLine(111, 280, 200, 422);
                        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
                        g.drawString("ネック", 210, 430);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                        g.drawString("(任意の場所をクリックで戻る)", 360, 430);
                        g.drawString("ピアノでいう鍵盤がこの部分にあたります。ネックには", 210, 470);
                        g.drawString("フレットと呼ばれる金属が打ってあり、フレットの間を", 210, 490);
                        g.drawString("抑えることで音程を変え、メロディーやコードを奏でます。", 210, 510);
                        desc_flag = true;
                    }
                    
                    // body
                    if((e.getX() >= 25 && e.getX() <= 76) && (e.getY() >= 326 && e.getY() <= 560)
                            || (e.getX() >= 20 && e.getX() <= 177) && (e.getY() >= 502 && e.getY() <= 560)
                            || (e.getX() >= 120 && e.getX() <= 177) && (e.getY() >= 360 && e.getY() <= 506)) {
                        g.setColor(Color.WHITE);
                        g.fillRect(201, 401, 369, 149);
                        g.setColor(Color.BLACK);
                        g.drawLine(152, 421, 200, 422);
                        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
                        g.drawString("ボディー", 210, 430);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                        g.drawString("(任意の場所をクリックで戻る)", 360, 430);
                        g.drawString("ギターの大部分を占めるパーツです。", 210, 470);
                        g.drawString("弦を弾くとボディーが振動し、音が増幅されます。", 210, 490);
                        g.drawString("木の材質によって音質が変わります。", 210, 510);
                        desc_flag = true;
                    }
                    
                    // pickup
                    if((e.getX() >= 77 && e.getX() <= 119) && (e.getY() >= 400 && e.getY() <= 476)) {
                        g.setColor(Color.WHITE);
                        g.fillRect(201, 401, 369, 149);
                        g.setColor(Color.BLACK);
                        g.drawLine(117, 408, 200, 422);
                        g.drawLine(117, 439, 200, 422);
                        g.drawLine(117, 466, 200, 422);
                        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
                        g.drawString("ピックアップ", 210, 430);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                        g.drawString("(任意の場所をクリックで戻る)", 360, 430);
                        g.drawString("弦の振動を電気信号に変えるパーツです。マイクの役割を", 210, 470);
                        g.drawString("担います。左の画像では「シングルコイル」タイプの", 210, 490);
                        g.drawString("ピックアップが3つ付いています。シングルコイル2つを", 210, 510);
                        g.drawString("1セットとした「ハムバッカー」タイプのものもあります。", 210, 530);
                        desc_flag = true;
                    }
                    
                    // bridge
                    if((e.getX() >= 77 && e.getX() <= 119) && (e.getY() >= 477 && e.getY() <= 501)) {
                        g.setColor(Color.WHITE);
                        g.fillRect(201, 401, 369, 149);
                        g.setColor(Color.BLACK);
                        g.drawLine(116, 492, 200, 422);
                        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
                        g.drawString("ブリッジ", 210, 430);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                        g.drawString("(任意の場所をクリックで戻る)", 360, 430);
                        g.drawString("弦を通し、弦の振動をボディに伝えるパーツです。ギターの", 210, 470);
                        g.drawString("裏に穴が空いており、そこから弦を通してペグに巻きます。", 210, 490);
                        g.drawString("音程の微妙なズレを修正するときに調整する役割もあります。", 210, 510);
                        desc_flag = true;
                    }
                }
                
                if(page == 2) {
                    //再生ボタン
                    if((e.getX() >= 250 && e.getX() <= 350) && (e.getY() >= 150 && e.getY() <= 250)) {
                        ac[sound_flag].play();
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                        g.setColor(new Color(10,200,10));
                        g.drawString("Now Playing...", 400, 200);
                        try {
                            Thread.sleep(3300);
                        } catch (InterruptedException ex) {
                            // do nothing
                        }
                        repaint();
                    }
                }
            }
        });

        addWindowListener(new SampleWindowListner());
        setSize(600, 600);
        setVisible(true);
    }

    public void paint(Graphics g) {
        this.g = g;
        
        if(page == 1) {
            ef1.setVisible(false);
            ef2.setVisible(false);
            ef3.setVisible(false);
            g.drawImage(img, 20, 80, this);
            
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.BOLD, 22));
            g.drawString("ギターの説明ページ", 280, 100);
            
            g.setFont(new Font("TimesRoman", Font.PLAIN, 13)); 
            g.drawString("ギターは、バンドにおいて中～高音を担当する弦楽器です。", 200, 140);
            g.drawString("一般的なギターは弦が6本あります。", 200, 160);
            g.drawString("バンドアンサンブルには、もう一つの弦楽器であるベースが", 200, 180);
            g.drawString("欠かせませんが、こちらは弦が4本で、主に低音を担当します。", 200, 200);
            
            g.drawString("ギターはジャズからロック、メタルまで様々なジャンルの曲で", 200, 240);
            g.drawString("活躍します。幅広いジャンルで使える理由として、エフェクター", 200, 260);
            g.drawString("の存在があります。ギターの音にエフェクト(特殊効果)をかける", 200, 280);
            g.drawString("ことで、やさしい音や勢いのある音を表現することができます。", 200, 300);
            
            g.setColor(Color.DARK_GRAY);
            g.drawRect(200, 320, 370, 70);
            g.drawString("上の「エフェクトの試聴」ボタンをクリックすることで、", 210, 340);
            g.drawString("エフェクターを通すとどのような音になるのかを実際に体験", 210, 360);
            g.drawString("することができます。", 210, 380);
            
            g.drawRect(200, 400, 370, 150);
            g.drawString("左の画像をクリックすると、クリックした部分の", 210, 460);
            g.drawString("説明がここに表示されます", 400, 480);
        }
        
        if(page == 2) {
            ef1.setVisible(true);
            ef2.setVisible(true);
            ef3.setVisible(true);
            
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.BOLD, 22));
            g.drawString("エフェクトの試聴ページ", 170, 110);
            
            // play button
            g.drawRect(250, 150, 100, 100);
            g.setColor(new Color(30, 200, 30));
            int[] xPoints = {275, 275, 330};
            int[] yPoints = {165, 235, 200};
            g.fillPolygon(xPoints, yPoints, 3);
            
            // effector
            g.setColor(Color.GRAY);
            g.fill3DRect(0, 430, 600, 10, true);
            g.setColor(new Color(230, 121, 40));
            g.fillRect(70, 340, 100, 180);
            g.setColor(new Color(168, 90, 168));
            g.fillRect(250, 340, 100, 180);
            g.setColor(new Color(10, 153, 255));
            g.fillRect(430, 340, 100, 180);
            g.setColor(Color.BLACK);
            g.drawRect(70, 340, 100, 180);
            g.drawRect(250, 340, 100, 180);
            g.drawRect(430, 340, 100, 180);
            
            g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            g.setColor(ef1_state ? Color.GREEN : Color.RED);
            g.drawString(ef1_state ? "ON" : "OFF", 105, 550);
            g.setColor(ef2_state ? Color.GREEN : Color.RED);
            g.drawString(ef2_state ? "ON" : "OFF", 280, 550);
            g.setColor(ef3_state ? Color.GREEN : Color.RED);
            g.drawString(ef3_state ? "ON" : "OFF", 465, 550);
            
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("Distortion", 78, 390);
            g.drawString("Phaser", 270, 390);
            g.drawString("Delay", 455, 390);
            g.drawString("歪み系", 90, 490);
            g.drawString("空間系", 270, 490);
            g.drawString("空間系", 452, 490);
            
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
            g.drawString("右の三角ボタンをクリックで", 20, 200);
            g.drawString("再生できます", 140, 220);
            
            // effect description
            g.drawString("音が潰れて", 86, 300);
            g.drawString("かっこよくなります", 60, 320);
            g.drawString("モワンモワンと", 250, 300);
            g.drawString("音が左右にふられます", 231, 320);
            g.drawString("反射音で", 452, 300);
            g.drawString("音に奥行きが出ます", 417, 320);

            // 音再生用　ファイル番号の計算
            sound_flag = (ef1_state ? 4 : 0) + (ef2_state ? 2 : 0) + (ef3_state ? 1 : 0);
        }
    }

    //　ウィンドウを閉じる動作で行う処理（変更不要）
    class SampleWindowListner extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
