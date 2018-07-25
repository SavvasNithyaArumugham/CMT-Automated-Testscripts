package com.pearson.framework;

import java.awt.image.BufferedImage;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.FileInputStream;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordDocumentManager
{
    private final String filePath;
    private final String fileName;
    
    public WordDocumentManager(final String filePath, final String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }
    
    public void createDocument() {
        final XWPFDocument document = new XWPFDocument();
        this.writeIntoFile(document);
    }
    
    private void writeIntoFile(final XWPFDocument document) {
        final String absoluteFilePath = String.valueOf(this.filePath) + Util.getFileSeparator() + this.fileName + ".docx";
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(absoluteFilePath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FrameworkException("The specified file \"" + absoluteFilePath + "\" does not exist!");
        }
        try {
            document.write((OutputStream)fileOutputStream);
            fileOutputStream.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
            throw new FrameworkException("Error while writing into the specified Word document \"" + absoluteFilePath + "\"");
        }
    }
    
    public void addPicture(final File pictureFile) {
        final CustomXWPFDocument document = this.openFileForReading();
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText(pictureFile.getName());
        try {
            final String id = document.addPictureData((InputStream)new FileInputStream(pictureFile), 6);
            final BufferedImage image = ImageIO.read(pictureFile);
            document.createPicture(id, document.getNextPicNameNumber(6), image.getWidth(), image.getHeight());
        }
        catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Exception thrown while adding a picture file to a Word document");
        }
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addBreak(BreakType.PAGE);
        this.writeIntoFile((XWPFDocument)document);
    }
    
    private CustomXWPFDocument openFileForReading() {
        final String absoluteFilePath = String.valueOf(this.filePath) + Util.getFileSeparator() + this.fileName + ".docx";
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(absoluteFilePath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FrameworkException("The specified file \"" + absoluteFilePath + "\" does not exist!");
        }
        CustomXWPFDocument document;
        try {
            document = new CustomXWPFDocument((InputStream)fileInputStream);
        }
        catch (IOException e2) {
            e2.printStackTrace();
            throw new FrameworkException("Error while opening the specified Word document \"" + absoluteFilePath + "\"");
        }
        return document;
    }
}
