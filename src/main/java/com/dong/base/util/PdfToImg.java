package com.dong.base.util;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.lowagie.text.pdf.PdfReader;


public class PdfToImg {

    public static void main(String[] args) {
//        pdf2Image("D:/2.pdf", "D:/img", 300);
        aa(new File("D:/pdf"));
        System.out.println(".....over");

    }

    public static void aa(File path){
        File[] files = path.listFiles();
        if(files!=null) {
            for(File file:files) {
                if(file.isDirectory()) {
                   aa(file);
                }else {
                    //遇到文件，检查
                    if(file.getAbsolutePath().endsWith("pdf")||file.getAbsolutePath().endsWith("PDF")) {
                        System.out.println("文件："+file.getAbsolutePath());
                        pdf2Image(file.getAbsolutePath(),"D:/img",300);
                    }else{
                        System.out.println("没有处理的文件："+file.getAbsolutePath());
                    }
                }
            }
        }
    }


    /***
     * PDF文件转PNG图片，全部页数
     *
     * @param PdfFilePath pdf完整路径
     * @param imgFilePath 图片存放的文件夹
     * @param dpi dpi越大转换后越清晰，相对转换速度越慢
     * @return
     */
    public static void pdf2Image(String PdfFilePath, String dstImgFolder, int dpi) {
        File file = new File(PdfFilePath);
        PDDocument pdDocument;
        try {
            String imgPDFPath = file.getParent();
            int dot = file.getName().lastIndexOf('.');
            String imagePDFName = file.getName().substring(0, dot); // 获取图片文件名
            String imgFolderPath = null;
            if (dstImgFolder.equals("")) {
                imgFolderPath = imgPDFPath + File.separator + imagePDFName;// 获取图片存放的文件夹路径
            } else {
                imgFolderPath = dstImgFolder + File.separator + imagePDFName;
            }

            if (createDirectory(imgFolderPath)) {

                pdDocument = PDDocument.load(file);
                PDFRenderer renderer = new PDFRenderer(pdDocument);
                /* dpi越大转换后越清晰，相对转换速度越慢 */
                PdfReader reader = new PdfReader(PdfFilePath);
                int pages = reader.getNumberOfPages();
                StringBuffer imgFilePath = null;
                for (int i = 0; i < pages; i++) {
                    String imgFilePathPrefix = imgFolderPath + File.separator + imagePDFName;
                    imgFilePath = new StringBuffer();
                    imgFilePath.append(imgFilePathPrefix);
                    imgFilePath.append("_");
                    imgFilePath.append(String.valueOf(i + 1));
                    imgFilePath.append(".jpg");
                    File dstFile = new File(imgFilePath.toString());
                    BufferedImage image = renderer.renderImageWithDPI(i, dpi);
                    ImageIO.write(image, "png", dstFile);
                }
                System.out.println("PDF文档转PNG图片成功！");

            } else {
                System.out.println("PDF文档转PNG图片失败：" + "创建" + imgFolderPath + "失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean createDirectory(String folder) {
        File dir = new File(folder);
        if (dir.exists()) {
            return true;
        } else {
            return dir.mkdirs();
        }
    }

}
