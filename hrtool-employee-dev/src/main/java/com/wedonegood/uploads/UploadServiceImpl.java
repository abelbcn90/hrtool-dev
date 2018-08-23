package com.wedonegood.uploads;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {

    private final static String UPLOADS_FOLDER = "uploads.folder";
    private final static String FILE_NAME_PROFILE_PICTURE = "file.name.profile.picture";
    private final static String FILE_NAME_PASSPORT_SCAN = "file.name.passport.scan";
    private final static String FILE_NAME_SMALL = "file.name.small";
    private final static String FILE_NAME_MEDIUM = "file.name.medium";
    private final static String FILE_NAME_BIG = "file.name.big";
    private final static String FILE_NAME_PREVIEW = "file.name.preview";
    private final static String FILE_SIZE_SMALL = "file.size.small";
    private final static String FILE_SIZE_MEDIUM = "file.size.medium";
    private final static String FILE_SIZE_BIG = "file.size.big";
	private final static String FILE_SIZE_PREVIEW = "file.size.preview";
	final static int SCALED_SIZE_SMALL = 44;
	final static int SCALED_SIZE_MEDIUM = 56;
	final static int SCALED_SIZE_BIG = 138;
	final static int SCALED_SIZE_PREVIEW = 99;
    
    @Autowired
	private Environment env;

    @Override
    public String createProfilePicture(final Long employeeId, final String originalFileName, final InputStream file) throws IOException {
        final File f = new File(this.getFilePath(employeeId, this.env.getProperty(FILE_NAME_PROFILE_PICTURE) + originalFileName.substring(originalFileName.indexOf("."))));
        
	    f.getParentFile().mkdirs();
	    Files.copy(file, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    
	    ImageIOUtil.writeImage(this.resizeImage(f, SCALED_SIZE_SMALL, SCALED_SIZE_SMALL), this.getFilePath(employeeId, this.env.getProperty(FILE_NAME_SMALL) + originalFileName.substring(originalFileName.indexOf("."))), 300);
	    ImageIOUtil.writeImage(this.resizeImage(f, SCALED_SIZE_MEDIUM, SCALED_SIZE_MEDIUM), this.getFilePath(employeeId, this.env.getProperty(FILE_NAME_MEDIUM) + originalFileName.substring(originalFileName.indexOf("."))), 300);
	    ImageIOUtil.writeImage(this.resizeImage(f, SCALED_SIZE_BIG, SCALED_SIZE_BIG), this.getFilePath(employeeId, this.env.getProperty(FILE_NAME_BIG) + originalFileName.substring(originalFileName.indexOf("."))), 300);
	    
        return f.getPath();
    }
    
    @Override
    public String createPassportScan(final Long employeeId, final String originalFileName, final InputStream file) throws IOException {
    	final File f = new File(this.getFilePath(employeeId, this.env.getProperty(FILE_NAME_PASSPORT_SCAN) + originalFileName.substring(originalFileName.indexOf("."))));
    	
    	f.getParentFile().mkdirs();
    	Files.copy(file, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
    	
    	ImageIOUtil.writeImage(this.resizeImage(f, SCALED_SIZE_PREVIEW, SCALED_SIZE_PREVIEW), this.getFilePath(employeeId, this.env.getProperty(FILE_NAME_PREVIEW) + originalFileName.substring(originalFileName.indexOf("."))), 300);
    	
    	return f.getPath();
    }
    
    @Override
    public String createPassportScanPdf(final Long employeeId, final String originalFileName, final InputStream file) throws IOException {
    	final File f = new File(this.getFilePath(employeeId, this.env.getProperty(FILE_NAME_PASSPORT_SCAN) + originalFileName.substring(originalFileName.indexOf("."))));
    	
    	f.getParentFile().mkdirs();
    	Files.copy(file, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
    	
    	final PDDocument pdDocument = PDDocument.load(f, MemoryUsageSetting.setupTempFileOnly());
		final PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
		
		ImageIOUtil.writeImage(this.resizeImagePdf(pdfRenderer, SCALED_SIZE_PREVIEW, SCALED_SIZE_PREVIEW, 0), this.getFilePath(employeeId, this.env.getProperty(FILE_NAME_PREVIEW) + ".jpg"), 300);
		
		pdDocument.close();
    	
    	return f.getPath();
    }
    
    @Override
    public String getFilePath(final Long employeeId, final String fileName) {
    	return this.env.getProperty(UPLOADS_FOLDER) + employeeId + "\\" + fileName;
    }
    
    @Override
    public void deletePassportScan(final Long employeeId, final String filePath) {
    	final File file = new File(filePath);
    	file.delete();
    	
    	final File preview = new File(this.getFilePath(employeeId, this.env.getProperty(FILE_NAME_PREVIEW) + ".jpg"));
    	preview.delete();
    }
    
    /**
     * 
     * @param file
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
	private BufferedImage resizeImage(final File file, final int width, final int height) throws IOException {
		final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    final Graphics2D normalGraphics2D = bufferedImage.createGraphics(); //create a graphics object to paint to
	    normalGraphics2D.setBackground(Color.WHITE);
	    normalGraphics2D.setPaint(Color.WHITE);
	    normalGraphics2D.fillRect(0, 0, width, height);
	    normalGraphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    
	    final Image image = ImageIO.read(file);
	    
	    final Dimension pageSize = new Dimension(image.getWidth(null), image.getHeight(null));
	    final Dimension boundary = new Dimension(width, height);
	    
	    final Dimension pageScaledSize = this.getScaledDimension(pageSize, boundary);
	    final Double pageWith = pageScaledSize.getWidth();
	    final Double pageHeight = pageScaledSize.getHeight();
	    
	    final int x = (width - pageWith.intValue()) / 2;
	    normalGraphics2D.drawImage(image, x, 0, pageWith.intValue(), pageHeight.intValue(), null); //draw the image scaled
		
		return bufferedImage;
	}
	
	/**
	 * 
	 * @param pdfRenderer
	 * @param width
	 * @param height
	 * @param pageIndex
	 * @return
	 * @throws IOException
	 */
	private BufferedImage resizeImagePdf(final PDFRenderer pdfRenderer, final int width, final int height, final int pageIndex) throws IOException {
	    final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    final Graphics2D normalGraphics2D = bufferedImage.createGraphics(); //create a graphics object to paint to
	    normalGraphics2D.setBackground(Color.WHITE);
	    normalGraphics2D.setPaint(Color.WHITE);
	    normalGraphics2D.fillRect(0, 0, width, height);
	    normalGraphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    
	    final Image page = pdfRenderer.renderImageWithDPI(pageIndex, 300, ImageType.RGB);
	    
	    final Dimension pageSize = new Dimension(page.getWidth(null), page.getHeight(null));
	    final Dimension boundary = new Dimension(width, height);
	    
	    final Dimension pageScaledSize = this.getScaledDimension(pageSize, boundary);
	    final Double pageWith = pageScaledSize.getWidth();
	    final Double pageHeight = pageScaledSize.getHeight();
	    
	    final int x = (width - pageWith.intValue()) / 2;
	    normalGraphics2D.drawImage(page, x, 0, pageWith.intValue(), pageHeight.intValue(), null); //draw the image scaled
		
		return bufferedImage;
	}
	
	/**
	 * 
	 * @param imgSize
	 * @param boundary
	 * @return
	 */
	private Dimension getScaledDimension(final Dimension imgSize, final Dimension boundary) {
	    final int original_width = imgSize.width;
	    final int original_height = imgSize.height;
	    final int bound_width = boundary.width;
	    final int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;

	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }

	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }

	    return new Dimension(new_width, new_height);
	}
}
