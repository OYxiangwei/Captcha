package com.oy.java;

import sun.font.TrueTypeFont;
import sun.java2d.pipe.BufferedRenderPipe;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

public class Captcha {
    private int height = 100;
    private int width = 240;
    private int codeNum = 4;
    private int lineNum = 20;
    private String code = null;
    //设置验证码图片缓存
    private BufferedImage bufferedImage = null;
    private char[] codeQueue={'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};
    private Random random = new Random();
    public Captcha(){
        this.createCode();
    }
    public Captcha(int width,int height){
        this.height = height;
        this.width = width;
        this.createCode();
    }

    public  Captcha(int width,int height,int codeNum,int lineNum){
        this.height=height;
        this.width = width;
        this.codeNum = codeNum;
        this.lineNum = lineNum;
        this.createCode();
    }
    public void createCode(){
        int codex = width/(codeNum+3);
        int codey = height -20;
        bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0,0,width,height);
        ImgFontByte imgFont = new ImgFontByte();
        Font font = imgFont.getFont(codey);
        graphics.setFont(font);
        for (int i=0;i<lineNum;i++){
            int xP = getRandomNumber(width / 2);
            int yP = getRandomNumber(height/2);
            int xD = xP + getRandomNumber(width * 10);
            int yD = yP + getRandomNumber(height * 10);
            graphics.setColor(getRandomColor());
            graphics.drawLine(xP,yP,xD,yD);
        }
        StringBuffer randomCode = new StringBuffer();
        for (int i=0;i<codeNum;i++){
            String strRand = String.valueOf(codeQueue[random.nextInt(codeQueue.length)]);
            graphics.setColor(getRandomColor());
            graphics.drawString(strRand,(i+1)*codex,getRandomNumber(height/2)+50);
            randomCode.append(strRand);
        }
        code =randomCode.toString();
    }

   private  Color getRandomColor(){
        int r = getRandomNumber(255);
        int g = getRandomNumber(255);
        int b = getRandomNumber(255);
        return new Color(r,g,b);
    }
   private int getRandomNumber(int a){
        return random.nextInt(a);
    }
    public void write(OutputStream sos)throws IOException {
        ImageIO.write(bufferedImage,"png",sos);
        sos.close();
    }
    public String getCode(){
        return code;
    }

    class ImgFontByte{
        public Font getFont(int fontHeight){
            try {
                Font baseFont = Font.createFont(Font.TRUETYPE_FONT,new ByteArrayInputStream(hex2byte(getFontByteStr())));
                return baseFont.deriveFont(Font.PLAIN,fontHeight);
            }catch (Exception e){
                e.printStackTrace();
                return new Font("Arial",Font.PLAIN,fontHeight);
            }
        }
        private byte[] hex2byte(String str){
            if (str==null){
                return null;
            }
            str = str.trim();
            int len = str.length();
            if(len==0||len%2==1){
                return null;
            }
            byte[] b = new byte[len/2];
            try {
                for(int i = 0;i<str.length();i+=2){
                    b[i/2]=(byte) Integer.decode("0x"+str.substring(i,i+2)).intValue();
                }
                return b;
            }catch (Exception e){
                return null;
            }
        }
    private String getFontByteStr(){
            String pathname = "D:\\IDEA\\Captcha\\HexString.txt";
            File file = new File(pathname);
            String tem = null;
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(file));
                tem = reader.readLine();
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return tem;
    }
    }

}
